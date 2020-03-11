package application.repositories;

import application.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

    Iterable<Product> findProductByMerchantId(int merchantId);

    @Override
    <S extends Product> S save(S s);

    Optional<Product> getProductByProductId(int productId);

    void deleteProductByProductId(int productId);
}
