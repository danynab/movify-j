package business;

import models.Review;

import java.util.List;

/**
 * Created by Dani on 13/3/15.
 */
public interface ReviewService {

    public List<Review> getByMovieId(int movieId);

    public List<Review> getByMovieIdAndUsername(int movieId, String username);

    public Review rateMovie(String username, int movieId, String comment, double rating);
}
