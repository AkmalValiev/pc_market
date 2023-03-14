package uz.pdp.pc_market.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pc_market.entity.Currency;

@Projection(types = Currency.class)
public interface CustomCurrency {

    Integer getId();

    String getName();

    boolean getActive();

}
