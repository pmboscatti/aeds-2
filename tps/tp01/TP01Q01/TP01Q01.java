public class TP01Q01 {
    public static void main(String[] args) {
        MyIO.setCharset("ISO-8859-1");

        String palavra = MyIO.readLine();

        // Testa o fim do arquivo
        while (testaFim(palavra) == false) {
            if (conferePalindromo(palavra) == true) {
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

    public static boolean conferePalindromo(String palavra) {
        boolean confere = false;

        // Testa a string pelo início e pelo final
        for (int i = 0, j = (palavra.length() - 1); i < palavra.length(); i++, j--) {
            if (palavra.charAt(i) == palavra.charAt(j)) {
                confere = true;
            } else {
                confere = false;
                i = palavra.length(); // Termina o loop
            }
        }

        return confere;
    }
}
