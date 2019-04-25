import java.util.*;

public class TrapezoidalRulePi
{
  public static void main(String[] args)
  {
    TrapezoidalRulePi trapezoidalRulePi = new TrapezoidalRulePi();
    if (args.length == 0)
    {
      trapezoidalRulePi.run(0.001f);
    } else
    {
      trapezoidalRulePi.run(Float.parseFloat(args[0]));
    }
  }
  
  public static void run(float range)
  {
    float pi = 0;
    for (float i = range; i < 1-range; i+=range)
    {
      pi += GetArea(i, GetHeight(i),range);
    }
    System.out.printf("pi = %f%n", pi*4);
  }
  
  public static float GetHeight(float X) //高さを算出する
  {
    return (float) Math.sqrt((double) 1 - (X * X));
  }
  
  public static float GetArea(float X, float H, float range) //面積を算出する
  {
    return ((X - (X-range)) * (H + (H-range))) / 2.0f;
  }
}