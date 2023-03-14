package uz.pdp.pc_market.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pc_market.entity.Client;

@Projection(types = Client.class)
public interface CustomClient {
    Integer getId();
    String getName();

    String getPhoneNumber();
}
