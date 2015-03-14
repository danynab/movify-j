package business.impl;

import business.UserService;
import models.User;
import org.apache.commons.codec.digest.DigestUtils;
import play.db.ebean.Model;

import java.util.Calendar;

/**
 * Created by Dani on 13/3/15.
 */
public class UserServiceImpl implements UserService {

    private static Model.Finder<String, User> find = new Model.Finder<>(
            String.class, User.class
    );

    @Override
    public User get(String username) {
        return find.byId(username);
    }

    @Override
    public User login(String username, String password) {
        User user = get(username);
        if (user != null) {
            String passwordHash = DigestUtils.md5Hex(password);
            if (user.getPassword().equals(passwordHash))
                return user;
        }
        return null;
    }

    @Override
    public User signUp(String username, String password, String email) {
        User user = get(username);
        if (user != null) return null;
        String passwordHash = DigestUtils.md5Hex(password);
        user = new User(username, email, passwordHash, System.currentTimeMillis());
        user.save();
        return user;
    }

    @Override
    public void increaseExpiration(String username, int months) {
        User user = get(username);
        long expiration = user.getExpiration();
        Calendar expirationCalendar = Calendar.getInstance();
        expirationCalendar.setTimeInMillis(expiration);
        expirationCalendar.add(Calendar.MONTH, months);
        user.setExpiration(expirationCalendar.getTimeInMillis());
        user.update();
    }
}
