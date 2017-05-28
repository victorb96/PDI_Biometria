import javax.swing.JFrame;
import telas.Tela_Cad;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Tela_Cad tela_cad = new Tela_Cad();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(tela_cad);
        frame.pack();
        frame.setVisible(true);
    }
    
}
