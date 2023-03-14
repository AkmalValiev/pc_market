package uz.pdp.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pc_market.entity.Detail;

public interface DetailRepository extends JpaRepository<Detail, Integer> {

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);

}
