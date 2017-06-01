
package validacao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class Validacao {

    private BufferedImage imgCad = null;
    private final ArrayList dadosCad = Dados.getDadosCad();
    private ArrayList dadosLog = null;
    private String msg;
    
    public ArrayList getDadosLog() {
        return dadosLog;
    }

    public void setDadosLog(ArrayList dadosLog) {
        this.dadosLog = dadosLog;
    }
    
    public boolean checkData(){
        
        boolean valid = true;
        int x = this.dadosCad.size();
        
        for(int i = 0; i < x; i++){
            //System.out.println("CAD: "+this.dadosCad.get(i)+" LOG: "+this.dadosLog.get(i));
            String cad = this.dadosCad.get(i).toString();
            String log = this.dadosLog.get(i).toString();
            if(!cad.equals(log)){
                //System.out.println("Dados Incopativeis");
                //JOptionPane.showMessageDialog(null, "Dados Incopativeis");
                valid = false;
                break;
            }
        }
        
        if(valid){
           this.msg = "Bem Vindo "+dadosLog.get(0)+"!";
        }
    
        return valid;
    }
    
    public String returnMsg(){
        return this.msg;
    }

    public boolean checkValidForm(JPanel form){
        if(form.isValid()){
            JOptionPane.showMessageDialog(form.getParent(), "Campo Não Preenchido!");
            return false;
        }else{
            return true;
        }   
    }
    
    public boolean lblIsNull(JLabel lblDigital){
        if(lblDigital.getIcon() == null){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean checkExistSkeleton(String digital){
        try{
            digital = digital.replaceAll(".jpg", "Esqueleto.jpg");
            this.imgCad = ImageIO.read(new File(digital));
            return true;
        }catch(IOException e){
            return false;
        }
    }
    
    public boolean digitalValidate(String imgLog){
        
        try {
            BufferedImage newImg = ImageIO.read(new File(imgLog));
            if(this.imgCad.getHeight() == newImg.getHeight() && this.imgCad.getWidth() == newImg.getWidth()){
                
                int[] vectorImgCad = new int[this.imgCad.getHeight()*this.imgCad.getWidth()];
                ArrayList pixelsCad = new ArrayList();

                int[] vectorImgLog = new int[this.imgCad.getHeight()*this.imgCad.getWidth()];
                ArrayList pixelsLog = new ArrayList();

                for(int j = 0; j < this.imgCad.getHeight(); j++){
                    for(int i = 0; i < this.imgCad.getWidth(); i++){
                        pixelsCad.add(this.imgCad.getRGB(i, j));
                        pixelsLog.add(newImg.getRGB(i, j));
                    }
                }

                if(this.getDetail(pixelsCad, vectorImgCad) == this.getDetail(pixelsLog, vectorImgLog)){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        } catch (IOException ex) {
            System.out.println("ERROR: "+ex);
            return false;
        }
    }
    
    private int getDetail(ArrayList arrayImage, int[]newArrayImage){
        
        int b = java.awt.Color.BLACK.getRGB();
        int w = java.awt.Color.WHITE.getRGB();
        int count = 0;
        
        for(int i = 0; i < newArrayImage.length; i++){
            newArrayImage[i] = (int) arrayImage.get(i);
            if(i-1 != -1){
                if(newArrayImage[i] == w && newArrayImage[i-1] == b){
                    count += 1;
                }
            }
        }
        return count;
    }
}
