package controleatleta;

import java.util.Date;

public class Time {

    private String nome;
    private Date de;
    private Date ate;

    public Time(String nome, Date de) {
        this.nome = nome;
        this.de = de;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDe() {
        return de;
    }

    public void setDe(Date de) {
        this.de = de;
    }

    public Date getAte() {
        return ate;
    }

    public void setAte(Date ate) {
        this.ate = ate;
    }

    @Override
    public String toString() {
        return nome;
    }
}
