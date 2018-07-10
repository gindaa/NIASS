package idev.gin.nias.data.model;

public class LoginTokenCall {
    private String type;
    private String token;
    private String refreshToken;

    public String getType() {
        return type;
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
