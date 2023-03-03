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

bool testaVogais(char palavra[])
{
  bool teste = false;

  for (int i = 0; i < strlen(palavra); i++)
  {
    if (palavra[i] == 'a' || palavra[i] == 'A' || palavra[i] == 'e' ||
        palavra[i] == 'E' || palavra[i] == 'i' || palavra[i] == 'I' ||
        palavra[i] == 'o' || palavra[i] == 'O' || palavra[i] == 'u' ||
        palavra[i] == 'U')
    {
      teste = true;
    }
    else
    {
      teste = false;
      i = strlen(palavra);
    }
  }

  return teste;
}

bool testaConsoantes(char palavra[])
{
  bool teste = false;

  for (int i = 0; i < strlen(palavra); i++)
  {
    if (((palavra[i] >= 'B' && palavra[i] <= 'Z') ||
         (palavra[i] >= 'b' && palavra[i] <= 'z')) &&
        palavra[i] != 'a' && palavra[i] != 'A' && palavra[i] != 'e' &&
        palavra[i] != 'E' && palavra[i] != 'i' && palavra[i] != 'I' &&
        palavra[i] != 'o' && palavra[i] != 'O' && palavra[i] != 'u' &&
        palavra[i] != 'U')
    {
      teste = true;
    }
    else
    {
      teste = false;
      i = strlen(palavra);
    }
  }

  return teste;
}

bool testaInteiros(char palavra[])
{
  bool teste = false;

  for (int i = 0; i < strlen(palavra); i++)
  {
    if (palavra[i] >= '0' && palavra[i] <= '9')
    {
      teste = true;
    }
    else
    {
      teste = false;
      i = strlen(palavra);
    }
  }

  return teste;
}

bool testaReais(char palavra[])
{
  int separador = 0;
  bool teste = false;

  // Garantir que números com mais de um separador não sejam considerados
  for (int i = 0; i < strlen(palavra); i++)
  {
    if ((palavra[i] >= '0' && palavra[i] <= '9') || palavra[i] == '.' ||
        palavra[i] == ',')
    {
      if (palavra[i] == '.' || palavra[i] == ',')
      {
        separador++;

        if (separador > 1)
        {
          teste = false;
          i = strlen(palavra);
        }
        else
        {
          teste = true;
        }
      }
      else
      {
        teste = true;
      }
    }
    else
    {
      teste = false;
      i = strlen(palavra);
    }
  }

  return teste;
}

int main(void)
{
  char palavra[800];

  scanf("%[^\n]s", palavra);
  getchar();

  // Testa o fim do arquivo
  while (testaFim(palavra) == false)
  {
    if (testaVogais(palavra) == true)
    {
      printf("SIM ");
    }
    else
    {
      printf("NAO ");
    }
    if (testaConsoantes(palavra) == true)
    {
      printf("SIM ");
    }
    else
    {
      printf("NAO ");
    }
    if (testaInteiros(palavra) == true)
    {
      printf("SIM ");
    }
    else
    {
      printf("NAO ");
    }
    if (testaReais(palavra) == true)
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