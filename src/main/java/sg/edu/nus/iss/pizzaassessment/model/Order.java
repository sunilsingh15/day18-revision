package sg.edu.nus.iss.pizzaassessment.model;

import java.io.Serializable;
import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Order implements Serializable {

    private double totalCost = 0;
    private String orderID;
    private Pizza pizza;
    private Delivery delivery;

    public Order(Pizza pizza, Delivery delivery) {
        this.pizza = pizza;
        this.delivery = delivery;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public String getName() {
        return this.getDelivery().getName();
    }

    public String getPizzaName() {
        return this.getPizza().getPizza();
    }

    public String getAddress() {
        return this.getDelivery().getAddress();
    }

    public String getPhoneNum() {
        return this.getDelivery().getPhoneNum();
    }

    public boolean getRush() {
        return this.getDelivery().isRush();
    }

    public String getComments() {
        return this.getDelivery().getComments();
    }

    public String getSize() {
        return this.getPizza().getSize();
    }

    public int getQuantity() {
        return this.getPizza().getQuantity();
    }

    public double getPizzaCost() {
        return this.getRush() ? this.getTotalCost() - 2 : this.getTotalCost();
    }

    public static JsonObject toJSON(String json) {
        JsonReader r = Json.createReader(new StringReader(json));
        return r.readObject();
    }

    public static Order create(String jsonStr) {
        JsonObject o = toJSON(jsonStr);
        Pizza p = Pizza.create(o);
        Delivery d = Delivery.create(o);
        Order ord = new Order(p, d);
        ord.setOrderID(o.getString("orderID"));
        ord.setTotalCost(o.getJsonNumber("total").doubleValue());

        return ord;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("orderID", this.getOrderID())
                .add("name", this.getName())
                .add("address", this.getAddress())
                .add("phone", this.getPhoneNum())
                .add("rush", this.getRush())
                .add("comments", this.getComments())
                .add("pizza", this.getPizzaName())
                .add("size", this.getSize())
                .add("quantity", this.getQuantity())
                .add("total", this.getTotalCost())
                .build();
    }

}
