import java.io.*;

class CelulaDupla {
    public Personagem elemento;
    public CelulaDupla ant;
    public CelulaDupla prox;

    public CelulaDupla() {
        this(new Personagem());
    }

    public CelulaDupla(Personagem elemento) {
        this.elemento = elemento;
        this.ant = this.prox = null;
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

class ListaDupla {
    private CelulaDupla primeiro;
    private CelulaDupla ultimo;

    int numeroComparacoes = 0, numeroMovimentacoes = 0;

    // Cria uma Lista Dupla sem elementos
    public ListaDupla() {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }

    // Insere um elemento na primeira posição da lista
    public void inserirInicio(Personagem personagem) {
        CelulaDupla tmp = new CelulaDupla(personagem);

        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        } else {
            tmp.prox.ant = tmp;
        }
        tmp = null;
    }

    // Insere um elemento na última posição da lista
    public void inserirFim(Personagem personagem) {
        ultimo.prox = new CelulaDupla(personagem);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    // Remove um elemento da primeira posição da lista
    public Personagem removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

        CelulaDupla tmp = primeiro;
        primeiro = primeiro.prox;
        Personagem resp = primeiro.elemento;
        tmp.prox = primeiro.ant = null;
        tmp = null;

        return resp;
    }

    // Remove um elemento da última posição da lista
    public Personagem removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }
        Personagem resp = ultimo.elemento;
        ultimo = ultimo.ant;
        ultimo.prox.ant = null;
        ultimo.prox = null;

        return resp;
    }

    // Insere um elemento em uma posição específica
    public void inserir(Personagem personagem, int pos) throws Exception {

        int tamanho = tamanho();

        if (pos < 0 || pos > tamanho) {
            throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
        } else if (pos == 0) {
            inserirInicio(personagem);
        } else if (pos == tamanho) {
            inserirFim(personagem);
        } else {
            // Caminhar até a posição anterior à inserção
            CelulaDupla i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            CelulaDupla tmp = new CelulaDupla(personagem);
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp.prox.ant = tmp;
            tmp = i = null;
        }
    }

    // Remove um elemento de uma posição específica
    public Personagem remover(int pos) throws Exception {
        Personagem resp;
        int tamanho = tamanho();

        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");

        } else if (pos < 0 || pos >= tamanho) {
            throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
        } else if (pos == 0) {
            resp = removerInicio();
        } else if (pos == tamanho - 1) {
            resp = removerFim();
        } else {
            // Caminhar até a posição anterior à inserção
            CelulaDupla i = primeiro.prox;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            i.ant.prox = i.prox;
            i.prox.ant = i.ant;
            resp = i.elemento;
            i.prox = i.ant = null;
            i = null;
        }

        return resp;
    }

    // Exibir os resultados
    public void mostrar() {
        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
            MyIO.print(" ## " + i.elemento.getNome());
            MyIO.print(" ## " + i.elemento.getAltura());
            if (i.elemento.getPeso() % 1 == 0) {
                MyIO.print(" ## " + (int) i.elemento.getPeso());
            } else {
                MyIO.print(" ## " + i.elemento.getPeso());
            }
            MyIO.print(" ## " + i.elemento.getCorDoCabelo());
            MyIO.print(" ## " + i.elemento.getCorDaPele());
            MyIO.print(" ## " + i.elemento.getCorDosOlhos());
            MyIO.print(" ## " + i.elemento.getAnoNascimento());
            MyIO.print(" ## " + i.elemento.getGenero());
            MyIO.print(" ## " + i.elemento.getHomeworld());
            MyIO.println(" ## ");
        }
    }

    // Exibir os resultados de forma invertida
    public void mostrarInverso() {
        for (CelulaDupla i = ultimo; i != primeiro; i = i.ant) {
            MyIO.print(" ## " + i.elemento.getNome());
            MyIO.print(" ## " + i.elemento.getAltura());
            if (i.elemento.getPeso() % 1 == 0) {
                MyIO.print(" ## " + (int) i.elemento.getPeso());
            } else {
                MyIO.print(" ## " + i.elemento.getPeso());
            }
            MyIO.print(" ## " + i.elemento.getCorDoCabelo());
            MyIO.print(" ## " + i.elemento.getCorDaPele());
            MyIO.print(" ## " + i.elemento.getCorDosOlhos());
            MyIO.print(" ## " + i.elemento.getAnoNascimento());
            MyIO.print(" ## " + i.elemento.getGenero());
            MyIO.print(" ## " + i.elemento.getHomeworld());
            MyIO.println(" ## ");
        }
    }

    // Pesquisa um elemento na lista
    public boolean pesquisar(Personagem x) {
        boolean resp = false;
        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
            if (i.elemento == x) {
                resp = true;
                i = ultimo;
            }
        }
        return resp;
    }

    // Calcula retorna o tamanho da lista
    public int tamanho() {
        int tamanho = 0;
        for (CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++)
            ;
        return tamanho;
    }

    // Função para trocar os elementos de lugar
    public void trocaElementos(CelulaDupla i, CelulaDupla j) {
        Personagem personagemTemporario = i.elemento;
        i.elemento = j.elemento;
        j.elemento = personagemTemporario;

        numeroMovimentacoes += 3;
    }

    // Função para Ordenação por Quicksort
    public void ordenaPorQuicksort() {
        ordenaPorQuicksort(primeiro.prox, ultimo, 0, (tamanho() - 1));
    }

    public void ordenaPorQuicksort(CelulaDupla esq, CelulaDupla dir, int esqInt, int dirInt) {
        CelulaDupla i = esq;
        CelulaDupla j = dir;
        CelulaDupla p = i.prox;

        int iCont = esqInt;
        int jCont = dirInt;

        Personagem pivo = p.elemento;

        while (iCont <= jCont) {
            while ((i.elemento.getCorDoCabelo().compareTo(pivo.getCorDoCabelo())) < 0) {
                i = i.prox;
                iCont++;
            }

            while ((j.elemento.getCorDoCabelo().compareTo(pivo.getCorDoCabelo())) > 0) {
                j = j.ant;
                jCont--;
            }

            if (iCont <= jCont) {
                trocaElementos(i, j);

                i = i.prox;
                iCont++;

                j = j.ant;
                jCont--;
            }
        }

        if (esqInt < jCont) {
            numeroComparacoes += 1;

            ordenaPorQuicksort(esq, j, esqInt, jCont);
        }
        if (iCont < dirInt) {
            numeroComparacoes += 1;

            ordenaPorQuicksort(i, dir, iCont, dirInt);
        }
    }

    // Função para Ordenação por Inserção
    public void ordenaPorInsercao() {
        for (CelulaDupla i = primeiro.prox.prox; i.prox != null; i = i.prox) {
            Personagem personagemTemporario = i.elemento;
            CelulaDupla j = i.ant;
            while ((j.ant != null) && ((j.elemento.getCorDoCabelo().equals(personagemTemporario.getCorDoCabelo()))
                    && (j.elemento.getNome().compareTo(personagemTemporario.getNome()) > 0))) {
                numeroComparacoes += 3;
                numeroMovimentacoes += 1;

                j.prox.elemento = j.elemento;
                j = j.ant;
            }

            j.prox.elemento = personagemTemporario;

            numeroMovimentacoes += 1;
        }
    }

    // Criar arquivo de log
    public void criarLog(long tempoInicial) {
        long tempoFinal = System.currentTimeMillis(); // Gravar o tempo do fim da execução

        Arq.openWrite("matricula_quicksort2.txt");
        Arq.println(
                "Matricula: 790052\t" + "Numero Comparacoes: " + numeroComparacoes + "\t" + "Numero de Movimentacoes: "
                        + numeroMovimentacoes + "\t" + "Tempo Execucao: " + (tempoFinal - tempoInicial) + "ms");
        Arq.close();
    }
}

public class TP03Q04 {
    public static void main(String[] args) throws Exception {
        MyIO.setCharset("ISO-8859-1");
        long tempoInicial = System.currentTimeMillis(); // Gravar tempo de início de execução

        ListaDupla listaPersonagens = new ListaDupla();

        String caminhoArquivo = MyIO.readLine().replaceAll("é", "\u00e9");

        // Testar fim da primeira parte do arquivo
        while (testaFim(caminhoArquivo) == false) {

            // Montar personagem e adicionar ao fim da lista
            listaPersonagens.inserirFim(montaPersonagem(caminhoArquivo));

            caminhoArquivo = MyIO.readLine().replaceAll("é", "\u00e9");
        }

        listaPersonagens.ordenaPorQuicksort(); // Ordena peso por Quicksort, mas não troca os nomes, por ser instável

        listaPersonagens.ordenaPorInsercao(); // Ordena cor do cabelo e nome por Inserção, por ser estável

        listaPersonagens.mostrar();

        listaPersonagens.criarLog(tempoInicial); // Passar o tempo de início da execução
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