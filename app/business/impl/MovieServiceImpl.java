package business.impl;

import business.MovieService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import infrastructure.Factories;
import models.Genre;
import models.Movie;
import models.Review;
import play.db.ebean.Model;
import play.libs.Json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dani on 13/3/15.
 */
public class MovieServiceImpl implements MovieService {

    private static Model.Finder<Integer, Movie> find = new Model.Finder<>(
            Integer.class, Movie.class
    );

    @Override
    public Movie get(int id) {
        return find.byId(id);
    }

    @Override
    public List<Movie> getAll() {
        return find.all();
    }

    @Override
    public List<Movie> getRandom(int count) {
        List<Integer> ids = new ArrayList<>();
        for (Movie movie : getAll())
            ids.add(movie.getId());
        Collections.shuffle(ids);
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < count && i < movies.size(); i++) {
            movies.add(get(ids.get(i)));
        }
        return movies;
    }

    @Override
    public List<Movie> search(String text) {
        return find.where("" +
                "title like '% " + text + "%' or " +
                "director like '%" + text + "%'" +
                "writers like '%" + text + "%'" +
                "stars like '%" + text + "%'").orderBy("title").findList();
    }

    @Override
    public Movie save(String title, int year, int duration, String description, String storyline, String director, String writers, String stars, String cover, String background, String movieUrl, String trailer) {
        Movie movie = new Movie(title, year, duration, description, storyline, director, writers, stars, cover, background, movieUrl, trailer);
        movie.save();
        return movie;
    }

    @Override
    public String movieWithReviewToJson(Movie movie, Review reviewToAdd) {
        ObjectNode result = Json.newObject();
        List<Review> reviews = Factories.businessFactory.getReviewService().getByMovieId(movie.getId());
        int sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        double rating = (new Double((sum / (reviews.size() * 1.0)) * 100).intValue()) / 100.0;
        String genresJson = Factories.businessFactory.getGenreService().genresToJson(movie.getGenres());

        result.put("id", movie.getId());
        result.put("title", movie.getTitle());
        result.put("year", movie.getYear());
        result.put("duration", movie.getDuration());
        result.put("genres", Json.parse(genresJson));
        result.put("description", movie.getDescription());
        result.put("storyline", movie.getStoryline());
        result.put("director", movie.getDirector());
        result.put("writers", movie.getWriters());
        result.put("stars", movie.getStars());
        result.put("cover", movie.getCover());
        result.put("background", movie.getBackground());
        result.put("movie", movie.getMovie());
        result.put("trailer", movie.getTrailer());
        result.put("reviews", Json.toJson(reviews));
        result.put("rating", rating);
        result.put("userReview", Json.toJson(reviewToAdd));

        return result.textValue();
    }

    @Override
    public String moviesToJson(List<Movie> movies) {
        List<MovieWrapper> wrappers = new ArrayList<>();
        for (Movie movie: movies)
            wrappers.add(createWrapper(movie));
        return Json.stringify(Json.toJson(wrappers));
    }

    private MovieWrapper createWrapper(Movie movie) {
        List<Review> reviews = Factories.businessFactory.getReviewService().getByMovieId(movie.getId());
        int sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        List<GenreWrapper> genreWrappers = new ArrayList<>();
        for(Genre genre: movie.getGenres())
            genreWrappers.add(new GenreWrapper(genre.getName(), genre.getImage()));
        double rating = (new Double((sum / (reviews.size() * 1.0)) * 100).intValue()) / 100.0;
        MovieWrapper wrapper = new MovieWrapper(movie.getId(), movie.getTitle(),
                movie.getYear(), movie.getDuration(), genreWrappers, movie.getDescription(),
                movie.getStoryline(), movie.getDirector(), movie.getWriters(), movie.getStars(),
                movie.getCover(), movie.getBackground(), movie.getMovie(), movie.getTrailer(),
                reviews, rating);
        return wrapper;
    }

    public class MovieWrapper {
        private int id;
        private String title;
        private int year;
        private int duration;
        private List<GenreWrapper> genres;
        private String description;
        private String storyline;
        private String director;
        private String writers;
        private String starts;
        private String cover;
        private String background;
        private String movie;
        private String trailer;
        private List<Review> reviews;
        private double rating;

        public MovieWrapper(int id, String title, int year, int duration, List<GenreWrapper> genres, String description, String storyline, String director, String writers, String starts, String cover, String background, String movie, String trailer, List<Review> reviews, double rating) {
            this.id = id;
            this.title = title;
            this.year = year;
            this.duration = duration;
            this.genres = genres;
            this.description = description;
            this.storyline = storyline;
            this.director = director;
            this.writers = writers;
            this.starts = starts;
            this.cover = cover;
            this.background = background;
            this.movie = movie;
            this.trailer = trailer;
            this.reviews = reviews;
            this.rating = rating;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public List<GenreWrapper> getGenres() {
            return genres;
        }

        public void setGenres(List<GenreWrapper> genres) {
            this.genres = genres;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStoryline() {
            return storyline;
        }

        public void setStoryline(String storyline) {
            this.storyline = storyline;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getWriters() {
            return writers;
        }

        public void setWriters(String writers) {
            this.writers = writers;
        }

        public String getStarts() {
            return starts;
        }

        public void setStarts(String starts) {
            this.starts = starts;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getMovie() {
            return movie;
        }

        public void setMovie(String movie) {
            this.movie = movie;
        }

        public String getTrailer() {
            return trailer;
        }

        public void setTrailer(String trailer) {
            this.trailer = trailer;
        }

        public List<Review> getReviews() {
            return reviews;
        }

        public void setReviews(List<Review> reviews) {
            this.reviews = reviews;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }
    }
}
