package br.pucrs;

import java.util.Random;

public class MultiplicacaoInteira {

    static long iteracoes = 0;

    public static void main(String[] args) {
        testarMultiplicacao(4);
        testarMultiplicacao(16);
        testarMultiplicacao(64);
    }

    public static void testarMultiplicacao(int bits) {
        Random aleatorio = new Random();
        long limite = (bits == 64) ? Long.MAX_VALUE : (1L << bits) - 1;

        long x = (long)(aleatorio.nextDouble() * limite);
        long y = (long)(aleatorio.nextDouble() * limite);

        iteracoes = 0;
        long inicio = System.nanoTime();
        long resultado = multiplicar(x, y, bits);
        long fim = System.nanoTime();

        System.out.println("Bits: " + bits);
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("Resultado: " + resultado);
        System.out.println("Iterações (chamadas recursivas): " + iteracoes);
        System.out.println("Tempo (ms): " + (fim - inicio) / 1_000_000.0);
        System.out.println("--------");
    }

    public static long multiplicar(long x, long y, int n) {
        iteracoes++;

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
}
