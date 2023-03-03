public class TP01Q06 {
    public static void main(String[] args) {
        String palavra = MyIO.readLine();

        // Testa o fim do arquivo
        while (testaFim(palavra) == false) {
            if (testaVogais(palavra) == true) {
                MyIO.print("SIM ");
            } else {
                MyIO.print("NAO ");
            }
            if (testaConsoantes(palavra) == true) {
                MyIO.print("SIM ");
            } else {
                MyIO.print("NAO ");
            }
            if (testaInteiro(palavra) == true) {
                MyIO.print("SIM ");
            } else {
                MyIO.print("NAO ");
            }
            if (testaReal(palavra) == true) {
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

    public static boolean testaVogais(String palavra) {
        boolean teste = false;

        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) == 'a' || palavra.charAt(i) == 'A' || palavra.charAt(i) == 'e'
                    || palavra.charAt(i) == 'E' || palavra.charAt(i) == 'i' || palavra.charAt(i) == 'I'
                    || palavra.charAt(i) == 'o' || palavra.charAt(i) == 'O' || palavra.charAt(i) == 'u'
                    || palavra.charAt(i) == 'U') {
                teste = true;
            } else {
                teste = false;
                i = palavra.length();
            }
        }

        return teste;
    }

    public static boolean testaConsoantes(String palavra) {
        boolean teste = false;

        for (int i = 0; i < palavra.length(); i++) {
            if (((palavra.charAt(i) >= 'B' && palavra.charAt(i) <= 'Z')
                    || (palavra.charAt(i) >= 'b' && palavra.charAt(i) <= 'z')) && palavra.charAt(i) != 'a'
                    && palavra.charAt(i) != 'A' && palavra.charAt(i) != 'e'
                    && palavra.charAt(i) != 'E' && palavra.charAt(i) != 'i' && palavra.charAt(i) != 'I'
                    && palavra.charAt(i) != 'o' && palavra.charAt(i) != 'O' && palavra.charAt(i) != 'u'
                    && palavra.charAt(i) != 'U') {
                teste = true;
            } else {
                teste = false;
                i = palavra.length();
            }
        }

        return teste;
    }

    public static boolean testaInteiro(String palavra) {
        boolean teste = false;

        for (int i = 0; i < palavra.length(); i++) {
            if (Character.isDigit(palavra.charAt(i))) {
                teste = true;
            } else {
                teste = false;
                i = palavra.length();
            }
        }

        return teste;
    }

    public static boolean testaReal(String palavra) {
        int separador = 0;
        boolean teste = false;

        for (int i = 0; i < palavra.length(); i++) {
            if (Character.isDigit(palavra.charAt(i)) || palavra.charAt(i) == '.' || palavra.charAt(i) == ',') {
                if (palavra.charAt(i) == '.' || palavra.charAt(i) == ',') {
                    separador += 1;

                    // Garantir que números com mais de um separador não sejam considerados
                    if (separador > 1) {
                        teste = false;
                        i = palavra.length();
                    } else {
                        teste = true;
                    }
                } else {
                    teste = true;
                }
            } else {
                teste = false;
                i = palavra.length();
            }
        }

        return teste;
    }
}
