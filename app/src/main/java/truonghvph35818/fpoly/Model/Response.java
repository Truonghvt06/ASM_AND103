package truonghvph35818.fpoly.Model;

public class Response<T> {
    private int status;
    private String mess;
    private T data;
    private String token;
    private String refreshToken;

    public Response() {
    }

    public Response(int status, String mess, T data) {
        this.status = status;
        this.mess = mess;
        this.data = data;
    }

    public Response(int status, String mess, T data, String token, String refreshToken) {
        this.status = status;
        this.mess = mess;
        this.data = data;
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
