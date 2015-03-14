package business.impl;

import business.*;

/**
 * Created by Dani on 13/3/15.
 */
public class BusinessFactoryImpl implements BusinessFactory {

    @Override
    public GenreService getGenreService() {
        return new GenreServiceImpl();
    }

    @Override
    public MovieService getMovieService() {
        return new MovieServiceImpl();
    }

    @Override
    public ReviewService getReviewService() {
        return new ReviewServiceImpl();
    }

    @Override
    public SubscriptionService getSubscriptionService() {
        return new SubscriptionServiceImpl();
    }

    @Override
    public UserService getUserService() {
        return new UserServiceImpl();
    }
}
