package business.impl;

import business.GenreService;
import models.Genre;
import models.Movie;
import play.db.ebean.Model;

import java.util.List;

/**
 * Created by Dani on 13/3/15.
 */
public class GenreServiceImpl implements GenreService {

    private static Model.Finder<String, Genre> find = new Model.Finder<>(
            String.class, Genre.class
    );

    @Override
    public Genre get(String name) {
        return find.byId(name);
    }

    @Override
    public List<Genre> getAll() {
        return find.all();
    }

    @Override
    public List<Movie> getMovies(String name) {
        Genre genre = get(name);
        return genre.getMovies();
    }

    @Override
    public Genre save(String name, String image) {
        Genre genre = new Genre(name, image);
        genre.save();
        return genre;
    }
}
