package imgr.com.iManager_App.ui.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "token")
@AllArgsConstructor
@NoArgsConstructor
public class TY_SCToken
{
    @NotNull
    @Id
    private String token;
    @NotNull
    private String sessionid;
}
