package controleatleta;

import java.util.Date;

public class Premiacao {

    private String titulo;
    private Date data;

    public Premiacao(String titulo, Date data) {
        this.titulo = titulo;
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    
    }
    
    @Override
    public String toString() {
        return this.data.toString() + " - " + this.titulo;
    }
}
