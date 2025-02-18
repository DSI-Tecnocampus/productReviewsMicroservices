package cat.tecnocampus.frontproductcomposite.application.services;

import java.util.ArrayList;
import java.util.List;

public class ProductComposite {
    private long id;
    private String name;
    private String description;
    private List<Review> reviews = new ArrayList<>();


    public ProductComposite() {
    }

    public ProductComposite(String name, String description, List<Review> reviews) {
        this.name = name;
        this.description = description;
        this.reviews = reviews;
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

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
