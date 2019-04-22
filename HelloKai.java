import java.util.*;
public class HelloKai{
  public void run(String[] args)
  {
    if(args.length == 0){
      System.out.println("Hello World!");
    }
    else
    {
      System.out.printf("Hello " + args[0] + "%n");
    }
  }
    public static void main(String[] args)
  {
    HelloKai application = new HelloKai();
    application.run(args);
  }
}

