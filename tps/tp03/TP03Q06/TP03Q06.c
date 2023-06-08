#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

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

// Definição da Celula
typedef struct Celula
{
    Personagem elemento; // Elemento inserido na Celula
    struct Celula *prox; // Aponta para Celula -> Prox
} Celula;

Celula *novaCelula(Personagem elemento)
{
    Celula *nova = (Celula *)malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->prox = NULL;

    return nova;
}

// Criação da pilha
Celula *topo; // Sem Celula cabeça

// Cria uma fila sem elementos
void start()
{
    topo = NULL;
}

// Inserir elemento na pilha (FILO)
void inserir(Personagem personagem)
{
    Celula *tmp = novaCelula(personagem);
    tmp->prox = topo;
    topo = tmp;
    tmp = NULL;
}

// Remover elemento da pilha (FILO)
Personagem remover()
{
    if (topo == NULL)
    {
        printf("Erro ao remover!");
    }

    Personagem resp = topo->elemento;
    Celula *tmp = topo;
    topo = topo->prox;
    tmp->prox = NULL;
    free(tmp);
    tmp = NULL;

    return resp;
}

// Exibir os elementos da pilha de maneira inversa, utilizando recursão
int contadorPosicao = 0;

void mostrar(Celula *i)
{
    if (i != NULL)
    {
        mostrar(i->prox);
        printf("[%d] ", contadorPosicao);
        printf(" ## %s", i->elemento.nome);
        printf(" ## %d", i->elemento.altura);
        if (fmod(i->elemento.peso, 1) == 0)
            printf(" ## %.0lf", i->elemento.peso);
        else
            printf(" ## %.1lf", i->elemento.peso);
        printf(" ## %s", i->elemento.corDoCabelo);
        printf(" ## %s", i->elemento.corDaPele);
        printf(" ## %s", i->elemento.corDosOlhos);
        printf(" ## %s", i->elemento.anoNascimento);
        printf(" ## %s", i->elemento.genero);
        printf(" ## %s", i->elemento.homeworld);
        printf(" ## \n");
        contadorPosicao++;
    }
}

int main(void)
{
    char caminhoArquivo[100], nomePersonagem[100];
    int contadorTamanho = 0;
    start();

    scanf(" %[^\n]s", caminhoArquivo);
    getchar();

    // Testa o fim da primeira parte do arquivo
    while (testaFim(caminhoArquivo) == false)
    {
        inserir(montaPersonagem(caminhoArquivo));
        contadorTamanho += 1;

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

        char caminhoOperacao[100];

        // Analisar o comando de cada operação a ser executada
        if (comandoOperacao[0] == 'I') // Casos de inserção
        {
            scanf(" %[^\n]s", caminhoOperacao);
            getchar();

            inserir(montaPersonagem(caminhoOperacao));
        }
        else if (comandoOperacao[0] == 'R') // Casos de remoção
        {
            printf("(R) %s\n", remover().nome);
        }
    }

    mostrar(topo);

    return 0;
}