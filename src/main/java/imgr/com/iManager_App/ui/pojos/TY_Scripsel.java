package imgr.com.iManager_App.ui.pojos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_Scripsel
{
    @NotNull(message = "Scrip cannot be null")
    @NotBlank(message = "Scrip cannot be blank")
    private String scripName;
}
