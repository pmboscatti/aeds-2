public class TP01Q15 {
    public static void main(String[] args) {
        String palavra = MyIO.readLine();

        // Testa o fim do arquivo
        while (testaFim(palavra) == false) {
            if (testaVogais(palavra, 0) == 0) {
                MyIO.print("SIM ");
            } else {
                MyIO.print("NAO ");
            }
            if (testaConsoantes(palavra, 0) == 0) {
                MyIO.print("SIM ");
            } else {
                MyIO.print("NAO ");
            }
            if (testaInteiros(palavra, 0) == 0) {
                MyIO.print("SIM ");
            } else {
                MyIO.print("NAO ");
            }
            if (testaReais(palavra, 0, 0) == 0) {
                MyIO.print("SIM\n");
            } else {
                MyIO.print("NAO\n");
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

    public static int testaVogais(String palavra, int i) {

        if (i < palavra.length()) {
            if (palavra.charAt(i) == 'a' || palavra.charAt(i) == 'e' || palavra.charAt(i) == 'i'
                    || palavra.charAt(i) == 'o' || palavra.charAt(i) == 'u' || palavra.charAt(i) == 'A'
                    || palavra.charAt(i) == 'E' || palavra.charAt(i) == 'I' || palavra.charAt(i) == 'O'
                    || palavra.charAt(i) == 'U') {
                return 0 + testaVogais(palavra, i + 1);
            } else {
                return 1;
            }
        }

        return 0;
    }

    public static int testaConsoantes(String palavra, int i) {

        if (i < palavra.length()) {
            if (((palavra.charAt(i) >= 'B' && palavra.charAt(i) <= 'Z')
                    || (palavra.charAt(i) >= 'b' && palavra.charAt(i) <= 'z')) && palavra.charAt(i) != 'e'
                    && palavra.charAt(i) != 'i'
                    && palavra.charAt(i) != 'o' && palavra.charAt(i) != 'u' && palavra.charAt(i) != 'E'
                    && palavra.charAt(i) != 'I' && palavra.charAt(i) != 'O'
                    && palavra.charAt(i) != 'U') {
                return 0 + testaConsoantes(palavra, i + 1);
            } else {
                return 1;
            }
        }

        return 0;
    }

    public static int testaInteiros(String palavra, int i) {

        if (i < palavra.length()) {
            if (palavra.charAt(i) >= '0' && palavra.charAt(i) <= '9') {
                return 0 + testaInteiros(palavra, i + 1);
            } else {
                return 1;
            }
        }

        return 0;
    }

    public static int testaReais(String palavra, int i, int cont) {

        if (i < palavra.length()) {
            if (((palavra.charAt(i) >= '0' && palavra.charAt(i) <= '9') || palavra.charAt(i) == '.'
                    || palavra.charAt(i) == ',') && cont <= 1) {

                if (palavra.charAt(i) == '.'
                        || palavra.charAt(i) == ',') {
                    return 0 + testaReais(palavra, i + 1, cont + 1); // Garantir que números com mais de um separador
                                                                     // não sejam considerados
                }
                return 0 + testaReais(palavra, i + 1, cont + 0);
            } else {
                return 1;
            }
        }

        return 0;
    }
}
