package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.CartItems;
import net.javaguides.springboot.model.CustomCartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItems,Integer> {


}
