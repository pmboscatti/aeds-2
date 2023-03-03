#include <stdbool.h>
#include <stdio.h>
#include <string.h>

bool testaFim(char palavra[])
{
  bool teste = false;

  if (palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M')
  {
    teste = true;
  }

  return teste;
}

int conferePalindromo(char palavra[], int esq, int dir)
{
  int confere = 0;

  // Testa a posição do início da string com o final
  while (esq <= dir)
  {
    if (palavra[esq] == palavra[dir])
    {
      return 0 + conferePalindromo(palavra, esq + 1, dir - 1); // Valor se mantém zerado em caso palíndromo
    }
    else
    {
      return 1;
    }
  }

  return confere;
}

int main(void)
{
  char palavra[800];

  scanf("%[^\n]s", palavra);
  getchar();

  // Testa o fim do arquivo
  while (testaFim(palavra) == false)
  {
    if (conferePalindromo(palavra, 0, (strlen(palavra) - 1)) == 0)
    {
      printf("SIM\n");
    }
    else
    {
      printf("NAO\n");
    }

    scanf("%[^\n]s", palavra);
    getchar();
  }

  return 0;
}