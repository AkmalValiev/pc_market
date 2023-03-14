package uz.pdp.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pc_market.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByPhoneNUmber(String phoneNUmber);

}
