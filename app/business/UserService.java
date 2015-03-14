package business;

import models.User;

/**
 * Created by Dani on 13/3/15.
 */
public interface UserService {

    public User get(String username);

    public User login(String username, String password);

    public User signUp(String username, String password, String email);

    public void increaseExpiration(String username, int months);
}
