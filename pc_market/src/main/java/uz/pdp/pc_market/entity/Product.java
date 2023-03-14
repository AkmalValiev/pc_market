package uz.pdp.pc_market.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Category category;

    @Column(nullable = false)
    private Integer guaranteeYear;

    @Column(nullable = false)
    private String description;

    @ManyToMany
    private Set<Detail> details;

}
