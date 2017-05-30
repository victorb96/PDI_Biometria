
package componentes;

import java.util.ArrayList;
import javax.swing.Icon;

public class Usuario {
    private String nome;
    private String sobrenome;
    private String rg;
    private String cpf;
    private Icon digital;

    public Usuario(String nome, String sobrenome, String rg, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.cpf = cpf;
    }

    public Usuario() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public Icon getDigital() {
        return digital;
    }

    public void setDigital(Icon digital) {
        this.digital = digital;
    }
    
    public void trataImage(){
        
        String urlImage = digital.toString();
        urlImage = urlImage.replaceAll("\\\\", "/");
        System.out.println(urlImage);
        tratamentoImg testeImg = new tratamentoImg();
        testeImg.convert(urlImage);
    }

    public ArrayList teste(){
        ArrayList dados = new ArrayList();
        
        dados.add(nome);
        dados.add(sobrenome);
        dados.add(rg);
        dados.add(cpf);
        
        this.trataImage();
        
        return dados;
    }
}
