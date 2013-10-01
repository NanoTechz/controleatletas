package controleatleta;

import java.util.ArrayList;
import java.util.List;


public class JogadorBasquete extends Atleta{
    private char categoria; // A=Amador P=Profissional
    private Posicao posicao;
    private short numeroCamisa;
    private List<Time> clubes;
    private List<DadosPartida> historicoPartidas;

    public JogadorBasquete(Posicao posicao, String nome) {
        super(nome);
        this.posicao = posicao;
        this.clubes = new ArrayList<Time>();
        this.historicoPartidas = new ArrayList<DadosPartida>();
    }

    public void addTime(Time time){
        clubes.add(time);
    } 
    
    public void addPartida(DadosPartida partida){
       historicoPartidas.add(partida);
    }

    public char getCategoria() {
        return categoria;
    }

    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public short getNumeroCamisa() {
        return numeroCamisa;
    }

    public void setNumeroCamisa(short numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }

    public List<Time> getClubes() {
        return clubes;
    }

    public List<DadosPartida> getHistoricoPartidas() {
        return historicoPartidas;
    }    
}
