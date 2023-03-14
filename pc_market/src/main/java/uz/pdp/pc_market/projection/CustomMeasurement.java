package uz.pdp.pc_market.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pc_market.entity.Measurement;

@Projection(types = Measurement.class)
public interface CustomMeasurement {
    Integer getId();
    String getName();

    boolean getActive();
}
