package uz.pdp.pc_market.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    @NotNull(message = "name bo'sh bo'lmasligi kerak!")
    private String name;

    private boolean active;

    private Integer parenCategoryId;

    private Integer attachmentId;

}
