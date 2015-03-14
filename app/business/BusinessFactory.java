package business;

import models.Movie;

/**
 * Created by Dani on 13/3/15.
 */
public interface BusinessFactory {

    public GenreService getGenreService();

    public MovieService getMovieService();

    public ReviewService getReviewService();

    public SubscriptionService getSubscriptionService();

    public UserService getUserService();
}
