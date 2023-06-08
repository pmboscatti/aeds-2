import java.io.*;

class No {
    public int altura;
    public No esq;
    public No dir;
    public No2 outro;

    No(int altura) {
        this.altura = altura;
        this.esq = this.dir = null;
        this.outro = null;
    }

    No(int altura, No esq, No dir) {
        this.altura = altura;
        this.esq = esq;
        this.dir = dir;
        this.outro = null;
    }
}

class No2 {
    public Personagem elemento;
    public No2 esq;
    public No2 dir;

    No2(Personagem elemento) {
        this.elemento = elemento;
        this.esq = this.dir = null;
    }

    No2(Personagem elemento, No2 esq, No2 dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreArvore {
    private No raiz;
    int numeroComparacoes = 0;

    public ArvoreArvore() throws Exception {
        raiz = null;

        // Construir a árvore para comparar altura mod 15
        inserir(7);
        inserir(3);
        inserir(11);
        inserir(1);
        inserir(5);
        inserir(9);
        inserir(12);
        inserir(0);
        inserir(2);
        inserir(4);
        inserir(6);
        inserir(8);
        inserir(10);
        inserir(13);
        inserir(14);
    }

    public void inserir(int altura) throws Exception {
        raiz = inserir(altura, raiz);
    }

    private No inserir(int altura, No i) throws Exception {
        if (i == null) {
            i = new No(altura);

        } else if (altura < i.altura) {
            i.esq = inserir(altura, i.esq);

        } else if (altura > i.altura) {
            i.dir = inserir(altura, i.dir);

        } else {
            throw new Exception("Erro ao inserir!");
        }

        return i;
    }

    public void inserir(Personagem personagem) throws Exception {
        inserir(personagem, raiz);
    }

    public void inserir(Personagem personagem, No i) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao inserir: personagem invalido!");

        } else if ((personagem.getAltura() % 15) < i.altura) {
            numeroComparacoes++;

            inserir(personagem, i.esq);

        } else if ((personagem.getAltura() % 15) > i.altura) {
            numeroComparacoes++;

            inserir(personagem, i.dir);

        } else {
            i.outro = inserir(personagem, i.outro);
        }
    }

    private No2 inserir(Personagem personagem, No2 i) throws Exception {
        if (i == null) {
            i = new No2(personagem);

        } else if (personagem.getNome().compareTo(i.elemento.getNome()) < 0) {
            numeroComparacoes++;

            i.esq = inserir(personagem, i.esq);

        } else if (personagem.getNome().compareTo(i.elemento.getNome()) > 0) {
            numeroComparacoes++;

            i.dir = inserir(personagem, i.dir);
        } else {
            throw new Exception("Erro ao inserir: elemento existente!");
        }

        return i;
    }

    public boolean pesquisar(String nome) {
        return pesquisar(raiz, nome);
    }

    private boolean pesquisar(No no, String nome) {
        boolean resp = false;
        if (no == null) {
            resp = false;

        } else {
            // Faz a pesquisa no nó
            if (no.outro != null) {
                resp = pesquisarSegundaArvore(no.outro, nome);
            }

            // Caminha para a esquerda, caso não encontre
            if (resp == false) {
                MyIO.print("esq ");

                resp = pesquisar(no.esq, nome);
            }

            // Caminha para a direita, caso não encontre
            if (resp == false) {
                MyIO.print("dir ");

                resp = pesquisar(no.dir, nome);
            }
        }
        return resp;
    }

    private boolean pesquisarSegundaArvore(No2 no, String nome) {
        boolean resp;

        if (no == null) {
            resp = false;
        } else if (nome.compareTo(no.elemento.getNome()) == 0) {
            numeroComparacoes++;

            resp = true;
        } else {
            System.out.print("ESQ ");
            resp = pesquisarSegundaArvore(no.esq, nome);
            System.out.print("DIR ");
            resp = pesquisarSegundaArvore(no.dir, nome);
        }

        return resp;
    }

    // Criar arquivo de log
    public void criarLog(long tempoInicial) {
        long tempoFinal = System.currentTimeMillis(); // Gravar o tempo do fim da execução

        Arq.openWrite("matricula_arvoreArvore.txt");
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

public class TP04Q02 {
    public static void main(String[] args) throws Exception {
        MyIO.setCharset("ISO-8859-1");
        long tempoInicial = System.currentTimeMillis(); // Gravar tempo de início de execução

        ArvoreArvore arvorePersonagens = new ArvoreArvore();

        String caminhoArquivo = MyIO.readLine().replaceAll("é", "\u00e9");

        // Testar fim da primeira parte do arquivo
        while (testaFim(caminhoArquivo) == false) {

            // Montar personagem e inserir na árvore binária
            arvorePersonagens.inserir(montaPersonagem(caminhoArquivo));

            caminhoArquivo = MyIO.readLine().replaceAll("é", "\u00e9");
        }

        String nomeConsultaPersonagem = MyIO.readLine().replaceAll("é", "\u00e9");

        // Testar fim da segunda parte do arquivo
        while (testaFim(nomeConsultaPersonagem) == false) {
            // Montar o personagem para pesquisar pelo objeto com atributos

            MyIO.print(nomeConsultaPersonagem + " raiz ");

            if (arvorePersonagens.pesquisar(nomeConsultaPersonagem)) {
                MyIO.println("SIM");
            } else {
                MyIO.println("NÃO");
            }

            nomeConsultaPersonagem = MyIO.readLine().replaceAll("é", "\u00e9");
        }

        arvorePersonagens.criarLog(tempoInicial); // Passar o tempo de início da execução
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