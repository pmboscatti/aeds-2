import java.util.*;

public class BEE3357 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        double L = input.nextDouble();
        double Q = input.nextDouble();
        String[] pessoas = new String[N];

        for (int i = 0; i < N; i++) {
            pessoas[i] = input.next();
        }

        int qtdGoles = (int) (L / Q);
        double sobraChimarrao = L % Q;

        System.out.print(pessoas[(int) (qtdGoles % N)] + " ");
        System.out.printf("%.1f\n", sobraChimarrao);

        input.close();
    }
}