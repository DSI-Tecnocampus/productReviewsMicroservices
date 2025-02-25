package cat.tecnocampus.frontproductcomposite.application.services;

public class ProductDoesNotExistException extends RuntimeException {
    public ProductDoesNotExistException(long id) {
        super("Product with id: " + id + " does not exist.");
    }
}
