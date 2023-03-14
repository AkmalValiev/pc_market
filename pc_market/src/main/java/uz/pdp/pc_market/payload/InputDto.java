package uz.pdp.pc_market.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDto {

    @NotNull(message = "warehouseId bo'sh bo'lmasligi kerak!")
    private Integer warehouseId;

    @NotNull(message = "supplierId bo'sh bo'lmasligi kerak!")
    private Integer supplierId;

    @NotNull(message = "currencyId bo'sh bo'lmasligi kerak!")
    private Integer currencyId;

    @NotNull(message = "factureNumber bo'sh bo'lmasligi kerak!")
    private String factureNumber;

    @NotNull(message = "userId bo'sh bo'lmasligi kerak!")
    private Integer userId;

}
