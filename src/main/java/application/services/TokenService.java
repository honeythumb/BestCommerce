package application.services;

import application.entities.Merchant;
import application.entities.Token;
import application.repositories.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service
public class TokenService {
    @Autowired
    private TokenRepo repo;
    @Autowired
    private MerchantService merchantService;

    public Token getTokenByEmail(String email) {
        return repo.getByEmail(email);
    }

    public String updateToken(Token token) {
        repo.save(token);
        return "Token updated successfully.";
    }

    public Token getTokenByRegToken(String registerToken) {
        return repo.getByRegisterToken(registerToken);
    }

    public Merchant getMerchantByRegisterToken(String registerToken) {
        return merchantService.getMerchantByEmail(getTokenByRegToken(registerToken).getEmail()).get();
    }

    public void addToken(Merchant merchant, Token token) {
        token.setEmail(merchant.getEmail());
        repo.save(token);
    }

}
