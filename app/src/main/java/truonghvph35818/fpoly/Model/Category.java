package truonghvph35818.fpoly.Model;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("_id")
    private String id, nameCate;
    private String createdAt, updatedAt;

    public Category() {
    }

    public Category(String id, String nameCate, String createdAt, String updatedAt) {
        this.id = id;
        this.nameCate = nameCate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
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
