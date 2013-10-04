package controleatleta;

import java.util.ArrayList;


public class JogadorBasquete extends Atleta{
    private char categoria; // A=Amador P=Profissional
    private Posicao posicao;
    private short numeroCamisa;
    private ArrayList<Time> clubes;
    private ArrayList<DadosPartida> historicoPartidas;
    private ArrayList<Premiacao> premiacoes;

    public JogadorBasquete(String nome) {
        super(nome);
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

    public ArrayList<Time> getClubes() {
        return clubes;
    }

    public ArrayList<DadosPartida> getHistoricoPartidas() {
        return historicoPartidas;
    }  

    public ArrayList<Premiacao> getPremiacoes() {
        return premiacoes;
    }
    
    public int getTotalJogos(){
        return historicoPartidas.size();
    }
    
    public int getTotalVitorias(){
        int count =0;
        for (DadosPartida partida : historicoPartidas) {
            if(partida.isFoiVencedor())
                count++;
        }
        
        return count;
    }
    
    public int getJogosSelecao(){
        int count =0;
        for (DadosPartida partida : historicoPartidas) {
            if(partida.isPelaSelecaoNacional())
                count++;
        }
        
        return count;
    } 
    
    public int getVitoriasSelecao(){
        int count =0;
        for (DadosPartida partida : historicoPartidas) {
            if(partida.isPelaSelecaoNacional() && partida.isPelaSelecaoNacional())
                count++;
        }
        
        return count;
    }
    
    public double getPontuacaoMedia(){
        double media = 0;
        
       for (DadosPartida partida : historicoPartidas) {
            media+= partida.getPontuacao();
        }
       
       return media/historicoPartidas.size();
    }
    
    public double getPorcentagemLancesLivresConvertidos(){
        double total= 0;
        double total_convertidos = 0;
        
       for (DadosPartida partida : historicoPartidas) {
            total+= partida.getQtdLancesLivres();
            total_convertidos = partida.getQtdLancesLivresConvertidos();
        }
       
       return total_convertidos/total;
    }
    
    public double getMediaFaltasSofridas(){
        double total= 0;
        
       for (DadosPartida partida : historicoPartidas) {
            total+= partida.getFaltasSofridas();
        }
       
       return total/historicoPartidas.size();
    }

    public double getMediaFaltasCometidas(){
        double total= 0;
        
       for (DadosPartida partida : historicoPartidas) {
            total+= partida.getFaltasCometidas();
        }
       
       return total/historicoPartidas.size();
    }
}
