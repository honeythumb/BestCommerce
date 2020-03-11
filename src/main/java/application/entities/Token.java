package application.entities;

import javax.persistence.*;

@Entity
@Table
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column
    private String email;

    @Column
    private String registerToken;

    @Column
    private String utilToken;

    public Token(String registerToken, String utilToken) {
        this.registerToken = registerToken;
        this.utilToken = utilToken;
    }

    public Token(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisterToken() {
        return registerToken;
    }

    public void setRegisterToken(String registerToken) {
        this.registerToken = registerToken;
    }

    public String getUtilToken() {
        return utilToken;
    }

    public void setUtilToken(String utilToken) {
        this.utilToken = utilToken;
    }
}
