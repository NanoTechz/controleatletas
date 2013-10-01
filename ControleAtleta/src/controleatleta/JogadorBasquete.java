package controleatleta;

import java.util.ArrayList;
import java.util.List;


public class JogadorBasquete extends Atleta{
    private char categoria; // A=Amador P=Profissional
    private Posicao posicao;
    private short numeroCamisa;
    private List<Time> clubes;
    private List<DadosPartida> historicoPartidas;
    private List<Premiacao> premiacoes;

    public JogadorBasquete(Posicao posicao, String nome) {
        super(nome);
        this.posicao = posicao;
        this.clubes = new ArrayList<Time>();
        this.historicoPartidas = new ArrayList<DadosPartida>();
        this.premiacoes = new ArrayList<Premiacao>();
    }

    public void addTime(Time time){
        this.clubes.add(time);
    } 
    
    public void addPartida(DadosPartida partida){
       this.historicoPartidas.add(partida);
    }
    
    public void addPartida(Premiacao premiacao){
        this.premiacoes.add(premiacao);
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

    public List<Premiacao> getPremiacoes() {
        return premiacoes;
    }
 
}
