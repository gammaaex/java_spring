import java.util.*;

public class FizzBuzz
{
  public void run(String[] args)
  {
    if (args.length == 0)
    {
      calc(15);
    }
    else{
      int num = Integer.valueOf(args[0]);
      calc(num);
  
    }
  }
  public static void main(String[] args)
  {
    FizzBuzz application = new FizzBuzz();
    application.run(args);
  }
  public void calc(int num){
    for(int i=1;i<=num;i++){
      String result = new String();
      if(!(i %3==0 || i%5 == 0)){
        result = Integer.toString(i);
      } else
      {
        if (i % 3 == 0)
        {
          result = "fizz";
        }
        if (i % 5 == 0)
        {
          result = result + "buzz";
        }
      }
      System.out.println(result);
    }
  }
}