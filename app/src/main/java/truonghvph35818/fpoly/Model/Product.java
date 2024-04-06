package truonghvph35818.fpoly.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {
    @SerializedName("_id")
    private  String id;
    private String image, productName, description;
    private int price, quantity;
    private String createdAt, updatedAt;
//    @SerializedName("id_category")
    private CategoryTong categoryTong;
    private String id_category;

    public Product() {
    }

    public Product(String id, String image, String productName, String description, int price, int quantity, String createdAt, String updatedAt, CategoryTong categoryTong, String id_category) {
        this.id = id;
        this.image = image;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.categoryTong = categoryTong;
        this.id_category = id_category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public CategoryTong getCategoryTong() {
        return categoryTong;
    }

    public void setCategoryTong(CategoryTong categoryTong) {
        this.categoryTong = categoryTong;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }
}
