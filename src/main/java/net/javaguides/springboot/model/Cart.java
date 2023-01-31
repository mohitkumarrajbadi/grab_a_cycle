package net.javaguides.springboot.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cart {

    public Integer getCart_id() {
        return cart_id;
    }

    public void setCart_id(Integer cart_id) {
        this.cart_id = cart_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Id
    Integer cart_id;
    Long user_id;




}
