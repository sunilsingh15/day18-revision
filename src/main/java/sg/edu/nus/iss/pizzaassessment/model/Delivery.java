package sg.edu.nus.iss.pizzaassessment.model;

import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Delivery implements Serializable {

    @NotBlank(message = "Please enter your name!")
    @Size(min = 3, message = "Name cannot be less than 3 characters")
    private String name;

    @NotBlank(message = "Please enter your address!")
    private String address;

    @NotBlank
    @Pattern(regexp = "^[0-9]{8,}$", message = "Please enter a valid phone number!")
    private String phoneNum;

    private boolean rush = false;

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
        return rush;
    }

    public void setRush(boolean rush) {
        this.rush = rush;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public static Delivery create(JsonObject o) {
        Delivery d = new Delivery();
        d.setName(o.getString("name"));
        d.setAddress(o.getString("address"));
        d.setPhoneNum(o.getString("phone"));
        d.setRush(o.getBoolean("rush"));
        d.setComments(o.getString("comments"));

        return d;
    }

}
