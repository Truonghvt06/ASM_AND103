package truonghvph35818.fpoly.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryTong {
    @SerializedName("_id")
    private String id ;
    private String nameCate;
    private String createdAt, updatedAt;
    private ArrayList<Product> sanPhams;

    public CategoryTong() {
    }

    public CategoryTong(String nameCate, ArrayList<Product> sanPhams) {
        this.nameCate = nameCate;
        this.sanPhams = sanPhams;
    }

    public CategoryTong( String id, String nameCate, String createdAt, String updatedAt, ArrayList<Product> sanPhams) {
        this.id = id;
        this.nameCate = nameCate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sanPhams = sanPhams;
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

    public ArrayList<Product> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(ArrayList<Product> sanPhams) {
        this.sanPhams = sanPhams;
    }
}
