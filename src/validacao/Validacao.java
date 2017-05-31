
package validacao;

import com.sun.prism.paint.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class Validacao {
    
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
    
    public BufferedImage checkExistSkeleton(String digital){
        try{
            digital = digital.replaceAll(".jpg", "Esqueleto.jpg");
            BufferedImage skeleton = ImageIO.read(new File(digital));
            return skeleton;
        }catch(IOException e){
            return null;
        }
    }
    
    public void digitalValidate(BufferedImage imgCad, BufferedImage imgLog){
        
        if(imgCad.getHeight() == imgLog.getHeight() && imgCad.getWidth() == imgLog.getWidth()){
            
            //ArrayList pixelsLog = new ArrayList();
            //ArrayList pixelsCad = new ArrayList();
            
            int nMinuciaLog = 0;
            int nMinuciaCad = 0;
            
            int b = java.awt.Color.BLACK.getRGB();
            int w = java.awt.Color.WHITE.getRGB();
            
            int[] N = new int[imgCad.getHeight()*imgCad.getWidth()];
            ArrayList pixels = new ArrayList();
            
            for(int j = 0; j < imgCad.getHeight(); j++){
                for(int i = 0; i < imgCad.getWidth(); i++){
                    pixels.add(imgCad.getRGB(i, j));
                }
            }
            
            for(int i = 0; i < N.length; i++){
                N[i] = (int) pixels.get(i);
                if(N[i] == b){
                    System.out.println("Preto");
                }else if(N[i] == w){
                    System.out.println("Branco");
                }
            }
        }
    }
}
