package br.pucrs;

import java.util.Random;

public class App 
{
    static long iteracoes = 0;
    public static void main( String [] args )
    {
        int [] tamanhos = {32, 2048, 1_048_576};

        for (int tamanho : tamanhos) {
            int [] vetor = gerarVetorAleatorio(tamanho);
            iteracoes = 0;

            long inicio = System.nanoTime();
            mergeSort(vetor, 0, vetor.length - 1);
            long fim = System.nanoTime();

            System.out.println("Tamanho: " + tamanho);
            System.out.println("Iterações: " + iteracoes);
            System.out.println("Tempo (ms): " + (fim - inicio) / 1_000_000.0);
            System.out.println("--------");
        }
    }

    public static int [] gerarVetorAleatorio(int tamanho) {
        Random aleatorio = new Random();
        int [] vetor = new int [tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(100000);
        }
        return vetor;
    }

    public static void mergeSort(int[] vetor, int inicio, int fim) {
        if (inicio >= fim) return;

        int meio = (inicio + fim) / 2;

        mergeSort(vetor, inicio, meio);
        mergeSort(vetor, meio + 1, fim);
        intercalar(vetor, inicio, meio, fim);
    }

    public static void intercalar(int [] vetor, int inicio, int meio, int fim) {
        int tamanhoEsquerda = meio - inicio + 1;
        int tamanhoDireita = fim - meio;

        int [] esquerda = new int[tamanhoEsquerda];
        int [] direita = new int[tamanhoDireita];

        for (int i = 0; i < tamanhoEsquerda; i++) {
            esquerda [i] = vetor [inicio + i];
        }
        for (int j = 0; j < tamanhoDireita; j++) {
            direita [j] = vetor [meio + 1 + j];
        }

        int i = 0, j = 0, k = inicio;

        while (i < tamanhoEsquerda && j < tamanhoDireita) {
            iteracoes++;
            if (esquerda [i] <= direita [j]) {
                vetor [k++] = esquerda [i++];
            } else {
                vetor [k++] = direita [j++];
            }
        }

        while (i < tamanhoEsquerda) {
            vetor [k++] = esquerda [i++];
            iteracoes++;
        }

        while (j < tamanhoDireita) {
            vetor [k++] = direita [j++];
            iteracoes++;
        }
    }
}
