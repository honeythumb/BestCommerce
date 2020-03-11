package application.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int merchantId;
    @Column(nullable = false, unique = true)
    private String merchantName;
    @Column(nullable = false)
    private String merchantType;
    @Column(nullable = false)
    private String ownerName;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column
    private String roles;
    @Column
    private boolean isActive;

    public Merchant(){}



    public Merchant(String merchantName, String merchantType, String ownerName, String address, String phoneNumber, String email, String password) {
        this.merchantName = merchantName;
        this.merchantType = merchantType;
        this.ownerName = ownerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;

    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
