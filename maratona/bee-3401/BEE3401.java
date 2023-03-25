import java.util.*;

public class BEE3401 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numeroGoiabeiras = input.nextInt();
        int tamanhoSalto = input.nextInt();
        boolean confereContaminado = true;

        for (int i = 0; i < numeroGoiabeiras; i++) {
            int x = input.nextInt();
            int y = input.nextInt();

            if ((x + y) > tamanhoSalto) {
                confereContaminado = false;
                i = numeroGoiabeiras; // Interrompe as repetições
            }
        }

        if (confereContaminado == false)
            System.out.println("N");
        else
            System.out.println("S");

        input.close();
    }
}