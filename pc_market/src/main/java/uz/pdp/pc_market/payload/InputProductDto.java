package uz.pdp.pc_market.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductDto {

    @NotNull(message = "productId null bo'lmasligi shart!")
    private Integer productId;

    @NotNull(message = "inputId null bo'lmasligi shart!")
    private Integer inputId;

    @NotNull(message = "amount null bo'lmasligi shart!")
    private Integer amount;

    @NotNull(message = "price null bo'lmasligi shart!")
    private Double price;

}
