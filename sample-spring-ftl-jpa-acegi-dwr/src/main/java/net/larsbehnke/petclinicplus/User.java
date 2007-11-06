package net.larsbehnke.petclinicplus;

public class User extends Person {

    private static final long serialVersionUID = -1306581235758909330L;

    private String loginName;
    
    private String password;
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

}
