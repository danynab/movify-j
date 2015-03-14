package business;

import models.Movie;
import models.Review;

import java.util.List;

/**
 * Created by Dani on 13/3/15.
 */
public interface MovieService {

    public Movie get(int id);

    public List<Movie> getAll();

    public List<Movie> getRandom(int count);

    public List<Movie> search(String text);

    public Movie save(String title, int year, int duration, String description, String storyline, String director, String writers, String stars, String cover, String background, String movie, String trailer);

    public String movieWithReviewToJson(Movie movie, Review review);

    public String moviesToJson(List<Movie> movies);
}
