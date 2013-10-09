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
    //atributos baseados do projeto -> https://github.com/luisfrvilela/controleatletas
    private short assistencia;
    private short qtdArremessos1Ponto;
    private short qtdArremessos2Pontos;
    private short qtdArremessos3Pontos;
    private short rebotesOfensivos;
    private short rebotesDefensivos;
    private short bloqueios;

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

    public short getAssistencia() {
        return assistencia;
    }

    public void setAssistencia(short assistencia) {
        this.assistencia = assistencia;
    }

    public short getQtdArremessos1Ponto() {
        return qtdArremessos1Ponto;
    }

    public void setQtdArremessos1Ponto(short qtdArremessos1Ponto) {
        this.qtdArremessos1Ponto = qtdArremessos1Ponto;
    }

    public short getQtdArremessos2Pontos() {
        return qtdArremessos2Pontos;
    }

    public void setQtdArremessos2Pontos(short qtdArremessos2Pontos) {
        this.qtdArremessos2Pontos = qtdArremessos2Pontos;
    }

    public short getQtdArremessos3Pontos() {
        return qtdArremessos3Pontos;
    }

    public void setQtdArremessos3Pontos(short qtdArremessos3Pontos) {
        this.qtdArremessos3Pontos = qtdArremessos3Pontos;
    }

    public short getRebotesOfensivos() {
        return rebotesOfensivos;
    }

    public void setRebotesOfensivos(short rebotesOfensivos) {
        this.rebotesOfensivos = rebotesOfensivos;
    }

    public short getRebotesDefensivos() {
        return rebotesDefensivos;
    }

    public void setRebotesDefensivos(short rebotesDefensivos) {
        this.rebotesDefensivos = rebotesDefensivos;
    }

    public short getBloqueios() {
        return bloqueios;
    }

    public void setBloqueios(short bloqueios) {
        this.bloqueios = bloqueios;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return simpleDateFormat.format(data) + " - " + (tipo == 'A' ? "Amistoso" : "Oficial");
    }
}
