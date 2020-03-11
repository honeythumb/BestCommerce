package application.services;
import application.email.Mailer;
import application.entities.Merchant;
import application.entities.Token;
import application.token.TokenProvider;
import application.utilities.comm.LoginRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenProvider provider;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private JavaMailSender sender;

    public String login(LoginRq request) throws Exception {
        try {
            manager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPsw()));
       } catch (Exception ex) {
            throw new Exception("Invalid username / password");
        }
        return provider.generateToken(request.getEmail(), request.isRemember());
    }

    public String register(Merchant merchant) {
        if (merchantService.getMerchantByEmail(merchant.getEmail()).isPresent()) return "User already exist with this email";
        else {
            merchant.setRoles("ROLES_USER");
            merchant.setActive(false);
            merchant.setPassword(encoder.encode(merchant.getPassword()));
            sendConfirmation(merchant);
            merchantService.save(merchant);
            return "Register was successful. Please confirm your email.";
        }
    }

    private void sendConfirmation (Merchant merchant) {
        String regToken = provider.generateToken(merchant.getMerchantName());
        String utilToken = provider.generateToken(merchant.getEmail());
        tokenService.addToken(merchant, new Token(regToken, utilToken));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(Mailer.username);
        message.setTo(merchant.getEmail());
        message.setSubject("Account Confirmation");
        message.setText(
                        "\nName:                " + merchant.getMerchantName() +
                        "\nMerchant Category:   " + merchant.getMerchantType() +
                        "\nProduct Owner:       " + merchant.getOwnerName() +
                        "\nAddress:             " + merchant.getAddress() +
                        "\nPhone Number:        " + merchant.getPhoneNumber() +
                        "\nEmail address:       " + merchant.getEmail() +
                        "\nClick link below to confirm your account: " +
                        "\nhttp://localhost:8080/confirmation/" + regToken);
        sender.send(message);
    }

    private void activate(Merchant merchant) {
        merchant.setActive(true);
        Token token = tokenService.getTokenByEmail(merchant.getEmail());
        token.setRegisterToken("");
        tokenService.updateToken(token);
        merchantService.save(merchant);
    }

    private Merchant confirm(String regToken) {
        return tokenService.getMerchantByRegisterToken(regToken);
    }

    public void activated(String token) {
        activate(confirm(token));
    }

}
