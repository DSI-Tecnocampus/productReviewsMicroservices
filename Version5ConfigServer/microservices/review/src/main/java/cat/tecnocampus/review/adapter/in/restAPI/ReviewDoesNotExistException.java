package cat.tecnocampus.review.adapter.in.restAPI;

public class ReviewDoesNotExistException extends RuntimeException {
    public ReviewDoesNotExistException(long id) {
        super("Product with id: " + id + " does not exist.");
    }
}
