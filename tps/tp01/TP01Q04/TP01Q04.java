import java.util.Random;

public class TP01Q04 {
    public static void main(String[] args) {
        MyIO.setCharset("ISO-8859-1");

        Random gerador = new Random();
        gerador.setSeed(4);

        String palavra = MyIO.readLine();

        // Testa o fim do arquivo
        while (testaFim(palavra) == false) {
            MyIO.println(sorteiaLetra(palavra, gerador)); // Exibe a frase substituída

            palavra = MyIO.readLine();
        }
    }

    public static boolean testaFim(String palavra) {
        boolean teste = false;

        if (palavra.equals("FIM")) {
            teste = true;
        }

        return teste;
    }

    public static String sorteiaLetra(String palavra, Random gerador) {
        String alterado = new String();

        // Estatística definida para sorteio das mesmas letras nos testes
        char char1 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
        char char2 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));

        for (int i = 0; i < palavra.length(); i++) {
            // Gera string alterada
            if (palavra.charAt(i) == char1) {
                alterado += char2;
            } else {
                alterado += palavra.charAt(i);
            }
        }

        return alterado;
    }
}
