package uz.pdp.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.pc_market.entity.Measurement;
import uz.pdp.pc_market.projection.CustomMeasurement;

@RepositoryRestResource(path = "measurement", excerptProjection = CustomMeasurement.class)
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}
