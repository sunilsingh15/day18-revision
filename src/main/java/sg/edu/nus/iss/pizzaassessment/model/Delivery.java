package sg.edu.nus.iss.pizzaassessment.model;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Delivery implements Serializable {

    @NotBlank(message = "Please state your name")
    @Size(min = 3, message = "Name cannot be less than 3 characters")
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    @Size(min = 8)
    private String phoneNum;

    private boolean isRush = false;

    private String comments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean isRush() {
        return isRush;
    }

    public void setRush(boolean isRush) {
        this.isRush = isRush;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
