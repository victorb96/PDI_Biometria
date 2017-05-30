
package validacao;

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
}
