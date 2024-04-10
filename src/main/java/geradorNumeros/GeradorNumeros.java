package geradorNumeros;

import javax.naming.LimitExceededException;

public class GeradorNumeros {
  //  m > 0
  //  0 < a < m
  //  0 <= c < M
  //  a semente (x0) 0 <= x0 < M
  private static long seed = 123456L;
  private static final long a = 1664525L;
  private static final long c = 1013904223L;
  private static final long M = (long) Math.pow(2, 32);

  private static long previous = seed;
  private static long limite;

  public static synchronized double nextRandom() throws LimitExceededException {
    if (limite == 0) throw new LimitExceededException("Acabaram-se os números");
    limite--;
    previous = ((a * previous) + c) % M;
    return (double) previous / M;
  }

  public static synchronized double nextRandomNormalized(double min, double max) throws LimitExceededException {
    if (limite == 0) throw new LimitExceededException("Acabaram-se os números");
    limite--;
    return (double) ((max - min) * nextRandom()) + min;
  }

  public static void setSeed(long seed) {
    GeradorNumeros.seed = seed;
    previous = seed;
  }

  public static void setLimite(long limite) {
    GeradorNumeros.limite = limite;
  }
}
