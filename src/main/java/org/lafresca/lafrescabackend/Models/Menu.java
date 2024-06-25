package org.lafresca.lafrescabackend.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private String id;
    private String Name;
    private String Description;
    private String Image;
    private String CafeId;
    private Integer Available; // 1 for available, 0 for not available
    private Double Price;
    private List<String> FoodIds;
    private Integer Deleted;

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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getCafeId() {
        return CafeId;
    }

    public void setCafeId(String cafeId) {
        CafeId = cafeId;
    }

    public Integer getAvailable() {
        return Available;
    }

    public void setAvailable(Integer available) {
        Available = available;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public List<String> getFoodIds() {
        return FoodIds;
    }

    public void setFoodIds(List<String> foodIds) {
        FoodIds = foodIds;
    }

    public Integer getDeleted() {
        return Deleted;
    }

    public void setDeleted(Integer deleted) {
        Deleted = deleted;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", Image='" + Image + '\'' +
                ", CafeId='" + CafeId + '\'' +
                ", Available=" + Available +
                ", Price=" + Price +
                ", FoodIds=" + FoodIds +
                ", Deleted=" + Deleted +
                '}';
    }
}
