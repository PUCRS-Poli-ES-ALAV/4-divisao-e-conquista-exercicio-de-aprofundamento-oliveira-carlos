package br.pucrs;

import java.util.Random;

public class MaximoValor {

    static long iteracoes = 0;
    public static void main(String[] args) {
        int[] tamanhos = {32, 2048, 1_048_576};

        for (int tamanho : tamanhos) {
            long[] vetor = gerarVetorAleatorio(tamanho);
            iteracoes = 0;

            long inicio = System.nanoTime();
            long maximo = encontrarMaximo(vetor, tamanho);
            long fim = System.nanoTime();

            System.out.println("Tamanho: " + tamanho);
            System.out.println("Máximo encontrado: " + maximo);
            System.out.println("Iterações: " + iteracoes);
            System.out.println("Tempo (ms): " + (fim - inicio) / 1_000_000.0);
            System.out.println("--------");
        }
    }

    public static long[] gerarVetorAleatorio(int tamanho) {
        Random aleatorio = new Random();
        long[] vetor = new long[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1_000_000); // Pode ajustar o intervalo se quiser
        }
        return vetor;
    }

    public static long encontrarMaximo(long[] vetor, int tamanho) {
        long maximo = vetor[0];
        for (int i = 1; i < tamanho; i++) {
            iteracoes++;
            if (vetor[i] > maximo) {
                maximo = vetor[i];
            }
        }
        return maximo;
    }
}

