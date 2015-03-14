package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Dani on 13/3/15.
 */
@Entity
public class Subscription extends Model {

    @Id
    private int months;
    private String name;
    private String description;
    private double price;

    public Subscription(String name, String description, int months, double price) {
        this.months = months;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Subscription that = (Subscription) o;

        return months == that.months;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + months;
        return result;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "months=" + months +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}

