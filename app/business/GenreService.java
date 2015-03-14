package business;

import models.Genre;
import models.Movie;

import java.util.List;

/**
 * Created by Dani on 13/3/15.
 */
public interface GenreService {

    public Genre get(String name);

    public List<Genre> getAll();

    public List<Movie> getMovies(String name);

    public Genre save(String name, String image);

}
