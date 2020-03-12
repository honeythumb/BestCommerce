package application.utilities;

import application.entities.Merchant;
import application.services.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {
    private static MerchantService service;

    @Autowired
    public CurrentUser(MerchantService service) {
        CurrentUser.service = service;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static Merchant get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return service.getMerchantByEmail(email).get();
    }
}
