public class TP01Q05 {
    public static void main(String[] args) {
        int numVar = MyIO.readInt();
        int A = 0, B = 0, C = 0;
        int parenteseAbre = 0, parenteseFecha = 0;
        String expressaoBooleana = new String();
        String expressaoLimpa = new String();

        // Testa o fim do arquivo
        while (numVar != 0) {
            // Caso o primeiro número seja de duas variáveis
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
                // Caso o primeiro número seja de três variáveis
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

            // Enquanto a expressão não tiver somente o resultado
            while (expressaoBooleana.length() > 1) {
                for (int i = 0; i < expressaoBooleana.length(); i++) {
                    if (expressaoBooleana.charAt(i) == '(') {
                        parenteseAbre = i;
                    }
                    if (expressaoBooleana.charAt(i) == ')') {
                        parenteseFecha = i;
                        i = expressaoBooleana.length();
                    }
                }

                // Chamar a função dependendo do caso
                if (expressaoBooleana.charAt(parenteseAbre - 1) == 'd') {

                    // Calcula caso 'and'
                    expressaoLimpa = new String();

                    // Trocar os valores e limpar a expressão
                    for (int i = 0; i < expressaoBooleana.length(); i++) {
                        if (i == (parenteseAbre - 3)) {
                            // Redundância na localização do termo para abranger duas ou três variáveis
                            if (expressaoBooleana.charAt(parenteseAbre + 1) == '1'
                                    && expressaoBooleana.charAt(parenteseAbre + 3) == '1'
                                    && expressaoBooleana.charAt(parenteseFecha - 1) == '1') {
                                expressaoLimpa += '1';
                            } else {
                                expressaoLimpa += '0';
                            }
                        } else if (i > (parenteseAbre - 3) && i <= parenteseFecha) {
                            expressaoLimpa += "";
                        } else {
                            expressaoLimpa += expressaoBooleana.charAt(i);
                        }
                    }
                } else if (expressaoBooleana.charAt(parenteseAbre - 1) == 'r') {

                    // Calcula caso 'or'
                    expressaoLimpa = new String();

                    // Trocar os valores e limpar a expressão
                    for (int i = 0; i < expressaoBooleana.length(); i++) {
                        if (i == (parenteseAbre - 2)) {
                            // Redundância na localização do termo para abranger duas ou três variáveis
                            if (expressaoBooleana.charAt(parenteseAbre + 1) == '1'
                                    || expressaoBooleana.charAt(parenteseAbre + 3) == '1'
                                    || expressaoBooleana.charAt(parenteseFecha - 1) == '1') {
                                expressaoLimpa += '1';
                            } else {
                                expressaoLimpa += '0';
                            }
                        } else if (i > (parenteseAbre - 2) && i <= parenteseFecha) {
                            expressaoLimpa += "";
                        } else {
                            expressaoLimpa += expressaoBooleana.charAt(i);
                        }
                    }

                } else if (expressaoBooleana.charAt(parenteseAbre - 1) == 't') {

                    // Calcula 'not'
                    expressaoLimpa = new String();

                    // Trocar os valores e limpar a expressão
                    for (int i = 0; i < expressaoBooleana.length(); i++) {
                        if (i == (parenteseAbre - 3)) {
                            if (expressaoBooleana.charAt(parenteseAbre + 1) == '0') {
                                expressaoLimpa += '1';
                            } else {
                                expressaoLimpa += '0';
                            }
                        } else if (i > (parenteseAbre - 3) && i <= parenteseFecha) {
                            expressaoLimpa += "";
                        } else {
                            expressaoLimpa += expressaoBooleana.charAt(i);
                        }
                    }
                }

                expressaoBooleana = expressaoLimpa;
            }

            // Exibe o resultado
            MyIO.println(expressaoBooleana);

            numVar = MyIO.readInt();
        }
    }
}