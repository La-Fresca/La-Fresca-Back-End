package org.lafresca.lafrescabackend.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Food")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    private String id;
    private String Name;
    private String Description;
    private Double Price;
    private String Image;
    private Integer Available; // 1 for available, 0 for not available
    private List<CustomFeature> Features;
    private String CafeId;
    private Integer Deleted;
    private String Category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Integer getAvailable() {
        return Available;
    }

    public void setAvailable(Integer available) {
        Available = available;
    }

    public List<CustomFeature> getFeatures() {
        return Features;
    }

    public void setFeatures(List<CustomFeature> features) {
        Features = features;
    }

    public String getCafeId() {
        return CafeId;
    }

    public void setCafeId(String cafeId) {
        CafeId = cafeId;
    }

    public Integer getDeleted() {
        return Deleted;
    }

    public void setDeleted(Integer deleted) {
        Deleted = deleted;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", Price=" + Price +
                ", Image='" + Image + '\'' +
                ", Available=" + Available +
                ", Features=" + Features +
                ", CafeId='" + CafeId + '\'' +
                ", Deleted=" + Deleted +
                ", Category='" + Category + '\'' +
                '}';
    }
}
