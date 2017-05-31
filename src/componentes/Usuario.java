
package componentes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Icon;
import validacao.Validacao;

public class Usuario {
    private String nome;
    private String sobrenome;
    private String rg;
    private String cpf;
    private Icon digital;
    private BufferedImage digitalTratada = null;

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

    public BufferedImage getDigitalTratada() {
        return digitalTratada;
    }

    public void setDigitalTratada(BufferedImage digitalTratada) {
        this.digitalTratada = digitalTratada;
    }
    
    public void trataImage(){
        
        String urlImage = digital.toString();
        urlImage = urlImage.replaceAll("\\\\", "/");
        System.out.println(urlImage);
        tratamentoImg testeImg = new tratamentoImg();
        testeImg.convert(urlImage);
        
        this.digitalTratada = testeImg.getNewImage();
        
        //Validacao val = new Validacao();
        
        //val.getDigitalDetail(this.digitalTratada);
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
