public class TP01Q13 {
    public static void main(String[] args) {
        String palavra = MyIO.readLine();

        // Testa o fim do arquivo
        while (testaFim(palavra) == false) {

            MyIO.println(cifraCesar(palavra, 0));

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

    public static String cifraCesar(String palavra, int i) {
        String cifra = new String();

        // Recursividade adiciona letra criptografada a cada chamada
        if (i == palavra.length() - 1) {
            cifra += (char) (palavra.charAt(i) + 3);
        } else {
            cifra += ((char) (palavra.charAt(i) + 3)) + cifraCesar(palavra, i + 1);
        }

        return cifra;
    }
}
