package application.api;

import application.entities.Merchant;
import application.services.AuthService;
import application.services.MerchantService;
import application.services.TokenService;
import application.utilities.comm.LoginRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
   private AuthService auth;
    @Autowired
   private MerchantService service;
    @Autowired
    private TokenService tok;

    @PostMapping("/signin")
    public String login(@RequestBody LoginRq rq) throws Exception {
        return auth.login(rq);
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
