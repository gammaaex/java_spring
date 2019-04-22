import java.util.*;

public class HelloKai2{
  public void run(String[] args)
  {
    if (args.length == 0)
    {
      System.out.println("Hello World!");
    } else
    {
      if (Objects.equals(args[0], "world"))
      {
        System.out.printf("Hi," + args[0] + "%n");
      } else
      {
        System.out.printf("Hello " + args[0] + "%n");
      }
    }
  }
  public static void main(String[] args)
  {
    HelloKai2 application = new HelloKai2();
    application.run(args);
  }
}
