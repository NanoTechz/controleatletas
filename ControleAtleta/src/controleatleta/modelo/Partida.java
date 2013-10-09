package controleatleta.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Partida {
    private Date data;
    private char tipo; // A=amistoso O=oficial 
    private boolean pelaSelecaoNacional;
    private boolean foiVencedor;
    private short pontuacao;
    private short faltasCometidas;
    private short faltasSofridas;
    private short qtdLancesLivres;
    private short qtdLancesLivresConvertidos;

    public Partida(Date data, char tipo, boolean pelaSelecaoNacional) {
        this.data = data;
        this.tipo = tipo;
        this.pelaSelecaoNacional = pelaSelecaoNacional;
    }

    public boolean isPelaSelecaoNacional() {
        return pelaSelecaoNacional;
    }

    public void setPelaSelecaoNacional(boolean pelaSelecaoNacional) {
        this.pelaSelecaoNacional = pelaSelecaoNacional;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public short getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(short pontuacao) {
        this.pontuacao = pontuacao;
    }

    public short getFaltasCometidas() {
        return faltasCometidas;
    }

    public void setFaltasCometidas(short faltasCometidas) {
        this.faltasCometidas = faltasCometidas;
    }

    public short getFaltasSofridas() {
        return faltasSofridas;
    }

    public void setFaltasSofridas(short faltasSofridas) {
        this.faltasSofridas = faltasSofridas;
    }

    public short getQtdLancesLivres() {
        return qtdLancesLivres;
    }

    public void setQtdLancesLivres(short qtdLancesLivres) {
        this.qtdLancesLivres = qtdLancesLivres;
    }

    public short getQtdLancesLivresConvertidos() {
        return qtdLancesLivresConvertidos;
    }

    public void setQtdLancesLivresConvertidos(short qtdLancesLivresConvertidos) {
        this.qtdLancesLivresConvertidos = qtdLancesLivresConvertidos;
    }

    public boolean isFoiVencedor() {
        return foiVencedor;
    }

    public void setFoiVencedor(boolean foiVencedor) {
        this.foiVencedor = foiVencedor;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        return simpleDateFormat.format(data)+ " - " + (tipo == 'A' ? "Amistoso" : "Oficial");
    }
    
    
}
