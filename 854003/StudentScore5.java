/**
 * 学籍番号  : 854003
 * 氏名 　　 : 山内龍我
 */
import java.util.*;
import java.io.*;
class StudentScore5
{
  public Stats5 Status; //一人の学生が持つ全ての問題のMaxやMinを持つ
  
  public StudentScore5(HashMap<String, HashMap<String, String>> _map,
                       HashMap<String, HashMap<String, Integer>> _studentsTimeMap, String id)//questionNum,score
  {
    Status = new Stats5(Max(_map.get(id)), Min(_map.get(id)), Sum(_map.get(id)),
                        Average(_map.get(id)), AverageOfTakenTime(_studentsTimeMap.get(id)));
  }
  public double AverageOfTakenTime(HashMap<String, Integer> studentTimeMap)
  {
    int result = 0;
    int count = 0;
    for (var time : studentTimeMap.values())
    {
      if (time == null)
      {
        time = 0;
      } else
      {
        result += time;
        count++;
      }
    }
    return (double) result / (double) count;
  }
  
  private int Sum(HashMap<String, String> map)
  {
    int result = 0;
    for (var score : map.values())
    {
      if (score == null || score.equals("")) continue;
      result += Integer.valueOf(score);
    }
    return result;
  }
  
  private int Max(HashMap<String, String> map)
  {
    int result = 0;
    for (var score : map.values())
    {
      if (score == null || score.equals("")) continue;
      result = (result < Integer.valueOf(score)) ? Integer.valueOf(score) : result;
    }
    return result;
  }
  
  private int Min(HashMap<String, String> map)
  {
    int result = 10000;
    for (var score : map.values())
    {
      if (score == null || score.equals("")) continue;
      result = (result > Integer.valueOf(score)) ? Integer.valueOf(score) : result;
    }
  
    return result;
  }
  private double Average(HashMap<String, String> map)
  {
    int size = 0;
    for (var item : map.keySet())
    {
      if (!(map.get(item).equals("") || map.get(item) == null))
      {
        size++;
      }
    }
    return (double) Sum(map) / (double) size;
  }
}