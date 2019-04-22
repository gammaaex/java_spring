import java.util.*;

public class ArgsSorter
{
  public static void run(String[] args)
  {
    System.out.printf("before: ");
    PrintArray(args);
    Arrays.sort(args);
    System.out.printf("after: ");
    PrintArray(args);
  }
  
  public static void PrintArray(String[] args)
  {
    for (int i = 0; i < args.length; i++)
    {
      System.out.printf("%s ", args[i]);
    }
    System.out.println();
  }
  public static void main(String[] args)
  {
    run(args);
  }
}