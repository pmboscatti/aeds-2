#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Definição do registro do personagem
typedef struct Personagem
{
    char nome[40];
    int altura;
    double peso;
    char corDoCabelo[40];
    char corDaPele[40];
    char corDosOlhos[40];
    char anoNascimento[40];
    char genero[40];
    char homeworld[40];
} Personagem;

// Função para retornar o resto da divisão com double
double fmod(double x, double y)
{
    return x - (int)(x / y) * y;
}

// Capturar o atributo entre aspas simples
void leituraAtributo(char atributo[], char descricaoPersonagem[], int index)
{
    int i = 0;

    while (descricaoPersonagem[index] != '\'')
    {
        atributo[i] = descricaoPersonagem[index];

        i++;
        index++;
    }

    atributo[i] = '\0';
}

// Imprimir os resultados
void imprimirAtributos(Personagem personagem)
{
    double peso = personagem.peso;

    // Consertar a exibição para garantir a mesma quantidade de casas decimais solicitadas
    if (fmod(peso, 1) == 0)
    {
        printf(" ## %s ## %d ## %.0lf ## %s ## %s ## %s ## %s ## %s ## %s ## \n", personagem.nome, personagem.altura, personagem.peso, personagem.corDoCabelo, personagem.corDaPele, personagem.corDosOlhos, personagem.anoNascimento, personagem.genero, personagem.homeworld);
    }
    else
    {
        printf(" ## %s ## %d ## %.1lf ## %s ## %s ## %s ## %s ## %s ## %s ## \n", personagem.nome, personagem.altura, personagem.peso, personagem.corDoCabelo, personagem.corDaPele, personagem.corDosOlhos, personagem.anoNascimento, personagem.genero, personagem.homeworld);
    }
}

// Função para testar o fim do arquivo
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
    char caminhoArquivo[100];

    scanf("%[^\n]s", caminhoArquivo);
    getchar();

    // Testa o fim do arquivo
    while (testaFim(caminhoArquivo) == false)
    {
        FILE *leitura = fopen(caminhoArquivo, "r");

        char descricaoPersonagem[1000];

        fscanf(leitura, "%[^\n]s", descricaoPersonagem);

        Personagem personagem; // Cria a variável struct

        int contador = 0;

        for (int i = 0; i < strlen(descricaoPersonagem); i++)
        {
            if (descricaoPersonagem[i] == ':')
            {
                char atributo[50];
                contador++;

                switch (contador)
                {
                case 1:
                    leituraAtributo(atributo, descricaoPersonagem, i + 3);
                    strcpy(personagem.nome, atributo);
                    break;
                case 2:
                    leituraAtributo(atributo, descricaoPersonagem, i + 3);
                    personagem.altura = atoi(atributo);
                    break;
                case 3:
                    leituraAtributo(atributo, descricaoPersonagem, i + 3);
                    for (int i = 0; i < strlen(atributo); i++)
                    {
                        if (atributo[i] == ',')
                        {
                            atributo[i] = atributo[i - 1];
                            atributo[i - 1] = '0';
                        }
                    }
                    personagem.peso = atof(atributo);
                    break;
                case 4:
                    leituraAtributo(atributo, descricaoPersonagem, i + 3);
                    strcpy(personagem.corDoCabelo, atributo);
                    break;
                case 5:
                    leituraAtributo(atributo, descricaoPersonagem, i + 3);
                    strcpy(personagem.corDaPele, atributo);
                    break;
                case 6:
                    leituraAtributo(atributo, descricaoPersonagem, i + 3);
                    strcpy(personagem.corDosOlhos, atributo);
                    break;
                case 7:
                    leituraAtributo(atributo, descricaoPersonagem, i + 3);
                    strcpy(personagem.anoNascimento, atributo);
                    break;
                case 8:
                    leituraAtributo(atributo, descricaoPersonagem, i + 3);
                    strcpy(personagem.genero, atributo);
                    break;
                case 9:
                    leituraAtributo(atributo, descricaoPersonagem, i + 3);
                    strcpy(personagem.homeworld, atributo);

                    i = strlen(descricaoPersonagem); // Encerra os ciclos de repetição desnecessários

                    imprimirAtributos(personagem); // Imprimir os resultados
                    break;
                default:
                    break;
                }
            }
        }

        fclose(leitura);

        scanf("%[^\n]s", caminhoArquivo);
        getchar();
    }

    return 0;
}