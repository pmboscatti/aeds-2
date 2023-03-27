import java.util.*;

public class BEE3069 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int P = input.nextInt();
        int S = input.nextInt();
        int contadorTeste = 1;

        while (P != 0 && S != 0) {
            int[] vetorU = new int[S];
            int[] vetorV = new int[S];

            for (int i = 0; i < S; i++) {
                vetorU[i] = input.nextInt();
                vetorV[i] = input.nextInt();
            }

            // Exibir o número do teste que está sendo realizado
            System.out.println("Teste " + contadorTeste);
            contadorTeste++;

            // Colocar um limite que servirá como base de comparação
            int limite = vetorV[0];
            System.out.print(vetorU[0] + " ");

            for (int i = 1; i < S; i++) {
                if (vetorU[i] > limite) {
                    System.out.println(limite);
                    limite = vetorV[i];
                    System.out.print(vetorU[i] + " ");
                }
            }

            System.out.println(limite);

            P = input.nextInt();
            S = input.nextInt();
        }

        input.close();
    }
}