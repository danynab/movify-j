package models;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Dani on 13/3/15.
 */
@Entity
public class Review extends Model {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private int movieId;
    @Column(length=1500)
    private String comment;
    private double rating;

    public Review(String username, int movieId, String comment, double rating) {
        this.username = username;
        this.movieId = movieId;
        this.comment = comment;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Review review = (Review) o;

        if (movieId != review.movieId) return false;
        if (!username.equals(review.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + movieId;
        return result;
    }

    @Override
    public String toString() {
        return "Review{" +
                "username='" + username + '\'' +
                ", movieId=" + movieId +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                '}';
    }
}
