public class TP01Q03 {
    public static void main(String[] args) {
        String palavra = MyIO.readLine();

        // Testa o fim do arquivo
        while (testaFim(palavra) == false) {
            String cifra = new String();

            // Gera palavra cifrada
            for (int i = 0; i < palavra.length(); i++) {
                cifra += (char) (palavra.charAt(i) + 3);
            }

            // Exibe o resultado
            MyIO.println(cifra);

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
}
