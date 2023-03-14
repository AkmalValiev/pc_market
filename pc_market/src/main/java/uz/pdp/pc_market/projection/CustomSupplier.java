package uz.pdp.pc_market.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pc_market.entity.Supplier;

@Projection(types = Supplier.class)
public interface CustomSupplier {
    Integer getId();
    String getName();
    boolean getActive();
    String getPhoneNumber();
}
