package uz.pdp.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pc_market.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);

}
