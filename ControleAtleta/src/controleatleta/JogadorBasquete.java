package controleatleta;

import java.util.ArrayList;


public class JogadorBasquete extends Atleta{
    private char categoria; // A=Amador P=Profissional
    private Posicao posicao;
    private short numeroCamisa;
    private ArrayList<Time> times;
    private ArrayList<Partida> historicoPartidas;
    private ArrayList<Premiacao> premiacoes;

    public JogadorBasquete(String nome) {
        super(nome);
    }

    public void setTimes(ArrayList<Time> times){
        this.times = times;
    }
    
    public void setHistoricoPartidas(ArrayList<Partida> historicoPartidas){
        this.historicoPartidas = historicoPartidas;
    }
    
    public void setPremiacoes(ArrayList<Premiacao> premiacoes){
        this.premiacoes = premiacoes;
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
        return times;
    }

    public ArrayList<Partida> getHistoricoPartidas() {
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
        for (Partida partida : historicoPartidas) {
            if(partida.isFoiVencedor())
                count++;
        }
        
        return count;
    }
    
    public int getJogosSelecao(){
        int count =0;
        for (Partida partida : historicoPartidas) {
            if(partida.isPelaSelecaoNacional())
                count++;
        }
        
        return count;
    } 
    
    public int getVitoriasSelecao(){
        int count =0;
        for (Partida partida : historicoPartidas) {
            if(partida.isPelaSelecaoNacional() && partida.isFoiVencedor())
                count++;
        }
        
        return count;
    }
    
    public double getPontuacaoMedia(){
        double media = 0;
        
       for (Partida partida : historicoPartidas) {
            media+= partida.getPontuacao();
        }
       
       return (historicoPartidas.isEmpty()? 0 : media/historicoPartidas.size());
    }
    
    public double getPorcentagemLancesLivresConvertidos(){
        double total= 0;
        double total_convertidos = 0;
        
       for (Partida partida : historicoPartidas) {
            total+= partida.getQtdLancesLivres();
            total_convertidos += partida.getQtdLancesLivresConvertidos();
        }
       
       return (total == 0? 0 : total_convertidos/total)* 100; 
    
    }
    public double getMediaFaltasSofridas(){
        double total= 0;
        
       for (Partida partida : historicoPartidas) {
            total+= partida.getFaltasSofridas();
        }
       
       return (historicoPartidas.isEmpty()? 0 : total/historicoPartidas.size());
    }

    public double getMediaFaltasCometidas(){
        double total= 0;
        
       for (Partida partida : historicoPartidas) {
            total+= partida.getFaltasCometidas();
        }
       
       return (historicoPartidas.isEmpty()? 0 : total/historicoPartidas.size());
    }
    
    
}
