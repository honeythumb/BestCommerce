package application.security;

import application.entities.Merchant;
import application.services.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MerchantService service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Merchant> merchant = service.getMerchantByEmail(email);
        if (merchant.isPresent()) return merchant.map(MyUserDetails::new).get();
        else throw new IllegalAccessError();
    }
}
