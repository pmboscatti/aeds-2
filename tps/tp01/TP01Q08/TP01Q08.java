import java.io.*;
import java.net.*;

public class TP01Q08 {
    public static String getHtml(String endereco) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;

        try {
            url = new URL(endereco);
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                resp += line + "\n";
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException ioe) {
            // Vazio
        }

        return resp;
    }

    public static void main(String[] args) {
        MyIO.setCharset("ISO-8859-1");

        String nomePagina = MyIO.readLine();

        // Testa o fim do arquivo
        while (testaFim(nomePagina) == false) {
            String endereco = MyIO.readLine();
            String html = getHtml(endereco);

            // Zera as variáveis
            int aNormal = 0, eNormal = 0, iNormal = 0, oNormal = 0, uNormal = 0;
            int aAgudo = 0, eAgudo = 0, iAgudo = 0, oAgudo = 0, uAgudo = 0;
            int aCrase = 0, eCrase = 0, iCrase = 0, oCrase = 0, uCrase = 0;
            int aTil = 0, oTil = 0;
            int aCircunflexo = 0, eCircunflexo = 0, iCircunflexo = 0, oCircunflexo = 0, uCircunflexo = 0;
            int consoante = 0, br = 0, table = 0;

            for (int i = 0; i < html.length(); i++) {
                // Contagem de caracteres
                switch (html.charAt(i)) {
                    case 'a':
                        aNormal++;
                        break;
                    case 'e':
                        eNormal++;
                        break;
                    case 'i':
                        iNormal++;
                        break;
                    case 'o':
                        oNormal++;
                        break;
                    case 'u':
                        uNormal++;
                        break;
                    case '\u00e1':
                        aAgudo++;
                        break;
                    case '\u00e9':
                        eAgudo++;
                        break;
                    case '\u00ed':
                        iAgudo++;
                        break;
                    case '\u00f3':
                        oAgudo++;
                        break;
                    case '\u00fa':
                        uAgudo++;
                        break;
                    case '\u00e0':
                        aCrase++;
                        break;
                    case '\u00e8':
                        eCrase++;
                        break;
                    case '\u00ec':
                        iCrase++;
                        break;
                    case '\u00f2':
                        oCrase++;
                        break;
                    case '\u00f9':
                        uCrase++;
                        break;
                    case '\u00e3':
                        aTil++;
                        break;
                    case '\u00f5':
                        oTil++;
                        break;
                    case '\u00e2':
                        aCircunflexo++;
                        break;
                    case '\u00ea':
                        eCircunflexo++;
                        break;
                    case '\u00ee':
                        iCircunflexo++;
                        break;
                    case '\u00f4':
                        oCircunflexo++;
                        break;
                    case '\u00fb':
                        uCircunflexo++;
                        break;
                    // Caso o símbolo de tag apareça
                    case '<':
                        if (isTable(html, i) == true) {
                            i += 6;
                            table++;
                        } else if (isBr(html, i) == true) {
                            i += 3;
                            br++;
                        }
                        break;
                    default:
                        if (isConsoante(html.charAt(i)) == true) {
                            consoante++;
                        }
                        break;
                }
            }

            // Exibe os resultados
            MyIO.println("a(" + aNormal + ") " + "e(" + eNormal + ") " + "i(" + iNormal + ") " + "o(" + oNormal + ") "
                    + "u(" + uNormal + ") " + "á(" + aAgudo + ") " + "é(" + eAgudo + ") " + "í(" + iAgudo
                    + ") " + "ó("
                    + oAgudo + ") " + "ú(" + uAgudo + ") " + "à(" + aCrase + ") " + "è(" + eCrase + ") "
                    + "ì(" + iCrase
                    + ") " + "ò(" + oCrase + ") " + "ù(" + uCrase + ") " + "ã(" + aTil + ") " + "õ("
                    + oTil + ") "
                    + "â("
                    + aCircunflexo + ") " + "ê(" + eCircunflexo + ") " + "î(" + iCircunflexo + ") "
                    + "ô("
                    + oCircunflexo
                    + ") " + "û(" + uCircunflexo + ") " + "consoante(" + consoante + ") " + "<br>(" + br + ") "
                    + "<table>("
                    + table + ") " + nomePagina);

            nomePagina = MyIO.readLine();
        }
    }

    public static boolean testaFim(String palavra) {
        boolean teste = false;

        if (palavra.equals("FIM")) {
            teste = true;
        }

        return teste;
    }

    public static boolean isConsoante(char letra) {
        boolean resp = false;

        if ((letra >= 'a' && letra <= 'z')
                && letra != 'a' && letra != 'e' && letra != 'i' && letra != 'o'
                && letra != 'u') {
            resp = true;
        }

        return resp;
    }

    public static boolean isTable(String html, int i) {
        boolean resposta = false;
        String fraseTeste = new String();

        // Transforma a string zerada na string recebida
        for (int n = i; i < (n + 7); i++) {
            fraseTeste += html.charAt(i);
        }

        if (fraseTeste.equals("<table>")) {
            resposta = true;
        }

        return resposta;
    }

    public static boolean isBr(String html, int i) {
        boolean resposta = false;
        String fraseTeste = new String();

        // Transforma a string zerada na string recebida
        for (int n = i; i < (n + 4); i++) {
            fraseTeste += html.charAt(i);
        }

        if (fraseTeste.equals("<br>")) {
            resposta = true;
        }

        return resposta;
    }
}