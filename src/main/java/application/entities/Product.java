package application.entities;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table
public class Product {

    @Id
    @GeneratedValue()
    private int productId;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String name;
    @Column()
    private String description;
    @Column(nullable = false)
    private Double unitPrice;
    @Column(nullable = false)
    @Size(min = 5)
    private int inventory;
    @Column(nullable = false) //not null - choose (Direct and in Install)
    private String paymentOptions;
    @Column(nullable = false) //not null - one to many
    private String deliveryOptions;
    @Column
    private int merchantId;

    public Product(){}

    public Product(String name, double unitPrice, int inventory) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.inventory = inventory;

    }

    public Product(String category, String name, String description,
                        double unitPrice, int inventory,
                            String paymentOptions, String deliveryOptions, int merchantId) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.inventory = inventory;
        this.paymentOptions = paymentOptions;
        this.deliveryOptions = deliveryOptions;
        this.merchantId = merchantId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(String paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public String getDeliveryOptions() {
        return deliveryOptions;
    }

    public void setDeliveryOptions(String deliveryOptions) {
        this.deliveryOptions = deliveryOptions;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }
}
