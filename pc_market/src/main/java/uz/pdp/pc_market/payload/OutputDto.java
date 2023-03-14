package uz.pdp.pc_market.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputDto {

    @NotNull(message = "warehouseId null bo'lmasligi shart!")
    private Integer warehouseId;

    @NotNull(message = "currencyId null bo'lmasligi shart!")
    private Integer currencyId;

    @NotNull(message = "clientId null bo'lmasligi shart!")
    private Integer clientId;

    @NotNull(message = "userId null bo'lmasligi shart!")
    private Integer userId;

    @NotNull(message = "factureNumber null bo'lmasligi shart!")
    private String factureNumber;

}
