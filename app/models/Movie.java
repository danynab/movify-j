package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dani on 13/3/15.
 */
@Entity
public class Movie extends Model {

    @Id
    @GeneratedValue
    private int id;
    private String title;
    private int year;
    private int duration;
    @Column(length=500)
    private String description;
    @Column(length=1000)
    private String storyline;
    private String director;
    private String writers;
    private String stars;
    private String cover;
    private String background;
    private String movie;
    private String trailer;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Genre> genres;

    public Movie(String title, int year, int duration, String description, String storyline, String director, String writers, String stars, String cover, String background, String movie, String trailer) {
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.description = description;
        this.storyline = storyline;
        this.director = director;
        this.writers = writers;
        this.stars = stars;
        this.cover = cover;
        this.background = background;
        this.movie = movie;
        this.trailer = trailer;
        this.genres = new ArrayList<>();
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

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
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

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.addMovie(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return id == movie.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", storyline='" + storyline + '\'' +
                ", director='" + director + '\'' +
                ", writers='" + writers + '\'' +
                ", stars='" + stars + '\'' +
                ", cover='" + cover + '\'' +
                ", background='" + background + '\'' +
                ", movie='" + movie + '\'' +
                ", trailer='" + trailer + '\'' +
                ", genres='" + genres + '\'' +
                '}';
    }
}
