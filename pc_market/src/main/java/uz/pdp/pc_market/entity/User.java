package uz.pdp.pc_market.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNUmber;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String password;

    private boolean active;

    @ManyToMany
    private Set<Warehouse> warehouses;

}
