package net.javaguides.springboot.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CartItems {

    public Integer getCart_id() {
        return cart_id;
    }

    public void setCart_id(Integer cart_id) {
        this.cart_id = cart_id;
    }

    public Integer getBicycle_id() {
        return bicycle_id;
    }

    public void setBicycle_id(Integer bicycle_id) {
        this.bicycle_id = bicycle_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Id
    Integer cart_id;

    Integer bicycle_id;
    Integer quantity;
    Double price;

}
