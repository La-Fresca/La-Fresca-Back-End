package org.lafresca.lafrescabackend.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Branch")
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    private String CafeID;
    private String Address;
    private String ContactNo;
    private Double Longitude;
    private Double Latitude;
    private String BranchManager;

    public String getCafeID() {
        return CafeID;
    }

    public void setCafeID(String cafeID) {
        CafeID = cafeID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public String getBranchManager() {
        return BranchManager;
    }

    public void setBranchManager(String branchManager) {
        BranchManager = branchManager;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "CafeID='" + CafeID + '\'' +
                ", Address='" + Address + '\'' +
                ", ContactNo='" + ContactNo + '\'' +
                ", Longitude=" + Longitude +
                ", Latitude=" + Latitude +
                ", BranchManager='" + BranchManager + '\'' +
                '}';
    }
}
