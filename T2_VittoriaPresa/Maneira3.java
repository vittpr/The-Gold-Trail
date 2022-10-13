import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Scanner;

// Memória sem recursão
public class Maneira3 {
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new FileReader("teste20.txt"));
        int size = Integer.parseInt(in.next());
        String[][] mapa = new String[size][size];
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa.length; j++) {
                mapa[i][j] = in.next();
            }
        }

        int[][] matrizResultados = new int[size][size];

        System.out.println("O valor total de ouro obtido foi: " + caminho(size, mapa, matrizResultados));
        System.out.println("Caminho percorrido: " + printaCaminho(size - 1, 0, size, matrizResultados));

    }

    public static int caminho(int size, String[][] mapa, int[][] matrizResultados) {
        int norte = -100000000;
        int nordeste = -100000000;
        int leste = -100000000;
        for (int x = 0; x < size; x++) {
            for (int y = size - 1; y >= 0; y--) {
                // System.out.println(mapa[x][y]);

                if (mapa[x][y].equals("x")) {
                    continue;
                }

                if (x == 0 && y == size - 1) {
                    matrizResultados[x][y] = Integer.parseInt(mapa[x][y]);
                    continue;
                }

                int posicao = Integer.parseInt(mapa[x][y]);

                try {
                    norte = posicao + matrizResultados[x - 1][y];
                } catch (Exception e) {
                    norte = -100000000;
                }
                try {
                    nordeste = posicao + matrizResultados[x - 1][y + 1];
                } catch (Exception e) {
                    nordeste = -100000000;
                }
                try {
                    leste = posicao + matrizResultados[x][y + 1];
                } catch (Exception e) {
                    leste = -100000000;
                }

                if ((norte >= nordeste) && (norte >= leste)) {
                    matrizResultados[x][y] = norte;

                } else if ((nordeste >= norte) && (nordeste >= leste)) {
                    matrizResultados[x][y] = nordeste;

                } else if ((leste >= norte) && (leste >= nordeste)) {
                    matrizResultados[x][y] = leste;

                }

            }

        }

        return matrizResultados[size - 1][0];
    }

    public static String printaCaminho(int x, int y, int size, int[][] matrizResultados) {

        int norte = 0;
        int nordeste = 0;
        int leste = 0;
        String caminho1 = "";
        int posicaoAtual = matrizResultados[x][y];
        int posicaoFinal = matrizResultados[0][size - 1];

        while (posicaoAtual != posicaoFinal) {

            try {

                norte = matrizResultados[x - 1][y];

            } catch (Exception e) {

                norte = -100000000;
            }

            try {

                nordeste = matrizResultados[x - 1][y + 1];

            } catch (Exception e) {

                nordeste = -100000000;

            }

            try {

                leste = matrizResultados[x][y + 1];

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