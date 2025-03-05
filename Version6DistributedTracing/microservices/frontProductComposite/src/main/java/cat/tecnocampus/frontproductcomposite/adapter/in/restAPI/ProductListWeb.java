package cat.tecnocampus.frontproductcomposite.adapter.in.restAPI;

import java.util.ArrayList;
import java.util.List;

public class ProductListWeb {
    private long id;
    private String name;
    private String description;
    private List<ReviewListWeb> reviews = new ArrayList<>();

    public ProductListWeb(long id, String name, String description, List<ReviewListWeb> reviews) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.reviews = reviews;
    }

    public ProductListWeb() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<ReviewListWeb> getReviews() {
        return reviews;
    }
}
