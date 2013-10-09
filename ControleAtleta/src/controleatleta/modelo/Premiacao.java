package controleatleta.modelo;

import java.text.SimpleDateFormat;
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(data) + " - " + this.titulo;
    }
}
