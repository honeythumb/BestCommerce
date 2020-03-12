package application.api;

import application.entities.Merchant;
import application.services.AuthService;
import application.services.MerchantService;
import application.services.TokenService;
import application.utilities.CurrentUser;
import application.utilities.comm.LoginRq;
import application.utilities.comm.LoginRs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {
    @Autowired
   private AuthService auth;
    @Autowired
   private MerchantService service;
    @Autowired
    private TokenService tok;

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @PostMapping("/signin")
    public LoginRs login(@RequestBody LoginRq rq) throws Exception {
        String token = auth.login(rq);
        if (!token.isEmpty()) {
            Optional<Merchant> merch = service.getMerchantByEmail(rq.getEmail());
            return new LoginRs(merch.get(), token);
        }
        else return new LoginRs(null, null);
    }

    @PostMapping("/register")
    public String register(@RequestBody Merchant merchant) {
        merchant.setActive(false);
        merchant.setRoles("ROLES_USER");
        return auth.register(merchant);
    }

    @GetMapping("/confirmation/{token}")
    public String confirm(@PathVariable String token) {
        auth.activated(token);
        return "Account has been activated";
    }

}
