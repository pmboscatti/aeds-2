import java.util.*;

public class BEE3398 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double cotacaoMoeda = input.nextDouble();
        int quantidadeMoeda = input.nextInt();

        double totalCalculo = cotacaoMoeda * quantidadeMoeda;

        System.out.println(totalCalculo);

        input.close();
    }
}