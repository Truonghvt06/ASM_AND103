package truonghvph35818.fpoly.Model;

import com.google.gson.annotations.SerializedName;

public class Cart {
    @SerializedName("_id")
    private String id;
    private String soLuong;
    private String id_product;

    public Cart() {
    }

    public Cart(String id, String soLuong, String id_product) {
        this.id = id;
        this.soLuong = soLuong;
        this.id_product = id_product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }
}
