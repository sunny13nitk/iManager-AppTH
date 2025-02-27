package imgr.com.iManager_App.srv.pojos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_SCToken
{
    @NotNull
    private String token;
    @NotNull
    private String sessionid;
}