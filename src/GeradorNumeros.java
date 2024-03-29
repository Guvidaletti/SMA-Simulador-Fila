public class GeradorNumeros {
    //  m > 0
    //  0 < a < m
    //  0 <= c < M
    //  a semente (x0) 0 <= x0 < M
    private static final long seed = 123456L;
    private static final long a = 1664525L;
    private static final long c = 1013904223L;
    private static final long M = (long) Math.pow(2, 32);

    private static long previous = seed;

    public static double nextRandom() {
        previous = ((a * previous) + c) % M;
        return (double) previous / M;
    }
}
