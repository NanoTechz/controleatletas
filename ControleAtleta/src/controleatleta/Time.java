package controleatleta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Time {
    private String nome;
    private Endereco endereco;
    private List<String> contatos;
    private Date de;
    private Date ate;

    public Time(String nome, Endereco endereco, Date de) {
        this(nome, endereco, new ArrayList<String>(), de);
    }
    
    public Time(String nome, Endereco endereco, List<String> contatos, Date de) {
        this.nome = nome;
        this.de = de;
        this.endereco = endereco;
        this.contatos = contatos;
    }
        
    public void addTelefone(String telefone) {
        contatos.add(telefone);
    }
        
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<String> getContatos() {
        return contatos;
    }   

    @Override
    public String toString() {
        return nome;
    }
    
    
}
