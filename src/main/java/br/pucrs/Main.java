package br.pucrs;

import java.util.Random;

public class Main{

    static long iteracoesMergeSort = 0;
    static long iteracoesMaxRecursivo = 0;
    static long iteracoesMultiplicacao = 0;

    public static void main(String[] args) {
        int[] tamanhos = {32, 2048, 1048576};

        for (int tamanho : tamanhos) {
            System.out.println("=== TESTES COM TAMANHO: " + tamanho + " ===");

            long[] vetor = gerarVetorAleatorio(tamanho);

            testarMergeSort(vetor.clone());
            testarMaxIterativo(vetor.clone());
            testarMaxRecursivo(vetor.clone());

            System.out.println();
        }

        // Teste específico para multiplicação com 4, 16 e 64 bits
        testarMultiplicacao(4);
        testarMultiplicacao(16);
        testarMultiplicacao(64);
    }

    /////////////////// MERGESORT ///////////////////////////////////////////
    
    public static void testarMergeSort(long[] vetor) {
        iteracoesMergeSort = 0;
        long inicio = System.nanoTime();
        mergeSort(vetor, 0, vetor.length - 1);
        long fim = System.nanoTime();

        System.out.println("[Merge Sort]");
        System.out.println("Iterações: " + iteracoesMergeSort);
        System.out.println("Tempo: " + (fim - inicio) / 1_000_000.0 + " ms");
    }

    public static void mergeSort(long[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(vetor, inicio, meio);
            mergeSort(vetor, meio + 1, fim);
            intercalar(vetor, inicio, meio, fim);
        }
    }

    public static void intercalar(long[] vetor, int inicio, int meio, int fim) {
        int tamanhoEsq = meio - inicio + 1;
        int tamanhoDir = fim - meio;

        long[] esquerda = new long[tamanhoEsq];
        long[] direita = new long[tamanhoDir];

        for (int i = 0; i < tamanhoEsq; i++) {
            esquerda[i] = vetor[inicio + i];
        }

        for (int j = 0; j < tamanhoDir; j++) {
            direita[j] = vetor[meio + 1 + j];
        }

        int i = 0, j = 0, k = inicio;

        while (i < tamanhoEsq && j < tamanhoDir) {
            iteracoesMergeSort++;
            if (esquerda[i] <= direita[j]) {
                vetor[k++] = esquerda[i++];
            } else {
                vetor[k++] = direita[j++];
            }
        }

        while (i < tamanhoEsq) {
            vetor[k++] = esquerda[i++];
        }

        while (j < tamanhoDir) {
            vetor[k++] = direita[j++];
        }
    }

    //////////////////// MÁXIMO ITERATIVO ////////////////////
    
    public static void testarMaxIterativo(long[] vetor) {
        long inicio = System.nanoTime();

        //O valor máximo encontrado no vetor
        long max = maxIterativo(vetor);
        long fim = System.nanoTime();

        System.out.println("[Máximo Iterativo]");
        System.out.println("Iterações: " + (vetor.length - 1));
        System.out.println("Tempo: " + (fim - inicio) / 1_000_000.0 + " ms");
    }

    public static long maxIterativo(long[] vetor) {
        long max = vetor[0];
        for (int i = 1; i < vetor.length; i++) {
            if (vetor[i] > max) {
                max = vetor[i];
            }
        }
        return max;
    }

    //////////////////// MÁXIMO RECURSIVO ////////////////////
    
    public static void testarMaxRecursivo(long[] vetor) {
        iteracoesMaxRecursivo = 0;
        long inicio = System.nanoTime();
        
        //O valor máximo encontrado no vetor
        long max = maxRecursivo(vetor, 0, vetor.length - 1);
        long fim = System.nanoTime();

        System.out.println("[Máximo Recursivo]");
        System.out.println("Iterações: " + iteracoesMaxRecursivo);
        System.out.println("Tempo: " + (fim - inicio) / 1_000_000.0 + " ms");
    }

    public static long maxRecursivo(long[] vetor, int inicio, int fim) {
        iteracoesMaxRecursivo++;
        if (inicio == fim) return vetor[inicio];
        if (fim - inicio == 1) return Math.max(vetor[inicio], vetor[fim]);

        int meio = (inicio + fim) / 2;
        long v1 = maxRecursivo(vetor, inicio, meio);
        long v2 = maxRecursivo(vetor, meio + 1, fim);
        return Math.max(v1, v2);
    }

    //////////////////// MULTIPLICAÇÃO RECURSIVA ////////////////////
    public static void testarMultiplicacao(int bits) {
        Random r = new Random();
        long limite = (bits == 64) ? Long.MAX_VALUE : (1L << bits) - 1;

        long x = (long)(r.nextDouble() * limite);
        long y = (long)(r.nextDouble() * limite);

        iteracoesMultiplicacao = 0;
        long inicio = System.nanoTime();
        long resultado = multiplicar(x, y, bits);
        long fim = System.nanoTime();

        System.out.println("[Multiplicação Recursiva] - " + bits + " bits");
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("Resultado: " + resultado);
        System.out.println("Iterações: " + iteracoesMultiplicacao);
        System.out.println("Tempo: " + (fim - inicio) / 1_000_000.0 + " ms");
        System.out.println();
    }

    public static long multiplicar(long x, long y, int n) {
        iteracoesMultiplicacao++;

        if (n == 1) {
            return x * y;
        } else {
            int m = (n + 1) / 2;
            long potencia = 1L << m;

            long a = x / potencia;
            long b = x % potencia;
            long c = y / potencia;
            long d = y % potencia;

            long e = multiplicar(a, c, m);
            long f = multiplicar(b, d, m);
            long g = multiplicar(b, c, m);
            long h = multiplicar(a, d, m);

            return (1L << (2 * m)) * e + (1L << m) * (g + h) + f;
        }
    }


    //////////////////// Função auxiliar ////////////////////
    public static long[] gerarVetorAleatorio(int tamanho) {
        Random r = new Random();
        long[] vetor = new long[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = r.nextLong(1_000_000);
        }
        return vetor;
    }
}

