package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BicycleRepository extends JpaRepository<Bicycle, Integer> {
}
