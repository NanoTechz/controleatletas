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

    public void testConstrutor() {
        assertNotNull(controle.getListaJogadores());
    }

    public void testPesquisar() {
        JogadorBasquete jogadorBasquete = new JogadorBasquete("Fulano de Tal");
        controle.adicionar(jogadorBasquete);

        assertNotNull(controle.pesquisar("Fulano de Tal"));
        assertNull(controle.pesquisar("Maicon"));
    }
}
