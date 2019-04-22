import java.util.*;

public class StudentIdValidator
{
  public static void main(String[] args)
  {
    run(args);
  }
  
  public static void run(String[] args)
  {
    for(int i=0;i<args.length;i++)
    {
      validate(args[i]);
    }
  }
  
  public static void validate(String id)
  {
    if (id.length() != 6)
    {
      System.out.println("not student id");
    } else
    {
      int[] ArrayId = new int[6];
      for (int i = 0; i < 6; i++)
      {
        ArrayId[i] = Character.getNumericValue(id.charAt(i));
      }
      validateId(ArrayId);
    }
  }
  
  public static void validateId(int[] ArrayId)
  {
    int result = 0;
    for (int i = 0; i < 6; i++)
    {
      result += ArrayId[i];
      System.out.printf("%s",ArrayId[i]);
    }
    System.out.printf(" :");
    if (result % 10 == 0)
    {
      System.out.println("valid");
    } else
    {
      System.out.println("invalid");
    }
  }
  
}