package business;

import models.Subscription;

import java.util.List;

/**
 * Created by Dani on 13/3/15.
 */
public interface SubscriptionService {

    public Subscription getByMonths(int months);

    public List<Subscription> getAllOrderByMonths();

    public Subscription save(String name, String desription, int months, float price);
}
