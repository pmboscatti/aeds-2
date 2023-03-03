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

int main(void)
{
  int palindromo;
  char palavra[100];

  scanf("%[^\n]s", palavra);
  getchar();

  // Testa o fim do arquivo
  while (testaFim(palavra) == false)
  {

    palindromo = 0;

    for (int i = 0, j = strlen(palavra) - 1; i < strlen(palavra); i++, j--)
    {
      if (palavra[i] == palavra[j])
      {
        palindromo += 0; // Mantém o valor zerado em caso palíndromo
      }
      else
      {
        palindromo += 1;
      }
    }

    if (palindromo == 0)
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