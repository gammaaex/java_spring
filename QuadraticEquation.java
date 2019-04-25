import java.util.*;

public class QuadraticEquation
{
  public static void main(String[] args)
  {
    QuadraticEquation quadraticEquation = new QuadraticEquation();
    quadraticEquation.Run(args);
  }
  
  public static void Run(String[] args)
  {
    double a = Double.valueOf(args[0]);
    double b = Double.valueOf(args[1]);
    double c = Double.valueOf(args[2]);
    double discResult = Discriminant(a, b, c);
    String result = "";
    System.out.printf("answer : %s%n",Calc(a,b,c));
  }
  public static String Calc(double a, double b, double c){
    String result;

    if(Discriminant(a,b,c)==0){  //重解
      result = String.valueOf((-b + Math.sqrt(Discriminant(a,b,c)))/(2*a));
    }
    else if(Discriminant(a,b,c)<0){  //虚数解
      String imaginNum,realNum;
      imaginNum = String.valueOf(Math.sqrt(-(Discriminant(a,b,c)))/(2*a));
      realNum = String.valueOf(-b / (2*a));
      result = realNum +" + "+ imaginNum + " *i , " + realNum + " - "+ imaginNum + " *i ";
    }
    else{  //実数解
      String result1,result2;
      result1 = String.valueOf((-b + Math.sqrt(Discriminant(a,b,c)))/(2*a));
      result2 = String.valueOf((-b - Math.sqrt(Discriminant(a,b,c)))/(2*a));
      result = result1 +","+ result2;
    }
    return result;
  }
  public static double Discriminant(double a, double b, double c)
  {
    return (b*b)-(4*a*c);
  }
}