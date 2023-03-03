#include <stdio.h>

// Método para retornar o resto da divisão com double
double fmod(double x, double y)
{
  return x - (int)(x / y) * y;
}

void reverteArquivo(int posicao)
{
  FILE *leitura = fopen("arquivo.txt", "r");

  int numeroInteiro;
  double numeroReal;
  char quebraLinha;

  // Posiciona o ponteiro no final do arquivo
  fseek(leitura, 0, SEEK_END);

  // Pega o tamanho completo pegando desde o final
  int tamanhoArquivo = ftell(leitura);

  // Posiciona o ponteiro na posição recebida no parâmetro
  fseek(leitura, posicao, SEEK_SET);
  fscanf(leitura, "%lf", &numeroReal); // Pega o valor do número
  fseek(leitura, posicao, SEEK_SET);   // Retorna o ponteiro para a posição anterior

  for (int i = 0; i < tamanhoArquivo; i++) // Teste se existe quebra de linha
  {
    fscanf(leitura, "%c", &quebraLinha);
    posicao++;
    if (quebraLinha == '\n' || quebraLinha == EOF)
    {
      i = tamanhoArquivo;
    }
  }

  // Chama a recursão para começar a printar pelo primeiro
  if (posicao < tamanhoArquivo)
  {
    reverteArquivo(posicao);
  }

  if (numeroReal == (int)numeroReal)
  {
    numeroInteiro = (int)numeroReal;
    printf("%d\n", numeroInteiro);
  }
  else
  {
    // Consertar a exibição para garantir a mesma quantidade de casas decimais solicitadas
    if (fmod((numeroReal * 10), 1) == 0)
    {
      printf("%.1f\n", numeroReal);
    }
    else if (fmod((numeroReal * 100), 1) == 0)
    {
      printf("%.2f\n", numeroReal);
    }
    else
    {
      printf("%.3f\n", numeroReal);
    }
  }

  fclose(leitura);
}

int main(void)
{
  int n = 0;
  double numeroLido;

  scanf("%d", &n);

  FILE *acesso = fopen("arquivo.txt", "w");

  for (int i = 0; i < n; i++)
  {
    scanf("%lf", &numeroLido);
    fprintf(acesso, "%lf\n", numeroLido);
  }

  fclose(acesso);

  // Chama a função recursiva
  reverteArquivo(0);

  return 0;
}