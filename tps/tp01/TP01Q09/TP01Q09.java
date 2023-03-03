import java.io.*;

public class TP01Q09 {
    public static void main(String[] args) throws Exception {
        int n = MyIO.readInt();

        RandomAccessFile acesso = new RandomAccessFile("numeros.txt", "rw");

        double numeroLido;

        // Recebe os dados e escreve em um arquivo de texto
        for (int i = 0; i < n; i++) {
            numeroLido = MyIO.readDouble();

            acesso.writeDouble(numeroLido);
        }

        acesso.close();

        RandomAccessFile raf = new RandomAccessFile("numeros.txt", "r");

        int posicaoFim = (8 * (n - 1));

        double numeroEscrito;
        int numeroInteiro;

        // Posiciona o ponteiro no final do arquivo e subtrai a quantidade 8 bytes a
        // cada loop
        for (int i = posicaoFim; i >= 0; i = i - 8) {
            raf.seek(i);
            numeroEscrito = raf.readDouble();

            if (numeroEscrito % 1 == 0) {
                numeroInteiro = (int) (numeroEscrito);
                MyIO.println(numeroInteiro);
            } else {
                MyIO.println(numeroEscrito);
            }
        }
        raf.close();
    }
}