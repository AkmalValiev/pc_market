package uz.pdp.pc_market.entity.utils;

import jakarta.persistence.*;
import lombok.Data;


@Data
@MappedSuperclass
public abstract class AbsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    private boolean active;

}
