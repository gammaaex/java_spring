class MersenneNumbers_854003
{
  public static void main(String[] args)
  {
    MersenneNumbers_854003 mersenneNumbers = new MersenneNumbers_854003();
    mersenneNumbers.run(Integer.valueOf(args[0]));
  
  }
  
  public static void run(int n)
  {
    var result = mersenne(n); // n番目のメルセンヌ数を求める
    for(int i=1;i<=n;i++){
      printPrime(i, result);
    }
  }
  
  public static void printPrime(int n, long result)
  {
    System.out.printf("2 ^ %d - 1 = %d (%d)%n", n, result - 1, result - 1);
  }
  
  public static long mersenne(int n)
  {
    long result = 1;
    if (IsProbablePrime(n))
    {
      for (int i = 1; i <= n; i++)
      {
        result *= 2;
      }
    }
    return result;
  }
  
  public static boolean IsProbablePrime(long num)
  {
    for (int i = 1; i < num; i++)
    {
      if (i % num == 0) return true;
    }
    return false;
  }
}