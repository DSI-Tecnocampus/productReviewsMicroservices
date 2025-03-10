package cat.tecnocampus.frontproductcomposite.application.services;

public class Review {
    private long id;
    private long productId;
    private String author;
    private String content;
    private int rating;

    public Review() {
    }

    public Review(long productId, String author, String content, int rating) {
        this.productId = productId;
        this.author = author;
        this.content = content;
        this.rating = rating;
    }

    public Review(String author, String content, int rating) {
        this.author = author;
        this.content = content;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
