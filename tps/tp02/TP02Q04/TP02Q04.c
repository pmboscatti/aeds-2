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
void imprimirAtributos(Personagem listaPersonagens[], int *ptrTamanho)
{
    int tamanhoTotal = *ptrTamanho;

    for (int i = 0; i < tamanhoTotal; i++)
    {
        printf("[%d]  ## %s", i, listaPersonagens[i].nome);
        printf(" ## %d", listaPersonagens[i].altura);
        if (fmod(listaPersonagens[i].peso, 1) == 0)
            printf(" ## %.0lf", listaPersonagens[i].peso);
        else
            printf(" ## %.1lf", listaPersonagens[i].peso);
        printf(" ## %s", listaPersonagens[i].corDoCabelo);
        printf(" ## %s", listaPersonagens[i].corDaPele);
        printf(" ## %s", listaPersonagens[i].corDosOlhos);
        printf(" ## %s", listaPersonagens[i].anoNascimento);
        printf(" ## %s", listaPersonagens[i].genero);
        printf(" ## %s", listaPersonagens[i].homeworld);
        printf(" ## \n");
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

// Função para estruturar o personagem
Personagem montaPersonagem(char caminhoArquivo[])
{
    FILE *leitura = fopen(caminhoArquivo, "r");

    char descricaoPersonagem[1000];

    fscanf(leitura, " %[^\n]s", descricaoPersonagem);

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
                break;
            default:
                break;
            }
        }
    }

    fclose(leitura);

    return personagem;
}

// Inserir registro na primeira posição
void inserirInicio(Personagem listaPersonagens[], Personagem personagem, int *contador)
{
    int index = *contador;

    for (int i = index; i > 0; i--)
    {
        listaPersonagens[i] = listaPersonagens[i - 1];
    }

    listaPersonagens[0] = personagem;

    *contador += 1; // Incrementar o tamanho do vetor
}

// Inserir registro em uma posição qualquer e remanejar os demais registros
void inserir(Personagem listaPersonagens[], Personagem personagem, int posicao, int *contador)
{
    int index = *contador;

    for (int i = index; i > posicao; i--)
    {
        listaPersonagens[i] = listaPersonagens[i - 1];
    }

    listaPersonagens[posicao] = personagem;

    *contador += 1; // Incrementar o tamanho do vetor
}

// Inserir registro na última posição
void inserirFim(Personagem listaPersonagens[], Personagem personagem, int *contador)
{
    int index = *contador;

    listaPersonagens[index] = personagem;

    *contador += 1; // Incrementar o tamanho do vetor
}

// Remover registro na primeira posição
void removerInicio(Personagem listaPersonagens[], int *contador)
{
    printf("(R) %s\n", listaPersonagens[0].nome);

    *contador -= 1; // Reduzir o tamanho do vetor

    int index = *contador;

    for (int i = 0; i < index; i++)
    {
        listaPersonagens[i] = listaPersonagens[i + 1];
    }
}

// Remover registro em uma posição qualquer e remanejar os demais registros
void remover(Personagem listaPersonagens[], int posicao, int *contador)
{
    printf("(R) %s\n", listaPersonagens[posicao].nome);

    *contador -= 1; // Reduzir o tamanho do vetor

    int index = *contador;

    for (int i = posicao; i < index; i++)
    {
        listaPersonagens[i] = listaPersonagens[i + 1];
    }
}

// Remover registro na última posição
void removerFim(Personagem listaPersonagens[], int *contador)
{
    *contador -= 1; // Reduzir o tamanho do vetor

    int index = *contador;

    printf("(R) %s\n", listaPersonagens[index].nome);
}

int main(void)
{
    char caminhoArquivo[100];
    int i = 0, contadorTamanho = 0;
    int *ptrContador = &contadorTamanho;
    Personagem listaPersonagem[100];

    scanf(" %[^\n]s", caminhoArquivo);
    getchar();

    // Testa o fim do arquivo
    while (testaFim(caminhoArquivo) == false)
    {
        listaPersonagem[i] = montaPersonagem(caminhoArquivo);
        i++;
        *ptrContador += 1;

        scanf(" %[^\n]s", caminhoArquivo);
        getchar();
    }

    int quantidadeRegistros;
    scanf("%d", &quantidadeRegistros);

    // Analisar manipulações que devem ser feitas nos registros
    for (int i = 0; i < quantidadeRegistros; i++)
    {
        char comandoOperacao[5];
        scanf(" %s", comandoOperacao);
        getchar();

        int numeroOperacao;
        char caminhoOperacao[100];

        // Analisar o comando de cada operação a ser executada
        if (comandoOperacao[0] == 'I' && comandoOperacao[1] == 'I') // Casos de inserção
        {
            scanf(" %[^\n]s", caminhoOperacao);
            getchar();

            inserirInicio(listaPersonagem, montaPersonagem(caminhoOperacao), ptrContador);
        }
        else if (comandoOperacao[0] == 'I' && comandoOperacao[1] == '*')
        {
            scanf("%d", &numeroOperacao);
            scanf(" %[^\n]s", caminhoOperacao);
            getchar();

            inserir(listaPersonagem, montaPersonagem(caminhoOperacao), numeroOperacao, ptrContador);
        }
        else if (comandoOperacao[0] == 'I' && comandoOperacao[1] == 'F')
        {
            scanf(" %[^\n]s", caminhoOperacao);
            getchar();

            inserirFim(listaPersonagem, montaPersonagem(caminhoOperacao), ptrContador);
        }
        else if (comandoOperacao[0] == 'R' && comandoOperacao[1] == 'I') // Casos de remoção
        {
            removerInicio(listaPersonagem, ptrContador);
        }
        else if (comandoOperacao[0] == 'R' && comandoOperacao[1] == '*')
        {
            scanf("%d", &numeroOperacao);

            remover(listaPersonagem, numeroOperacao, ptrContador);
        }
        else if (comandoOperacao[0] == 'R' && comandoOperacao[1] == 'F')
        {
            removerFim(listaPersonagem, ptrContador);
        }
    }

    imprimirAtributos(listaPersonagem, ptrContador); // Imprimir os resultados

    return 0;
}