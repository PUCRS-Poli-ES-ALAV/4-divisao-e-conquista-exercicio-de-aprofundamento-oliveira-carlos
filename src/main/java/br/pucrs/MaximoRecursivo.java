package br.pucrs;

import java.util.Random;

public class MaximoRecursivo {

    static long iteracoes = 0;

    public static void main(String[] args) {
        int[] tamanhos = {32, 2048, 1_048_576};

        for (int tamanho : tamanhos) {
            long[] vetor = gerarVetorAleatorio(tamanho);
            iteracoes = 0;

            long inicio = System.nanoTime();
            long maximo = encontrarMaximoRecursivo(vetor, 0, vetor.length - 1);
            long fim = System.nanoTime();

            System.out.println("Tamanho: " + tamanho);
            System.out.println("Máximo encontrado: " + maximo);
            System.out.println("Iterações (chamadas recursivas): " + iteracoes);
            System.out.println("Tempo (ms): " + (fim - inicio) / 1_000_000.0);
            System.out.println("--------");
        }
    }

    public static long[] gerarVetorAleatorio(int tamanho) {
        Random aleatorio = new Random();
        long[] vetor = new long[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1_000_000);
        }
        return vetor;
    }

    public static long encontrarMaximoRecursivo(long[] vetor, int inicio, int fim) {
        iteracoes++;

        if (fim - inicio <= 1) {
            if (fim == inicio) return vetor[inicio]; // Casos com 1 elemento
            return Math.max(vetor[inicio], vetor[fim]); // Casos com 2 elementos
        } else {
            int meio = (inicio + fim) / 2;
            long maxEsquerda = encontrarMaximoRecursivo(vetor, inicio, meio);
            long maxDireita = encontrarMaximoRecursivo(vetor, meio + 1, fim);
            return Math.max(maxEsquerda, maxDireita);
        }
    }
}
