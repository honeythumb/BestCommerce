package application.utilities.comm;

import application.entities.Merchant;

public class LoginRs {

    private Merchant merchant;
    private String token;

    public LoginRs(Merchant merchant, String token) {
        this.merchant = merchant;
        this.token = token;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public String getToken() {
        return token;
    }
}
