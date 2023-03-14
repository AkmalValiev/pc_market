package uz.pdp.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.pc_market.entity.Currency;
import uz.pdp.pc_market.projection.CustomCurrency;

@RepositoryRestResource(path = "currency", excerptProjection = CustomCurrency.class)
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
