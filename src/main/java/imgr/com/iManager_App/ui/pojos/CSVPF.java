package imgr.com.iManager_App.ui.pojos;

import com.opencsv.bean.CsvBindByPosition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CSVPF
{
    @CsvBindByPosition(position = 0)
    private String scrip;
    @CsvBindByPosition(position = 1)
    private int units;
    @CsvBindByPosition(position = 2)
    private double ppu;
}