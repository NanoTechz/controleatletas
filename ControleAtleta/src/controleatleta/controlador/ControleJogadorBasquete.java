package controleatleta.controlador;

import controleatleta.modelo.JogadorBasquete;
import java.util.ArrayList;
import java.util.List;

public class ControleJogadorBasquete {
    private List<JogadorBasquete> listaJogadores;

    public ControleJogadorBasquete() {
        this.listaJogadores = new ArrayList<JogadorBasquete>();
    }
    
    public void adicionar(JogadorBasquete jogador){
        this.listaJogadores.add(jogador);
    }
    
    public void remover(JogadorBasquete jogador){
        this.listaJogadores.remove(jogador);
    }

    public List<JogadorBasquete> getListaJogadores() {
        return listaJogadores;
    }
    
    public JogadorBasquete pesquisar(String nome){
        for (JogadorBasquete jogadorBasquete : listaJogadores) {
            if(jogadorBasquete.getNome().equals(nome))
                return jogadorBasquete;
        }
        
        return null;
    }
}
