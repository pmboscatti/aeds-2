import java.io.*;

class No {
    public char elemento;
    public final int tamanho = 255;
    public No[] prox;
    public boolean folha;

    public No() {
        this(' ');
    }

    public No(char elemento) {
        this.elemento = elemento;
        prox = new No[tamanho];
        for (int i = 0; i < tamanho; i++)
            prox[i] = null;
        folha = false;
    }

    public static int hash(char x) {
        return (int) x;
    }
}

class ArvoreTrie {
    private No raiz;
    public int numeroComparacoes = 0;

    public ArvoreTrie() {
        raiz = new No();
    }

    public boolean pesquisar(String nome) throws Exception {
        return pesquisar(nome, raiz, 0);
    }

    public boolean pesquisar(String nome, No no, int i) throws Exception {
        boolean resp;
        if (no.prox[nome.charAt(i)] == null) {
            resp = false;
        } else if (i == nome.length() - 1) {
            resp = (no.prox[nome.charAt(i)].folha == true);
        } else if (i < nome.length() - 1) {
            resp = pesquisar(nome, no.prox[nome.charAt(i)], i + 1);
        } else {
            throw new Exception("Erro ao pesquisar!");
        }
        return resp;
    }

    public void inserir(Personagem personagem) throws Exception {
        inserir(personagem, raiz, 0);
    }

    private void inserir(Personagem personagem, No no, int i) throws Exception {

        if (no.prox[personagem.getNome().charAt(i)] == null) {
            no.prox[personagem.getNome().charAt(i)] = new No(personagem.getNome().charAt(i));

            numeroComparacoes++;

            if (i == personagem.getNome().length() - 1) {
                numeroComparacoes++;

                no.prox[personagem.getNome().charAt(i)].folha = true;
            } else {
                inserir(personagem, no.prox[personagem.getNome().charAt(i)], i + 1);
            }

        } else if (no.prox[personagem.getNome().charAt(i)].folha == false && i < personagem.getNome().length() - 1) {
            numeroComparacoes++;

            inserir(personagem, no.prox[personagem.getNome().charAt(i)], i + 1);

        } else {
            throw new Exception("Erro ao inserir!");
        }
    }

    // Função de inserir para ser usada na árvore de merge
    private void inserir(String s, No no, int i) throws Exception {
        if (no.prox[s.charAt(i)] == null) {
            no.prox[s.charAt(i)] = new No(s.charAt(i));

            numeroComparacoes++;

            if (i == s.length() - 1) {
                no.prox[s.charAt(i)].folha = true;

                numeroComparacoes++;
            } else {
                inserir(s, no.prox[s.charAt(i)], i + 1);
            }

        } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1) {
            numeroComparacoes++;

            inserir(s, no.prox[s.charAt(i)], i + 1);

        } else {
            throw new Exception("Erro ao inserir!");
        }
    }

    public void mostrar() {
        mostrar("", raiz);
    }

    public void mostrar(String s, No no) {
        if (no.folha == true) {
            System.out.println("Palavra: " + (s + no.elemento));
        } else {
            for (int i = 0; i < no.prox.length; i++) {
                if (no.prox[i] != null) {
                    mostrar(s + no.elemento, no.prox[i]);
                }
            }
        }
    }

    // Merge faz a chamada passando a primeira ou a segunda árvore
    public void inserirNaMerge(String s, ArvoreTrie arvore) throws Exception {
        inserirNaMerge("", arvore.raiz); // É passada a raiz da primeira ou da segunda árvore
    }

    // A palavra resultante inserida na árvore que o chamou
    public void inserirNaMerge(String s, No no) throws Exception {
        if (no.folha == true) {
            // Inserir na árvore que fez a chamada
            this.inserir((s + no.elemento).trim(), this.raiz, 0);
        } else {
            for (int i = 0; i < no.prox.length; i++) {
                if (no.prox[i] != null) {
                    numeroComparacoes++;

                    inserirNaMerge(s + no.elemento, no.prox[i]);
                }
            }
        }
    }

    // Criar arquivo de log
    public void criarLog(long tempoInicial, int comparacoes) {
        long tempoFinal = System.currentTimeMillis(); // Gravar o tempo do fim da execução

        Arq.openWrite("matricula_arvoreTrie.txt");
        Arq.println("Matricula: 790052\t" + "Numero Comparacoes: " + comparacoes + "\t" + "Tempo Execucao: "
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

    public Personagem(String nome, int altura, double peso, String corDoCabelo, String corDaPele, String corDosOlhos,
            String anoNascimento, String genero, String homeworld) {
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

public class TP04Q08 {
    public static void main(String[] args) throws Exception {
        MyIO.setCharset("ISO-8859-1");
        long tempoInicial = System.currentTimeMillis(); // Gravar tempo de início de execução

        ArvoreTrie arvorePrimeiraPersonagens = new ArvoreTrie(); // Criar primeira árvore
        ArvoreTrie arvoreSegundaPersonagens = new ArvoreTrie(); // Criar segunda árvore

        // Árvore que servirá como merge
        ArvoreTrie arvoreMergePersonagens = new ArvoreTrie();

        String caminhoArquivo = MyIO.readLine().replaceAll("é", "\u00e9");

        // Testar fim da primeira parte do arquivo
        while (testaFim(caminhoArquivo) == false) {

            // Montar personagem e inserir na árvore binária
            arvorePrimeiraPersonagens.inserir(montaPersonagem(caminhoArquivo));

            caminhoArquivo = MyIO.readLine().replaceAll("é", "\u00e9");
        }

        caminhoArquivo = MyIO.readLine().replaceAll("é", "\u00e9");

        // Testar fim da segunda parte do arquivo
        while (testaFim(caminhoArquivo) == false) {

            // Montar personagem e inserir na árvore binária
            arvoreSegundaPersonagens.inserir(montaPersonagem(caminhoArquivo));

            caminhoArquivo = MyIO.readLine().replaceAll("é", "\u00e9");
        }

        // Fazer o merge entre as duas árvores antes de utilizá-lo para pesquisar
        arvoreMergePersonagens.inserirNaMerge("", arvorePrimeiraPersonagens);
        arvoreMergePersonagens.inserirNaMerge("", arvoreSegundaPersonagens);

        // Armazenar os nomes que serão pesquisados
        String nomeConsultaPersonagem = MyIO.readLine().replaceAll("é", "\u00e9");

        // Testar fim da terceira parte do arquivo
        while (testaFim(nomeConsultaPersonagem) == false) {
            MyIO.print(nomeConsultaPersonagem + " ");

            if (arvoreMergePersonagens.pesquisar(nomeConsultaPersonagem)) {
                MyIO.println("SIM");
            } else {
                MyIO.println("NÃO");
            }

            nomeConsultaPersonagem = MyIO.readLine().replaceAll("é", "\u00e9");
        }

        int totalComparacoes = arvorePrimeiraPersonagens.numeroComparacoes + arvoreSegundaPersonagens.numeroComparacoes
                + arvoreMergePersonagens.numeroComparacoes;

        arvoreMergePersonagens.criarLog(tempoInicial, totalComparacoes); // Passar o tempo de início da execução
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