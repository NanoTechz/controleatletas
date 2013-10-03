/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleatleta.util;

import java.util.Date;

/**
 *
 * @author alunos
 */
public class ConverterData {
    
    /**
     * Transforma ######## num objeto Date;
     * Ex. converterStringParaData("22103333"); -> new Date(3333, 10, 22)
     * @param data ######## 
     * @return Date
     */
    public static Date converterStringParaData(String data){
        short campo[] = new short[3];
        try {
            String[] split = data.split("/");
            int i = 0;
            System.out.println(split);
            for (String campoTx : split) {
                campo[i] = Short.parseShort(campoTx);
                i++;
            }
        } catch (Exception e) {
            return null;
        }
        
        if ((campo[0] != 0) && (campo[1] != 0) && (campo[2] != 0) )
             return new Date(campo[2], campo[1], campo[0]);
        
        return null;
    } 
}
