public class TP01Q11 {
    public static void main(String[] args) {
        String palavra = MyIO.readLine();

        // Testa o fim do arquivo
        while (testaFim(palavra) == false) {
            if (conferePalindromo(palavra, 0, (palavra.length() - 1)) == 0) {
                MyIO.println("SIM");
            } else {
                MyIO.println("NAO");
            }

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

    public static int conferePalindromo(String palavra, int esq, int dir) {
        int confere = 0;

        // Recursividade afunilando a posição do início e do fim da string
        while (esq <= dir) {
            if (palavra.charAt(esq) == palavra.charAt(dir)) {
                return 0 + conferePalindromo(palavra, esq + 1, dir - 1);
            } else {
                return 1;
            }
        }

        return confere;
    }
}
