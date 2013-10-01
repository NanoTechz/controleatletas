package controleatleta;

public class Telefone {
    private short ddd; //Discagem Direta à Distância
    private short ddi; // Discagem Direta Internacional
    private String numero; 

    public Telefone(short ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }
    
    public short getDdd() {
        return ddd;
    }

    public void setDdd(short ddd) {
        this.ddd = ddd;
    }

    public short getDdi() {
        return ddi;
    }

    public void setDdi(short ddi) {
        this.ddi = ddi;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
}
