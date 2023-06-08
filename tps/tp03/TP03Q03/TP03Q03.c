#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <math.h>

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

#define TAMANHOFILA 6 // Exercício exigia fila circular de cinco elementos

int primeiroContador = 0, ultimoContador = 0; // Controlar o tamanho da Fila Circular por contadores

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

// Criação da Fila
Celula *primeiro;
Celula *ultimo;

// Cria uma fila sem elementos
void start()
{
    Personagem personagem;

    primeiro = novaCelula(personagem);
    ultimo = primeiro;
}

// Exibir a média arredondada das alturas
void calculaMedia()
{
    int contador = 0, media;
    double soma = 0;

    for (Celula *i = primeiro->prox; i != NULL; i = i->prox)
    {
        soma += i->elemento.altura;
        contador++;
    }

    media = round(soma / contador);

    printf("%d\n", media);
}

// Remover elemento da fila (FIFO)
Personagem remover()
{
    if (primeiro == ultimo)
    {
        printf("Erro ao remover!");
    }
    Celula *tmp = primeiro;
    primeiro = primeiro->prox;
    Personagem resp = primeiro->elemento;
    tmp->prox = NULL;
    free(tmp);
    tmp = NULL;

    primeiroContador = (primeiroContador + 1) % TAMANHOFILA;

    return resp;
}

// Inserir elemento na fila (FIFO)
void inserir(Personagem personagem)
{
    if (((ultimoContador + 1) % TAMANHOFILA) == primeiroContador)
    {
        remover(); // Remover item caso a Fila esteja cheia
    }

    ultimo->prox = novaCelula(personagem);
    ultimo = ultimo->prox;

    ultimoContador = (ultimoContador + 1) % TAMANHOFILA; // Análise de acordo com a Fila Circular padrão

    calculaMedia(); // Calcula a média das alturas
}

// Exibir o resultado
void imprimeResultado()
{
    Celula *i;
    for (i = primeiro->prox; i != NULL; i = i->prox)
    {
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

    return 0;
}