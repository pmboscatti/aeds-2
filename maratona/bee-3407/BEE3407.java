import java.util.*;

public class BEE3407 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int itensPrato = input.nextInt();
        int bolinhosDoce = input.nextInt();
        input.nextLine(); // Avisar o programa do salto
        String cafeNatan = input.nextLine().replaceAll(" ", "");
        String cafeSamuel = input.nextLine().replaceAll(" ", "");

        int contadorNatan = 0, contadorSamuel = 0;

        for (int i = 0; i < itensPrato; i++) {
            if (cafeNatan.charAt(i) == '1') {
                contadorNatan++;
            }
            if (cafeSamuel.charAt(i) == '1') {
                contadorSamuel++;
            }
        }

        if (contadorNatan == bolinhosDoce)
            System.out.println("Tudo certo.");
        else if (contadorSamuel == bolinhosDoce)
            System.out.println("Pegou de Samuel.");
        else
            System.out.println("Pegou de um estranho.");

        input.close();
    }
}