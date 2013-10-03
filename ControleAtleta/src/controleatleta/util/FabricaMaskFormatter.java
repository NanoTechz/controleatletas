package controleatleta.util;

import java.text.ParseException;
import javax.swing.text.MaskFormatter;

public class FabricaMaskFormatter {

    public static MaskFormatter getCPF() throws ParseException {
        MaskFormatter ftmCPF = new MaskFormatter("###.###.###-##");
        ftmCPF.setValidCharacters("0123456789");
        return ftmCPF;
    }

    public static MaskFormatter getRG() throws ParseException {
        MaskFormatter ftmRG = new MaskFormatter("#######");
        ftmRG.setValidCharacters("0123456789");
        return ftmRG;
    }

    public static MaskFormatter getData() throws ParseException {
        MaskFormatter ftmData = new MaskFormatter("##/##/####");
        ftmData.setValidCharacters("0123456789");
        return ftmData;
    }

    public static MaskFormatter getCEP() throws ParseException {
        MaskFormatter ftmCEP =  new MaskFormatter("##.###-##"); 
        ftmCEP.setValidCharacters("0123456789");
        return ftmCEP;
    }
}
