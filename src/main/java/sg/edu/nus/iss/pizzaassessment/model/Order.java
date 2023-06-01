package sg.edu.nus.iss.pizzaassessment.model;

import java.io.Serializable;

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

}
