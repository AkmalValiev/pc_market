package uz.pdp.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.pc_market.entity.Warehouse;
import uz.pdp.pc_market.projection.CustomWarehouse;

@RepositoryRestResource(path = "warehouse", excerptProjection = CustomWarehouse.class)
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
}
