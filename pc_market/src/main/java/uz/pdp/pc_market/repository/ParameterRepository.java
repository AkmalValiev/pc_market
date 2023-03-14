package uz.pdp.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pc_market.entity.Parameter;

public interface ParameterRepository extends JpaRepository<Parameter, Integer> {
}
