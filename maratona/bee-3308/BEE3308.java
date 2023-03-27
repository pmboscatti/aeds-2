import java.util.*;

public class BEE3308 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int N = input.nextInt();

        for (int i = 0; i < N; i++) {
            int M = input.nextInt();
            int A1 = input.nextInt();
            int A2 = input.nextInt();
            int A3 = input.nextInt();

            if (M == 0) { // Teste para o elevador parado
                if ((A1 + A2 + A3) == 0)
                    System.out.println("0");
                else if ((A1 + A2 + A3) == 1)
                    System.out.println("1");
                else
                    System.out.println("X");
            } else { // Teste para o elevador em movimento
                if ((A1 + A2 + A3) <= 1)
                    System.out.println("0");
                else
                    System.out.println("X");
            }
        }

        input.close();
    }
}