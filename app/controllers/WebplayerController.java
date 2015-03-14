package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import infrastructure.Factories;
import models.Genre;
import models.Movie;
import models.Review;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.webplayer;

import java.util.List;

/**
 * Created by Dani on 14/3/15.
 */
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
        return ok(Json.parse(json));
    }

    public static Result getGenre(String name) {
        Genre genre = Factories.businessFactory.getGenreService().get(name);
        return ok(Json.toJson(genre));
    }

    public static Result getMoviesByGenre(String name) {
        List<Movie> movies = Factories.businessFactory.getGenreService().getMovies(name);
        String json = Factories.businessFactory.getMovieService().moviesToJson(movies);
        return ok(Json.parse(json));
    }

    public static Result findMovies() {
        String search = request().getQueryString("search");
        List<Movie> movies = null;
        if (search == null) {
            movies = Factories.businessFactory.getMovieService().getAll();
        }
        else {
            movies = Factories.businessFactory.getMovieService().search(search);
        }
        String json = Factories.businessFactory.getMovieService().moviesToJson(movies);
        return ok(Json.parse(json));
    }

    public static Result getMovie(int id) {
        Movie movie = Factories.businessFactory.getMovieService().get(id);
        String userName = session(Application.USERNAME_KEY);
        Review review =  Factories.businessFactory.getReviewService().getByMovieIdAndUsername(movie.getId(), userName);
        String json = Factories.businessFactory.getMovieService().movieWithReviewToJson(movie, review);
        return ok(Json.parse(json));
    }
}
