package application.utilities.comm;


public class LoginRq {

    private String email;
    private String psw;
    private boolean remember;

    public LoginRq(String email, String psw, boolean remember) {
        this.email = email;
        this.psw = psw;
        this.remember = remember;
    }

    public String getEmail() {
        return email;
    }

    public String getPsw() {
        return psw;
    }

    public boolean isRemember() {
        return remember;
    }
}
