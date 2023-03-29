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

////////////////////////////////////////////////////////////////////////////

// Trocar os personagens de lugar
void swapPersonagem(Personagem listaPersonagens[], int i, int j, int *ptrMov)
{
    Personagem personagemTemporario = listaPersonagens[i];
    listaPersonagens[i] = listaPersonagens[j];
    listaPersonagens[j] = personagemTemporario;

    *ptrMov += 3;
}

// Construir o Heap
void construirHeap(Personagem listaPersonagens[], int tam, int *ptrComp, int *ptrMov)
{
    for (int i = tam; i > 1 && (listaPersonagens[i].altura > listaPersonagens[i / 2].altura); i /= 2)
    {
        swapPersonagem(listaPersonagens, i, i / 2, ptrMov);

        *ptrComp += 2;
    }
}

// Função para retornar o maior filho
int getMaiorFilho(Personagem listaPersonagens[], int i, int tam, int *ptrComp)
{
    int filho;
    if (2 * i == tam || listaPersonagens[2 * i].altura > listaPersonagens[2 * i + 1].altura)
    {
        filho = 2 * i;
    }
    else
    {
        filho = 2 * i + 1;
    }

    *ptrComp += 2;

    return filho;
}

// Função para testar se há um filho
bool hasFilho(int i, int tam, int *ptrComp)
{
    *ptrComp += 1;

    return (i <= (tam / 2));
}

// Reconstruir o Heap
void reconstruirHeap(Personagem listaPersonagens[], int tam, int *ptrComp, int *ptrMov)
{
    int i = 1;
    while (hasFilho(i, tam, ptrComp) == true)
    {
        int filho = getMaiorFilho(listaPersonagens, i, tam, ptrComp);
        if (listaPersonagens[i].altura < listaPersonagens[filho].altura)
        {
            swapPersonagem(listaPersonagens, i, filho, ptrMov);
            i = filho;
        }
        else
        {
            i = tam;
        }

        *ptrComp += 2;
    }

    *ptrComp += 1; // Incremento para quando não entrar na repetição
}

// Ordenar o vetor por Heapsort
void ordenaPorHeapsort(Personagem listaPersonagens[], int tamanhoVetor, int k, int *ptrComp, int *ptrMov)
{
    // Construção do heap
    for (int tam = 2; tam <= k; tam++)
    {
        construirHeap(listaPersonagens, tam, ptrComp, ptrMov);

        *ptrComp += 1;
    }

    // Para cada um dos (n-k) demais elementos, se ele for menor que a raiz, inserir do heap
    for (int i = k + 1; i <= tamanhoVetor - 1; i++)
    {
        if (listaPersonagens[i].altura < listaPersonagens[1].altura)
        {
            swapPersonagem(listaPersonagens, i, 1, ptrMov);
            reconstruirHeap(listaPersonagens, k, ptrComp, ptrMov);
        }

        *ptrComp += 2;
    }

    // Ordenação propriamente dita
    int tam = k;
    while (tam > 1)
    {
        *ptrComp += 1;

        swapPersonagem(listaPersonagens, 1, tam--, ptrMov);
        reconstruirHeap(listaPersonagens, tam, ptrComp, ptrMov);
    }
}

////////////////////////////////////////////////////////////////////////////

// Função para Ordenação por Inserção
void ordenaPorInsercao(Personagem listaPersonagens[], int tamanhoVetor, int *ptrComp, int *ptrMov)
{
    for (int i = 1; i < tamanhoVetor; i += 1)
    {
        Personagem personagemTemporario = listaPersonagens[i];
        int j = i - 1;
        while ((j >= 0) && ((listaPersonagens[j].altura == personagemTemporario.altura) && (strcmp(listaPersonagens[j].nome, personagemTemporario.nome) > 0)))
        {
            *ptrComp += 3;
            *ptrMov += 1;
            listaPersonagens[j + 1] = listaPersonagens[j];
            j -= 1;
        }

        *ptrComp += 1; // Como saber quando essa varia mais do que um?
        *ptrMov += 1;

        listaPersonagens[j + 1] = personagemTemporario;
    }

    *ptrComp += 1; // Incremento pela comparação ao sair da repetição
}

// Imprimir os resultados
void imprimirAtributos(Personagem listaPersonagens[], int k)
{
    for (int i = 1; i <= k; i++)
    {
        printf(" ## %s", listaPersonagens[i].nome);
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

// Função para criar arquivo de log
void criarLog(time_t inicio, int numeroComparacoes, int numeroMovimentacoes)
{
    float tempo;
    time_t final = time(NULL); // Marcar o final da execução

    tempo = difftime(final, inicio);

    FILE *log = fopen("matricula_heapsortParcial.txt", "w");

    fprintf(log, "Matricula: 790052\tNumero Comparacoes: %d\tNumero Movimentacoes: %d\tTempo Execucao: %fs\n", numeroComparacoes, numeroMovimentacoes, tempo);

    fclose(log);
}

int main(void)
{
    char caminhoArquivo[200];
    int contadorTamanho = 1, numeroComparacoes = 0, numeroMovimentacoes = 0;
    int *ptrComp = &numeroComparacoes, *ptrMov = &numeroMovimentacoes;
    int k = 10;                 // Fator parcial
    time_t inicio = time(NULL); // Marcar o início da execução
    Personagem listaPersonagem[100];

    scanf(" %[^\n]s", caminhoArquivo);
    getchar();

    // Testa o fim do arquivo
    while (testaFim(caminhoArquivo) == false)
    {
        listaPersonagem[contadorTamanho] = montaPersonagem(caminhoArquivo);
        contadorTamanho += 1;

        scanf(" %[^\n]s", caminhoArquivo);
        getchar();
    }

    ordenaPorHeapsort(listaPersonagem, contadorTamanho, k, ptrComp, ptrMov);

    ordenaPorInsercao(listaPersonagem, contadorTamanho, ptrComp, ptrMov);

    imprimirAtributos(listaPersonagem, k); // Imprimir os resultados

    criarLog(inicio, numeroComparacoes, numeroMovimentacoes);

    return 0;
}