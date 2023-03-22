import java.util.*;

public class BEE3358 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            String sobrenome = input.nextLine();
            boolean testeDificuldade = false;

            for (int i = 0; i < (sobrenome.length() - 2); i++) {
                if ((testaConsoante(sobrenome.charAt(i)) == true) && (testaConsoante(sobrenome.charAt(i + 1)) == true)
                        && (testaConsoante(sobrenome.charAt(i + 2)) == true)) {
                    testeDificuldade = true;
                    i = sobrenome.length(); // Interrompe as repetições
                }
            }

            if (testeDificuldade) {
                System.out.println(sobrenome + " nao eh facil");
            } else {
                System.out.println(sobrenome + " eh facil");
            }
        }

        input.close();
    }

    public static boolean testaConsoante(char letraTeste) {
        boolean consoante = false;

        char letra = Character.toLowerCase(letraTeste);

        if ((letra >= 'b' && letra <= 'z') && ((letra != 'e') && (letra != 'i') && (letra != 'o') && (letra != 'u'))) {
            consoante = true;
        }

        return consoante;
    }
}