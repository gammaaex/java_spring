import java.util.*;

class Q_11_ReversePolishNotationCalculator{
  public static void main(String[] args){
    Q_11_ReversePolishNotationCalculator q_11_reversePolishNotationCalculator = new Q_11_ReversePolishNotationCalculator();
    q_11_reversePolishNotationCalculator.Run(args);
  }
  public void Run(String[] args){
    ArrayList<String> templist = new ArrayList<>();
    ArrayList<String> formulaList = new ArrayList<>();
    ArrayList<String> resultList = new ArrayList<>();
    
    for(int i  =0;i<args.length;i++){
      templist.add(args[i]);
    }
    for(var ele : templist){
      formulaList.add(ele);
    }
    for(var ele : formulaList){
      for(int i = 0;i<ele.length();i++){
        if(Character.isDigit(ele.charAt(i)))
        {
          System.out.println(Integer.valueOf(ele.charAt(i));
        }
        else
        {
          char atom = ele.charAt(i);
          if (atom == "+".charAt(0))
          {
            System.out.println("+");
          } else if (atom == "-".charAt(0))
          {
            System.out.println("-");
          } else if (atom == "*".charAt(0))
          {
            System.out.println("*");
          } else if (atom == "/".charAt(0))
          {
            System.out.println("/");
          }
        }
      }
    }
  }
}