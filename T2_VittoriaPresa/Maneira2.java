import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Scanner;

// Recursão com memória
public class Maneira2 {
public static void main(String[] args) throws IOException {

    Scanner in = new Scanner(new FileReader("teste1.txt"));
    int size = Integer.parseInt(in.next());
    String[][] mapa = new String[size][size];
    for (int i = 0; i < mapa.length; i++) {
        for (int j = 0; j < mapa.length; j++) {
            mapa[i][j] = in.next();
        }
    }

    HashMap<String, Integer> posicoesPassadas = new HashMap<String, Integer>();

    int [][] matrizResultados = new int[size][size];

    System.out.println("O valor total de ouro obtido foi: " + caminho(size - 1, 0, size, mapa, posicoesPassadas, matrizResultados));
    System.out.println("Caminho percorrido: " + printaCaminho(size - 1, 0, size, matrizResultados));

}

public static int caminho(int x, int y, int size, String[][] mapa, HashMap<String, Integer> hash, 
int [][] matrizResultados) {

    if (x < 0 || y > size - 1) {
       try {

        matrizResultados[x][y] = Integer.parseInt(mapa[x][y]);

        return -10000;

       } catch (Exception e) {

        return -10000;

       } 
        
    }

    String coordNaMatriz = "" + x + " " + y;

    if (hash.containsKey(coordNaMatriz)) {
        return hash.get(coordNaMatriz);

    } else {

        if (mapa[x][y].equals("x")) {
            return -10000;
        }

        if (mapa[0][size - 1] == "x" || mapa[size - 1][0] == "x") {
            return -10000;
        }
        
        if (x == 0 && y == size - 1) {
            matrizResultados[x][y] = Integer.parseInt(mapa[x][y]);;
            return Integer.parseInt(mapa[x][y]);
            
        }

        int posicao = Integer.parseInt(mapa[x][y]);

        int norte = posicao + caminho(x - 1, y, size, mapa, hash, matrizResultados);

        int nordeste = posicao + caminho(x - 1, y + 1, size, mapa, hash, matrizResultados);

        int leste = posicao + caminho(x, y + 1, size, mapa, hash, matrizResultados);

        if ((norte >= nordeste) && (norte >= leste)) {
            hash.put(coordNaMatriz, norte);
            matrizResultados[x][y] = norte;
            return norte;

        } else if ((nordeste >= norte) && (nordeste >= leste)) {
            hash.put(coordNaMatriz, nordeste);
            matrizResultados[x][y] = nordeste;
            return nordeste;

        } else if ((leste >= norte) && (leste >= nordeste)) {
            hash.put(coordNaMatriz, leste);
            matrizResultados[x][y] = leste;
            return leste;

        } else
            return -10000;
    }
    
}

public static String printaCaminho(int x, int y, int size, int [][] matrizResultados) {

    int norte = 0;
    int nordeste = 0;
    int leste = 0;
    String caminho1 = "";
    int posicaoAtual = matrizResultados[x][y];
    int posicaoFinal = matrizResultados[0][size - 1];

    while (posicaoAtual != posicaoFinal) {

        try {

             norte = matrizResultados[x-1][y];
            
        } catch (Exception e) {

             norte = -100000000;
        }

        try {

            nordeste = matrizResultados[x-1][y+1];
            
        } catch (Exception e) {

            nordeste = -100000000;

        }

        try {

            leste = matrizResultados[x][y+1];
            
        } catch (Exception e) {
            
            leste = -100000000;

        }

        if (Math.max(norte, Math.max(nordeste, leste)) == norte) {

            x--;
            caminho1 += "N ";
            
        }

        if (Math.max(nordeste, Math.max(norte, leste)) == nordeste) {

            x--;
            y++;
            caminho1 += "NO ";
            
        }

        if (Math.max(leste, Math.max(nordeste, norte)) == leste) {

            y++;
            caminho1 += "L ";
            
        }

        posicaoAtual = matrizResultados[x][y];

    }
    return caminho1;
}

}