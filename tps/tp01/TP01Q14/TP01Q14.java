public class TP01Q14 {
    public static void main(String[] args) {
        int numVar = MyIO.readInt();
        int A = 0, B = 0, C = 0;
        String expressaoBooleana = new String();

        // Testa o fim do arquivo
        while (numVar != 0) {
            // Caso número lido indique duas variáveis
            if (numVar == 2) {
                A = MyIO.readInt();
                B = MyIO.readInt();

                // Limpar os espaços da expressão
                expressaoBooleana = MyIO.readLine().replaceAll(" ", "");

                // Colocar valores das variáveis recebidas nas variáveis da string
                if (A == 0) {
                    expressaoBooleana = expressaoBooleana.replaceAll("A", "0");
                } else {
                    expressaoBooleana = expressaoBooleana.replaceAll("A", "1");
                }
                if (B == 0) {
                    expressaoBooleana = expressaoBooleana.replaceAll("B", "0");
                } else {
                    expressaoBooleana = expressaoBooleana.replaceAll("B", "1");
                }
                // Caso número lido indique três variáveis
            } else if (numVar == 3) {
                A = MyIO.readInt();
                B = MyIO.readInt();
                C = MyIO.readInt();

                // Limpar os espaços da expressão
                expressaoBooleana = MyIO.readLine().replaceAll(" ", "");

                // Colocar valores das variáveis recebidas nas variáveis da string
                if (A == 0) {
                    expressaoBooleana = expressaoBooleana.replaceAll("A", "0");
                } else {
                    expressaoBooleana = expressaoBooleana.replaceAll("A", "1");
                }
                if (B == 0) {
                    expressaoBooleana = expressaoBooleana.replaceAll("B", "0");
                } else {
                    expressaoBooleana = expressaoBooleana.replaceAll("B", "1");
                }
                if (C == 0) {
                    expressaoBooleana = expressaoBooleana.replaceAll("C", "0");
                } else {
                    expressaoBooleana = expressaoBooleana.replaceAll("C", "1");
                }
            }

            // Chama método que calcula a expressão
            calculaAlgebra(expressaoBooleana);

            numVar = MyIO.readInt();
        }
    }

    public static void calculaAlgebra(String expressao) {
        int parenteseAbre = 0, parenteseFecha = 0;

        if (expressao.length() > 1) {
            for (int i = 0; i < expressao.length(); i++) {
                if (expressao.charAt(i) == '(') {
                    parenteseAbre = i;
                }
                if (expressao.charAt(i) == ')') {
                    parenteseFecha = i;
                    i = expressao.length();
                }
            }

            // Chamar a função dependendo do caso
            if (expressao.charAt(parenteseAbre - 1) == 'd') {
                calculaAnd(expressao, parenteseAbre, parenteseFecha);
            } else if (expressao.charAt(parenteseAbre - 1) == 'r') {
                calculaOr(expressao, parenteseAbre, parenteseFecha);
            } else if (expressao.charAt(parenteseAbre - 1) == 't') {
                calculaNot(expressao, parenteseAbre, parenteseFecha);
            }
        } else {
            MyIO.println(expressao); // Exibir na tela quando a expressão for apenas um número
        }
    }

    public static void calculaNot(String expressao, int parAbre, int parFecha) {
        String expressaoLimpa = new String();

        // Trocar os valores e limpar a expressão
        for (int i = 0; i < expressao.length(); i++) {
            if (i == (parAbre - 3)) {
                if (expressao.charAt(parAbre + 1) == '0') {
                    expressaoLimpa += '1';
                } else {
                    expressaoLimpa += '0';
                }
            } else if (i > (parAbre - 3) && i <= parFecha) {
                expressaoLimpa += "";
            } else {
                expressaoLimpa += expressao.charAt(i);
            }
        }

        // Chama a função original com a nova expressão gerada
        calculaAlgebra(expressaoLimpa);
    }

    public static void calculaAnd(String expressao, int parAbre, int parFecha) {
        String expressaoLimpa = new String();

        // Trocar os valores e limpar a expressão
        for (int i = 0; i < expressao.length(); i++) {
            if (i == (parAbre - 3)) {
                // Redundância na localização do termo para abranger duas ou três variáveis
                if (expressao.charAt(parAbre + 1) == '1' && expressao.charAt(parAbre + 3) == '1'
                        && expressao.charAt(parFecha - 1) == '1') {
                    expressaoLimpa += '1';
                } else {
                    expressaoLimpa += '0';
                }
            } else if (i > (parAbre - 3) && i <= parFecha) {
                expressaoLimpa += "";
            } else {
                expressaoLimpa += expressao.charAt(i);
            }
        }

        // Chama a função original com a nova expressão gerada
        calculaAlgebra(expressaoLimpa);
    }

    public static void calculaOr(String expressao, int parAbre, int parFecha) {
        String expressaoLimpa = new String();

        // Trocar os valores e limpar a expressão
        for (int i = 0; i < expressao.length(); i++) {
            if (i == (parAbre - 2)) {
                // Redundância na localização do termo para abranger duas ou três variáveis
                if (expressao.charAt(parAbre + 1) == '1' || expressao.charAt(parAbre + 3) == '1'
                        || expressao.charAt(parFecha - 1) == '1') {
                    expressaoLimpa += '1';
                } else {
                    expressaoLimpa += '0';
                }
            } else if (i > (parAbre - 2) && i <= parFecha) {
                expressaoLimpa += "";
            } else {
                expressaoLimpa += expressao.charAt(i);
            }
        }

        // Chama a função original com a nova expressão gerada
        calculaAlgebra(expressaoLimpa);
    }
}