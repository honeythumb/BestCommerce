package application.api;


import application.entities.Product;
import application.services.MerchantService;
import application.services.ProductService;
import application.utilities.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@SuppressWarnings("ALL")
@RestController("/product")
public class ProductController {

    @Autowired
    private MerchantService ms;

    @Autowired
    private ProductService ps;

    @PostMapping("/add")
    public String addProduct(Authentication authentication, @RequestBody Product product) {
        product.setMerchantId(CurrentUser.get().getMerchantId());
        ps.addProduct(product);
        return "Product successfully added.";
    }

    @PostMapping("/{productId}/delete")
    public String deleteProduct(@PathVariable int productId) {
       return ps.deleteProduct(productId);
    }

    @PostMapping("/{productId}/update")
    public String updateProduct(@PathVariable long productId, Product product) {
       return ps.updateProduct(productId, product);
    }

    @GetMapping("/{productId}")
    public Optional<Product> showProduct (@PathVariable int productId) {
        return ps.getProduct(productId);
    }

    @GetMapping("/all")
    public Iterable<Product> getAllMyProducts() {
        return ps.getMerchantsProducts(CurrentUser.get().getMerchantId());
    }

}
