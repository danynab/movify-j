package business.impl;

import business.MovieService;
import models.Movie;
import play.db.ebean.Model;

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
    public Movie getRandom(int count) {
        List<Integer> ids = new ArrayList<>();
        for (Movie movie : getAll())
            ids.add(movie.getId());
        Collections.shuffle(ids);
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < count && i < movies.size(); i++) {
            movies.add(get(ids.get(i)));
        }
        return null;
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
}
