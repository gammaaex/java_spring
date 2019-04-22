import java.util.*;

public class Fibonacci
{
  public void run(String[] args)
  {
    if (args.length == 0){
      Calc(20);
    }
    else{
      int num = Integer.valueOf(args[0]);
      Calc(num);
    }
  }
  
  public static void main(String[] args)
  {
    Fibonacci application = new Fibonacci();
    application.run(args);
  }
  public void Calc(int num){
    int[] fibonacci = new int[num];
    for(int i=0;i<num;i++){
      if(i==0  || i==1)
      {
        fibonacci[i] = 1;
      }
      else{
        fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
      }
      System.out.printf("%d%n",fibonacci[i]);
      
    }
  }
}