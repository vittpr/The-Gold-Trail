// Recursão sem memória

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Maneira1 {
    public static void main(String[] args) throws IOException {
    
        Scanner in = new Scanner(new FileReader("teste10.txt"));
        int size = Integer.parseInt(in.next());
        String[][] mapa = new String[size][size];
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa.length; j++) {
                mapa[i][j] = in.next();
            }
        }

        //System.out.println(mapa[9][0]);

        // Saída teste10 
        System.out.println("A quantidade de ouro adquirida foi: " + caminho(9, 0, size, mapa));

        //Saída teste20 (lenta)
        //System.out.println("A quantidade de ouro adquirida foi: " + caminho(19, 0, size, mapa));
    }

    public static int caminho(int x, int y, int size, String[][] mapa) {
        if (x < 0 || y > size - 1)
            return -100000000;
            
        if (mapa[x][y].equals("x"))
            return -100000000;

        if (mapa[0][size - 1] == "x" || mapa[size - 1][0] == "x")
            return -100000000;

        if (x == 0 && y == size - 1)
            return Integer.parseInt(mapa[x][y]);

        int posicao = Integer.parseInt(mapa[x][y]);

        int norte = posicao + caminho(x - 1, y, size, mapa);

        int nordeste = posicao + caminho(x - 1, y + 1, size, mapa);

        int leste = posicao + caminho(x, y + 1, size, mapa);

        // if ((norte >= nordeste) && (norte >= leste)) {
       
        // return norte;
        // }

        // else if ((nordeste >= norte) && (nordeste >= leste)) {
        
        //     return nordeste;
        // }
        // else if ((leste >= norte) && (leste >= nordeste)) {
        
        //     return leste;
        // }

        return 0;
    }
    

 

}