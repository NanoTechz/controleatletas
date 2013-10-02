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
        short dia, mes, ano;
        
        dia = (short) Integer.parseInt(data.substring(0,2));
        mes = (short) Integer.parseInt(data.substring(2,4));
        ano = (short) Integer.parseInt(data.substring(4));
        
        
        //Meu Deus!
        if ((dia > 0 ) && (dia < 32) && (mes > 0) && (mes < 13) && (ano > 1800 ) && (ano < 3000))
             return new Date(ano, mes, dia);
        
        return null;
    } 
}
