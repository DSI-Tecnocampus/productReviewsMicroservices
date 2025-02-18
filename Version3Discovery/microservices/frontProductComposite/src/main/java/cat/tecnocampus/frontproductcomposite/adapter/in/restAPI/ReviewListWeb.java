package cat.tecnocampus.frontproductcomposite.adapter.in.restAPI;

public class ReviewListWeb {
    private long id;
    private String author;
    private String content;
    private int rating;

    public ReviewListWeb() {
    }

    public ReviewListWeb(long id, String author, String content, int rating) {
        this.id = id;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
