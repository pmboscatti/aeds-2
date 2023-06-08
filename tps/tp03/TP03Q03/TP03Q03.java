import java.io.*;
import java.lang.Math;

class Celula {
    public Personagem elemento; // Elemento inserido na Celula
    public Celula prox; // Aponta a Celula -> Prox

    public Celula() {
        this(new Personagem());
    }

    public Celula(Personagem elemento) {
        this.elemento = elemento;
        this.prox = null;
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

class Fila {
    private Celula primeiro;
    private Celula ultimo;

    int tamanhoFila = 6; // Exercício exigia fila de tamanho 5
    int primeiroContador = 0, ultimoContador = 0;

    public Fila() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserir(Personagem personagem) throws Exception {
        if (((ultimoContador + 1) % tamanhoFila) == primeiroContador) {
            remover(); // Remover item caso a fila esteja cheia
        }

        ultimo.prox = new Celula(personagem);
        ultimo = ultimo.prox;

        ultimoContador = (ultimoContador + 1) % tamanhoFila; // Análise de acordo com a Fila Circular padrão

        calculaMedia(); // Calcula a média das alturas
    }

    public Personagem remover() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover!");
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Personagem resp = primeiro.elemento;
        tmp.prox = null;
        tmp = null;

        primeiroContador = (primeiroContador + 1) % tamanhoFila;

        return resp;
    }

    // Exibir a média arredondada das alturas
    public void calculaMedia() {
        int contador = 0;
        double soma = 0;

        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            soma += i.elemento.getAltura();
            contador++;
        }

        MyIO.println((int) (Math.round(soma / contador))); // Garantir o arredondamento necessário
    }
}

public class TP03Q03 {
    public static void main(String[] args) throws Exception {
        MyIO.setCharset("ISO-8859-1");

        Fila listaPersonagens = new Fila();

        String caminhoArquivo = MyIO.readLine().replaceAll("é", "\u00e9");

        // Testar fim da primeira parte do arquivo
        while (testaFim(caminhoArquivo) == false) {

            // Montar personagem e adicionar ao fim da lista
            listaPersonagens.inserir(montaPersonagem(caminhoArquivo));

            caminhoArquivo = MyIO.readLine().replaceAll("é", "\u00e9");
        }

        int quantidadeRegistros = MyIO.readInt(); // Ler quantidade de registros a serem inseridos ou removidos

        analisaOperacoes(quantidadeRegistros, listaPersonagens);
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

    // Analisar manipulações que devem ser feitas nos registros
    public static void analisaOperacoes(int quantidadeRegistros, Fila listaPersonagens) throws Exception {

        for (int i = 0; i < quantidadeRegistros; i++) {
            String comandoOperacao = MyIO.readString();
            String caminhoOperacao = new String();

            // Analisar o comando de cada operação a ser executada
            switch (comandoOperacao) {
                case "I":
                    caminhoOperacao = MyIO.readString().replaceAll("é", "\u00e9");
                    listaPersonagens.inserir(montaPersonagem(caminhoOperacao));
                    break;
                case "R":
                    MyIO.println("(R) " + listaPersonagens.remover().getNome());
                    break;
                default:
                    i = quantidadeRegistros; // Redundância para garantir o fim
                    break;
            }
        }
    }
}