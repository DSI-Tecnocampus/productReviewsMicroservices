package cat.tecnocampus.product.adapter.in.restAPI;

public class ProductDoesNotExistException extends RuntimeException {
    public ProductDoesNotExistException(long id) {
        super("Product with id: " + id + " does not exist.");
    }
}
