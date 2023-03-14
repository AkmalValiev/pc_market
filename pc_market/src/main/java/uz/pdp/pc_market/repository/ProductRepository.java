package uz.pdp.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pc_market.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
