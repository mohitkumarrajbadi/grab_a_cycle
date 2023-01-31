package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.CartItems;
import net.javaguides.springboot.model.CustomCartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomCartItemRepository extends JpaRepository<CustomCartModel, Integer> {
    @Query(value = "select cart.cart_id,\n" +
            "cart_items.quantity,\n" +
            "bicycle.bicycle_id,bicycle.name,bicycle.description,bicycle.price,bicycle.brand,bicycle.model_number,bicycle.image,bicycle.ratings,cart.user_id\n" +
            "from bicycle \n" +
            "join cart_items on cart_items.bicycle_id = bicycle.bicycle_id\n" +
            "join cart on cart.cart_id = cart_items.cart_id  where user_id = :user_ids", nativeQuery = true)
    List<CustomCartModel> findAllWithUserId(@Param("user_ids") Long user_ids);
}
