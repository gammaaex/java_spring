import java.util.*;
import java.io.*;
class StudentScore
{
  public Stats Status;
  public StudentScore(HashMap<String, HashMap<String,String>> _map,String id)//questionNum,score
  {
    Status = new Stats(Max(_map.get(id)),Min(_map.get(id)),Sum(_map.get(id)),Average(_map.get(id)));
  }
  private int Sum(HashMap<String, String> map)
  {
    int result = 0;
    for (var score : map.values())
    {
      if(score==null || score.equals(""))continue;
      result+=Integer.valueOf(score);
    }
    return result;
  }
  private int Max(HashMap<String, String> map)
  {
    int result = 0;
    for (var score : map.values())
    {
      if(score==null || score.equals(""))continue;
      result = (result < Integer.valueOf(score)) ? Integer.valueOf(score) : result;
    }
    return result;
  }
  private int Min(HashMap<String,String> map)
  {
    int result = 10000;
    for (var score : map.values())
    {
      if(score==null || score.equals(""))continue;
      result = (result > Integer.valueOf(score)) ? Integer.valueOf(score) : result;
    }
    return result;
  }
  private double Average(HashMap<String,String> map)
  {
    
    return (double)Sum(map) / (double)map.size();
  }
}