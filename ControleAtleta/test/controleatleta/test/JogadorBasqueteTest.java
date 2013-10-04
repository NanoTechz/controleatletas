/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleatleta.test;

import controleatleta.JogadorBasquete;
import controleatleta.Partida;
import java.util.ArrayList;
import junit.framework.TestCase;
import junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;



/**
 *
 * @author nanson
 */
public class JogadorBasqueteTest extends TestCase {
    private final double MARGEM_ERRO = 0.1;
    private JogadorBasquete jogador;
    
    @Override
    protected void setUp() throws Exception {
        jogador = new JogadorBasquete("Teste");
        
        ArrayList<Partida> partidas = new ArrayList<Partida>();
        
        for (int i = 0; i < 100; i++) {
            char tipo = (i % 2 == 0 ? 'A' : 'O');
            
            Partida partida = new Partida(null, tipo , (i % 2 == 0));
            
            if( i > 56){
                partida.setFoiVencedor(true);
            }
            
            partida.setPontuacao((short)i);
            partida.setFaltasSofridas((short)(2 * i));
            partida.setFaltasCometidas((short) ( i% 2 == 0 ? 0 : i));
            partida.setQtdLancesLivres((short)10);
            partida.setQtdLancesLivresConvertidos((short)4);
            
            partidas.add(partida);
        }
        
        jogador.setHistoricoPartidas(partidas);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testPontuacaoMedia(){
        assertEquals(49.5, jogador.getPontuacaoMedia(), MARGEM_ERRO);   
    }
    
    public void testPorcentagemLancesLivresConvertidos(){
        assertEquals(40.0, jogador.getPorcentagemLancesLivresConvertidos(), MARGEM_ERRO);
    }
    
    public void testMediaFaltasSofridas(){
        // Total de faltas = sum 2x, x in 0 to 99 = 9900
        assertEquals(9900.0 / 100, jogador.getMediaFaltasSofridas(), MARGEM_ERRO);
    }
    
    public void testMediaFaltasCometidas(){
        // total de faltas = sum 2x +1 , x in 0 to 49 = 2500
        assertEquals(2500.0 / 100, jogador.getMediaFaltasCometidas(), MARGEM_ERRO);
    }
    
    public void testVencedor(){
        assertEquals(43, jogador.getTotalVitorias());
    }
}
