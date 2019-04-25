import java.util.*;

public class MonteCarloPi
{
  public static void main(String[] args)
  {
    MonteCarloPi monteCarloPi = new MonteCarloPi();
    double pi = 0;
    if(args.length == 0){
      pi  = monteCarloPi.Calc(1000);
      
    }
    else{
      pi = monteCarloPi.Calc(Integer.valueOf(args[0]));
    }
    System.out.println(pi);
  }
  public static double Calc(int count){
    double X = 0;
    double Y = 0;
    int hitCount = 0;
    double pi = 0;
    for(int i= 0;i<count;i++)
    {
      X = Math.random();
      Y = Math.random();
      if (range(X, Y) <= 1)
      {
        hitCount++;
      }
    }
    return (float)hitCount/(float)count*4f;
  }
  
  public static double range(double X, double Y)
  {
    return Math.sqrt((X*X)+(Y*Y));
  }
}