import java.util.*;

class Duende {
    private String nome;
    private int idade;

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setNome(String nome) {
        if (nome.length() <= 20) {
            this.nome = nome;
        }
    }

    public void setIdade(int idade) {
        if (idade >= 10 && idade <= 100) {
            this.idade = idade;
        }
    }

    public Duende montaDuende(String nome, int idade) {
        Duende duende = new Duende();

        duende.setNome(nome);
        duende.setIdade(idade);

        return duende;
    }
}

public class BEE3176 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numeroDuendes = input.nextInt();
        int numeroEquipes = numeroDuendes / 3;

        Duende[] listaDuendes = new Duende[numeroDuendes];

        // Armazenar a lista de duendes
        for (int i = 0; i < numeroDuendes; i++) {
            Duende duende = new Duende();

            String nome = input.next();
            int idade = input.nextInt();

            listaDuendes[i] = duende.montaDuende(nome, idade);
        }

        // Ordenar os duendes pelo nome e idade
        for (int posicaoFixa = 0; posicaoFixa < (numeroDuendes - 1); posicaoFixa++) {
            int posicaoMaior = posicaoFixa;

            for (int i = (posicaoFixa + 1); i < numeroDuendes; i++) {
                if (listaDuendes[i].getIdade() >= listaDuendes[posicaoMaior].getIdade()) {
                    if (listaDuendes[i].getIdade() == listaDuendes[posicaoMaior].getIdade()
                            && ((listaDuendes[i].getNome()).compareTo(listaDuendes[posicaoMaior].getNome()) > 0)) {
                        posicaoMaior = i;
                    } else {
                        posicaoMaior = i;
                    }
                }
            }

            if (posicaoFixa != posicaoMaior) {
                Duende duendeTemporario = listaDuendes[posicaoFixa];
                listaDuendes[posicaoFixa] = listaDuendes[posicaoMaior];
                listaDuendes[posicaoMaior] = duendeTemporario;
            }
        }

        for (int i = 0; i < numeroEquipes; i++) {
            System.out.println("Time " + (i + 1));
            System.out.println(listaDuendes[i].getNome() + " " + listaDuendes[i].getIdade());
            System.out.println(listaDuendes[i + (numeroEquipes * 1)].getNome() + " "
                    + listaDuendes[i + (numeroEquipes * 1)].getIdade());
            System.out.println(listaDuendes[i + (numeroEquipes * 2)].getNome() + " "
                    + listaDuendes[i + (numeroEquipes * 2)].getIdade());
            System.out.println("");
        }

        input.close();
    }
}