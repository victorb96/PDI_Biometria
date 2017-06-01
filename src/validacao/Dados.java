
package validacao;

import java.util.ArrayList;

public class Dados {
    
    private static ArrayList dadosCad = new ArrayList();

    public static ArrayList getDadosCad() {
        return dadosCad;
    }

    public static void setDadosCad(ArrayList dadosCad) {
        Dados.dadosCad = dadosCad;
    }
}
