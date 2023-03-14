package uz.pdp.pc_market.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParameterDto {

    private String name;
    private Integer size;
    private Integer measurementId;
    private boolean active;

    @NotNull(message = "Detail bo'sh bo'lmasligi kerak!")
    private Integer detailId;

}
