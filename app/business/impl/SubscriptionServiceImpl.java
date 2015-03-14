package business.impl;

import business.SubscriptionService;
import models.Subscription;
import play.db.ebean.Model;

import java.util.List;

/**
 * Created by Dani on 13/3/15.
 */
public class SubscriptionServiceImpl implements SubscriptionService {

    private static Model.Finder<Integer, Subscription> find = new Model.Finder<>(
            Integer.class, Subscription.class
    );

    @Override
    public Subscription getByMonths(int months) {
        return find.byId(months);
    }

    @Override
    public List<Subscription> getAllOrderByMonths() {
        return find.orderBy("months").findList();
    }

    @Override
    public Subscription save(String name, String description, int months, float price) {
        Subscription subscription = new Subscription(name, description, months, price);
        subscription.save();
        return subscription;
    }
}
