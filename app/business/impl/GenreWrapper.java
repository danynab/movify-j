package business.impl;

/**
 * Created by Dani on 14/3/15.
 */
public class GenreWrapper {
    private String name;
    private String image;

    public GenreWrapper(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}