package truonghvph35818.fpoly.Model;

import com.google.gson.annotations.SerializedName;

public class Bill {
    @SerializedName("_id")
    private String id;
    private String namePro,email,phoneNumber,quantityPro,date,priceBill;
    private String createdAt, updatedAt;

    public Bill() {
    }

    public Bill(String id, String namePro, String email, String phoneNumber, String quantityPro, String date, String priceBill, String createdAt, String updatedAt) {
        this.id = id;
        this.namePro = namePro;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.quantityPro = quantityPro;
        this.date = date;
        this.priceBill = priceBill;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamePro() {
        return namePro;
    }

    public void setNamePro(String namePro) {
        this.namePro = namePro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQuantityPro() {
        return quantityPro;
    }

    public void setQuantityPro(String quantityPro) {
        this.quantityPro = quantityPro;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPriceBill() {
        return priceBill;
    }

    public void setPriceBill(String priceBill) {
        this.priceBill = priceBill;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
