package uz.pdp.pc_market.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pc_market.entity.Warehouse;

@Projection(types = Warehouse.class)
public interface CustomWarehouse {
    Integer getId();
    String getName();
    boolean getActive();
}
