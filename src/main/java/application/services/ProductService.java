package application.services;

import application.entities.Product;
import application.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService{

    @Autowired
    private ProductRepo repo;

    public Iterable<Product> getMerchantsProducts(int merchantId) {
        return repo.findProductByMerchantId(merchantId);
    }

    public void addProduct(Product product) {repo.save(product);}

    public String deleteProduct(int productId) {
        Optional<Product> product = repo.getProductByProductId(productId);
        if (product.isPresent()) {
            repo.deleteProductByProductId(productId);
            return "Product successfully deleted";
        }
        else return "Something went wrong";
    }


    public Optional<Product> getProduct(int productId) {
        return repo.getProductByProductId(productId);
    }

    public String updateProduct(long id, Product product) {
        Optional<Product> existing = repo.findById(id);
        if (existing.isPresent()){
            existing.get().setCategory(product.getCategory());
            existing.get().setDeliveryOptions(product.getDeliveryOptions());
            existing.get().setDescription(product.getDescription());
            existing.get().setInventory(product.getInventory());
            existing.get().setName(product.getName());
            existing.get().setUnitPrice(product.getUnitPrice());
            existing.get().setPaymentOptions(product.getPaymentOptions());
            repo.save(existing.get());
            return "Product successfully updated";
        }
        else return "Product not found";
    }

}
