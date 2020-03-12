package application.services;

import application.entities.Merchant;
import application.repositories.MerchantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantService{

    @Autowired
    private MerchantRepo repo;

    public Optional<Merchant> getMerchantByEmail(String email) {
        return repo.findMerchantByEmail(email);
    }

    public void save(Merchant merchant) {
        repo.save(merchant);}

}
