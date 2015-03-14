package controllers;

import filters.LoginRequiredAction;
import filters.SubscriptionRequiredAction;
import infrastructure.Factories;
import models.Genre;
import models.Movie;
import models.Review;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.webplayer;

import java.util.List;

/**
 * Created by Dani on 14/3/15.
 */
@With({LoginRequiredAction.class, SubscriptionRequiredAction.class})
public class WebplayerController extends Controller {

    public static Result showWebplayer() {
        List<Movie> randomMovies = Factories.businessFactory.getMovieService().getRandom(6);
        List<Movie> allMovies = Factories.businessFactory.getMovieService().getAll();
        List<Genre> genres = Factories.businessFactory.getGenreService().getAll();
        return ok(webplayer.render(randomMovies, allMovies, genres));
    }

    public static Result findGenres() {
        List<Genre> genres = Factories.businessFactory.getGenreService().getAll();
        String json = Factories.businessFactory.getGenreService().genresToJson(genres);
        return ok(json);
    }

    public static Result getGenre(String name) {
        Genre genre = Factories.businessFactory.getGenreService().get(name);
        return ok(Json.toJson(genre).toString());
    }

    public static Result getMoviesByGenre(String name) {
        List<Movie> movies = Factories.businessFactory.getGenreService().getMovies(name);
        String json = Factories.businessFactory.getMovieService().moviesToJson(movies);
        return ok(json);
    }

    public static Result findMovies() {
        String search = request().getQueryString("search");
        List<Movie> movies;
        if (search == null) {
            movies = Factories.businessFactory.getMovieService().getAll();
        } else {
            movies = Factories.businessFactory.getMovieService().search(search);
        }
        String json = Factories.businessFactory.getMovieService().moviesToJson(movies);
        return ok(json);
    }

    public static Result getMovie(int id) {
        Movie movie = Factories.businessFactory.getMovieService().get(id);
        String userName = session(Application.USERNAME_KEY);
        Review review = Factories.businessFactory.getReviewService().getByMovieIdAndUsername(movie.getId(), userName);
        if (review != null)
            return ok(Factories.businessFactory.getMovieService().movieWithReviewToJson(movie, review));
        else
            return ok(Factories.businessFactory.getMovieService().movieToJson(movie));
    }
}
