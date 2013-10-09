/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleatleta.test;

import controleatleta.controlador.ControleJogadorBasquete;
import controleatleta.modelo.JogadorBasquete;
import junit.framework.Assert.*;
import junit.framework.TestCase;

/**
 *
 * @author nanson
 */
public class ControleJgBasqueteTest extends TestCase {
    
    private ControleJogadorBasquete controle;

    @Override
    protected void setUp() throws Exception {
        controle = new ControleJogadorBasquete();
    }
    
    public void testConstrutor(){
        assertNotNull(controle.getListaJogadores());
    }
    
    public void testPesquisar(){
        for (int i = 0; i < 10; i++) {
            JogadorBasquete jogadorBasquete = new JogadorBasquete(Integer.toString(i)+"teste");
            controle.adicionar(jogadorBasquete);
        }
        
        assertNotNull(controle.pesquisar("5teste"));
        assertNull(controle.pesquisar("teste"));
    }
}
