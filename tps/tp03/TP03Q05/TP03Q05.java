class Celula {
    public int elemento;
    public Celula inf, sup, esq, dir;

    public Celula() {
        this(0);
    }

    public Celula(int elemento) {
        this(elemento, null, null, null, null);
    }

    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir) {
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;
    }
}

class Matriz {
    private Celula inicio;
    private int linha, coluna;

    public Matriz() {
        this(3, 3);
    }

    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;

        // Alocar as Celulas da matriz
        inicio = new Celula();
        Celula first = inicio;
        Celula tmp = inicio;
        Celula ctrl = inicio;

        for (int i = 0; i < linha; i++) {
            for (int j = 1; j < coluna; j++) {
                if (i == 0) {
                    first.inf = new Celula();
                    first.inf.sup = first;
                    first = first.inf;
                } else {
                    tmp.inf = new Celula();
                    tmp.inf.esq = first.inf;
                    first.inf.dir = tmp.inf;
                    tmp.inf.sup = tmp;
                    first = first.inf;
                    tmp = tmp.inf;
                }
            }

            first = ctrl;
            ctrl.dir = new Celula();
            tmp = ctrl.dir;
            tmp.esq = first;
            first.dir = tmp;
            ctrl = tmp;
        }
    }

    public Matriz preencherMatriz(int linha, int coluna) {
        Matriz x = new Matriz(linha, coluna);

        Celula tmp = x.inicio;
        Celula ctrl = tmp;

        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                int numero = MyIO.readInt();

                tmp.elemento = numero;
                tmp = tmp.dir;
            }

            ctrl = ctrl.inf;
            tmp = ctrl;
        }

        return x;
    }

    public void exibirMatriz(Matriz x) {
        Celula tmp = x.inicio;
        Celula ctrl = tmp;

        // Exibir os resultados
        for (int i = 0; i < x.linha; i++) {
            for (int j = 0; j < x.coluna; j++) {
                MyIO.print(tmp.elemento + " ");

                tmp = tmp.dir;
            }

            MyIO.println("");

            ctrl = ctrl.inf;
            tmp = ctrl;
        }
    }

    public Matriz soma(Matriz m) {
        Matriz resp = null;

        if (this.linha == m.linha && this.coluna == m.coluna) {
            resp = new Matriz(this.linha, this.coluna);

            Celula a = this.inicio;
            Celula b = m.inicio;
            Celula c = resp.inicio;

            Celula tmpA = a;
            Celula tmpB = b;
            Celula tmpC = c;

            for (int i = 0; i < this.linha; i++) {
                for (int j = 0; j < this.coluna; j++) {
                    c.elemento = a.elemento + b.elemento;

                    a = a.dir;
                    b = b.dir;
                    c = c.dir;
                }

                tmpA = tmpA.inf;
                tmpB = tmpB.inf;
                tmpC = tmpC.inf;
                a = tmpA;
                b = tmpB;
                c = tmpC;
            }
        }

        return resp;
    }

    public Matriz multiplicacao(Matriz m) {
        Matriz resp = null;

        if (this.coluna == m.linha) {
            resp = new Matriz(this.linha, m.coluna);

            Celula a = this.inicio;
            Celula b = m.inicio;
            Celula c = resp.inicio;

            Celula tmpA = a;
            Celula tmpB = b;
            Celula tmpC = c;

            for (int i = 0; i < this.linha; i++) {
                for (int j = 0; j < m.coluna; j++) {
                    int soma = 0; // Zerar o valor da soma

                    // Rodar até multiplicar todos os elementos da linha da primeira matriz
                    for (int k = 0; k < this.linha; k++) {
                        soma += a.elemento * b.elemento;

                        a = a.dir;
                        b = b.inf;
                    }

                    c.elemento = soma; // Recebe a soma das multiplicações
                    a = tmpA; // Primeira matriz volta para a linha inicial para recomeçar as multiplicações
                    tmpB = tmpB.dir;
                    b = tmpB; // Segunda matriz anda uma coluna para a direita
                    c = c.dir; // Matriz resultante se comporta normalmente
                }

                tmpA = tmpA.inf; // Desce após acabar todas as multiplicações da primeira linha
                tmpB = m.inicio; // Retorna ao início para ser completamente multiplicada pela nova linha
                tmpC = tmpC.inf; // Comporta-se normalmente
                a = tmpA;
                b = tmpB;
                c = tmpC;
            }
        }

        return resp;
    }

    public boolean isQuadrada() {
        return (this.linha == this.coluna);
    }

    public void mostrarDiagonalPrincipal(Matriz x) {
        if (isQuadrada() == true) {
            Celula tmp = x.inicio;

            for (int i = 1; i < x.linha; i++) {
                MyIO.print(tmp.elemento + " ");

                // Mover tmp para a diagonal inferior direita
                tmp = tmp.inf.dir;
            }

            MyIO.print(tmp.elemento + " ");
            MyIO.println("");
        } else {
            MyIO.println("A matriz não é quadrada!");
        }
    }

    public void mostrarDiagonalSecundaria(Matriz x) {
        if (isQuadrada() == true) {
            Celula tmp = x.inicio;

            // Caminhar com o tmp até a última célula superior direita
            for (int i = 1; i < x.coluna; i++) {
                tmp = tmp.dir;
            }

            for (int i = 1; i < x.linha; i++) {
                MyIO.print(tmp.elemento + " ");

                // Mover tmp para a diagonal inferior esquerda
                tmp = tmp.inf.esq;
            }

            MyIO.print(tmp.elemento + " ");
            MyIO.println("");
        } else {
            MyIO.println("A matriz não é quadrada!");
        }
    }
}

public class TP03Q05 {
    public static void main(String[] args) {
        Matriz baseMatriz = new Matriz();
        int casosTeste = MyIO.readInt();

        for (int i = 0; i < casosTeste; i++) {
            int linha = MyIO.readInt();
            int coluna = MyIO.readInt();

            Matriz a = baseMatriz.preencherMatriz(linha, coluna);

            linha = MyIO.readInt();
            coluna = MyIO.readInt();

            Matriz b = baseMatriz.preencherMatriz(linha, coluna);

            a.mostrarDiagonalPrincipal(a);
            a.mostrarDiagonalSecundaria(a);

            // Criar a matriz de soma
            Matriz s = a.soma(b);

            // Exibir a matriz de soma
            s.exibirMatriz(s);

            // Criar a matriz de multiplicação
            Matriz m = a.multiplicacao(b);

            // Exibir a matriz de soma
            m.exibirMatriz(m);
        }
    }
}
