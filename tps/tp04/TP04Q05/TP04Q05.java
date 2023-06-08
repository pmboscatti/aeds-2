import java.io.*;

class Hash {
    Personagem tabela[];
    int m1, m2, m, reserva;
    int numeroComparacoes = 0;

    public Hash() {
        this(21, 9);
    }

    public Hash(int m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
        this.m = m1 + m2;
        this.tabela = new Personagem[this.m];
        for (int i = 0; i < m1; i++) {
            tabela[i] = null;
        }
        reserva = 0;
    }

    public int h(Personagem elemento) {
        return elemento.getAltura() % m1;
    }

    public boolean inserir(Personagem elemento) {
        boolean resp = false;

        if (elemento != null) {
            int pos = h(elemento);
            if (tabela[pos] == null) {
                tabela[pos] = elemento;
                resp = true;
            } else if (reserva < m2) {
                tabela[m1 + reserva] = elemento;
                reserva++;
                resp = true;
            }
        }

        return resp;
    }

    public boolean pesquisar(String nome) {
        boolean resp = false;

        for (int i = 0; i < 30; i++) {
            if (tabela[i] != null) {
                if (tabela[i].getNome().equals(nome)) {
                    numeroComparacoes++;
                    resp = true;
                    i = 30;
                }
            }
        }

        return resp;
    }

    // Criar arquivo de log
    public void criarLog(long tempoInicial) {
        long tempoFinal = System.currentTimeMillis(); // Gravar o tempo do fim da execução

        Arq.openWrite("matricula_hashReserva.txt");
        Arq.println(
                "Matricula: 790052\t" + "Numero Comparacoes: " + numeroComparacoes + "\t" + "Tempo Execucao: "
                        + (tempoFinal - tempoInicial) + "ms");
        Arq.close();
    }
}

class Personagem {
    private String nome;
    private int altura;
    private double peso;
    private String corDoCabelo;
    private String corDaPele;
    private String corDosOlhos;
    private String anoNascimento;
    private String genero;
    private String homeworld;

    public Personagem() {
    }

    public Personagem(String nome, int altura, double peso, String corDoCabelo, String corDaPele,
            String corDosOlhos, String anoNascimento, String genero, String homeworld) {
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.corDoCabelo = corDoCabelo;
        this.corDaPele = corDaPele;
        this.corDosOlhos = corDosOlhos;
        this.anoNascimento = anoNascimento;
        this.genero = genero;
        this.homeworld = homeworld;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAltura() {
        return altura;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public void setCorDoCabelo(String corDoCabelo) {
        this.corDoCabelo = corDoCabelo;
    }

    public String getCorDoCabelo() {
        return corDoCabelo;
    }

    public void setCorDaPele(String corDaPele) {
        this.corDaPele = corDaPele;
    }

    public String getCorDaPele() {
        return corDaPele;
    }

    public void setCorDosOlhos(String corDosOlhos) {
        this.corDosOlhos = corDosOlhos;
    }

    public String getCorDosOlhos() {
        return corDosOlhos;
    }

    public String getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(String anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public Personagem clone() {
        Personagem cloned = new Personagem();

        cloned.nome = this.nome;
        cloned.altura = this.altura;
        cloned.peso = this.peso;
        cloned.corDoCabelo = this.corDoCabelo;
        cloned.corDaPele = this.corDaPele;
        cloned.corDosOlhos = this.corDosOlhos;
        cloned.anoNascimento = this.anoNascimento;
        cloned.genero = this.genero;
        cloned.homeworld = this.homeworld;

        return cloned;
    }
}

public class TP04Q05 {
    public static void main(String[] args) throws Exception {
        MyIO.setCharset("ISO-8859-1");
        long tempoInicial = System.currentTimeMillis(); // Gravar tempo de início de execução

        Hash hashPersonagens = new Hash();

        String caminhoArquivo = MyIO.readLine().replaceAll("é", "\u00e9");

        // Testar fim da primeira parte do arquivo
        while (testaFim(caminhoArquivo) == false) {

            // Montar personagem e inserir na árvore binária
            hashPersonagens.inserir(montaPersonagem(caminhoArquivo));

            caminhoArquivo = MyIO.readLine().replaceAll("é", "\u00e9");
        }

        String nomeConsultaPersonagem = MyIO.readLine().replaceAll("é", "\u00e9");

        // Testar fim da segunda parte do arquivo
        while (testaFim(nomeConsultaPersonagem) == false) {
            MyIO.print(nomeConsultaPersonagem);

            if (hashPersonagens.pesquisar(nomeConsultaPersonagem)) {
                MyIO.println(" SIM");
            } else {
                MyIO.println(" NÃO");
            }

            nomeConsultaPersonagem = MyIO.readLine().replaceAll("é", "\u00e9");
        }

        hashPersonagens.criarLog(tempoInicial); // Passar o tempo de início da execução
    }

    // Testar fim do arquivo
    public static boolean testaFim(String palavra) {
        boolean teste = false;

        if (palavra.equals("FIM")) {
            teste = true;
        }

        return teste;
    }

    // Retornar personagem lido
    public static Personagem montaPersonagem(String caminhoArquivo) throws Exception {
        RandomAccessFile leitura = new RandomAccessFile(caminhoArquivo, "r");

        String descricaoPersonagem = leitura.readLine().replaceAll("é", "\u00e9");

        Personagem personagem = new Personagem();
        int contadorDoisPontos = 0; // Contar separadores

        for (int i = 0; i < descricaoPersonagem.length(); i++) {
            if (descricaoPersonagem.charAt(i) == ':') {
                contadorDoisPontos++;

                switch (contadorDoisPontos) {
                    case 1:
                        personagem.setNome(leituraAtributo(descricaoPersonagem, i + 3));
                        break;
                    case 2:
                        String atributoInteiro = leituraAtributo(descricaoPersonagem, i + 3);
                        if (atributoInteiro.equals("unknown")) {
                            personagem.setAltura(0);
                        } else {
                            personagem.setAltura(Integer.parseInt(atributoInteiro));
                        }
                        break;
                    case 3:
                        String atributoDouble = leituraAtributo(descricaoPersonagem, i + 3).replaceAll(",", ".");
                        if (atributoDouble.equals("unknown")) {
                            personagem.setPeso(0);
                        } else {
                            personagem.setPeso(Double.parseDouble(atributoDouble));
                        }
                        break;
                    case 4:
                        personagem.setCorDoCabelo(leituraAtributo(descricaoPersonagem, i + 3));
                        break;
                    case 5:
                        personagem.setCorDaPele(leituraAtributo(descricaoPersonagem, i + 3));
                        break;
                    case 6:
                        personagem.setCorDosOlhos(leituraAtributo(descricaoPersonagem, i + 3));
                        break;
                    case 7:
                        personagem.setAnoNascimento(leituraAtributo(descricaoPersonagem, i + 3));
                        break;
                    case 8:
                        personagem.setGenero(leituraAtributo(descricaoPersonagem, i + 3));
                        break;
                    case 9:
                        personagem.setHomeworld(leituraAtributo(descricaoPersonagem, i + 3));
                        i = descricaoPersonagem.length(); // Encerra os ciclos de repetição desnecessários
                        break;
                    default:
                        break;
                }
            }
        }

        leitura.close();

        return personagem;
    }

    // Retornar valor do atributo do personagem
    public static String leituraAtributo(String descricaoPersonagem, int index) {
        String atributo = new String();

        while (descricaoPersonagem.charAt(index) != '\'') {
            atributo += descricaoPersonagem.charAt(index);

            index++;
        }

        return atributo;
    }
}