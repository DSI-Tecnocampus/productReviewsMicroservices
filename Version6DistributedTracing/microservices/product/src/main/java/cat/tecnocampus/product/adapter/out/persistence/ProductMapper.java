package cat.tecnocampus.product.adapter.out.persistence;

import cat.tecnocampus.product.application.services.Product;

public class ProductMapper {
    public static ProductEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        return entity;
    }

    public static Product toModel(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        Product product = new Product();
        product.setId(entity.getId());
        product.setName(entity.getName());
        product.setDescription(entity.getDescription());
        return product;
    }}
