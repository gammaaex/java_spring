/**
 * 学籍番号  : 854003
 * 氏名 　　 : 山内龍我
 * 最後に出力する最大値や最小値、平均を出力する
 */


import java.util.*;
import java.io.*;
class WriteCalc
{
  ArrayList<Integer> QuestionList;
  HashMap<String, HashMap<String, String>> StudentsMap;
  ArrayList<String> Output;
  
  public WriteCalc(ArrayList<Integer> questionList, HashMap<String, HashMap<String, String>> studentsMap,
                   ArrayList<String> output)
  {
    QuestionList = questionList;
    StudentsMap = studentsMap;
    Output = output;
  }
  
  public void Average()
  {
    for (var questionNum : QuestionList) //平均
    {
      int count = 0;
      int sum = 0;
      for (var studentNum : StudentsMap.keySet())
      {
        var isNull = NullChecker.NullCheck(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        sum += (isNull) ? 0 : Integer.valueOf(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        count = (isNull ? count : count + 1);
      }
      Output.add(String.format(",%f", (double) sum / (double) count));
    }
    Output.add(String.format("%n"));
  }
  
  public void Min()
  {
    for (var questionNum : QuestionList) //最小値
    {
      int min = 10000;
      int comp;
      for (var studentNum : StudentsMap.keySet())
      {
        var isNull = NullChecker.NullCheck(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        comp = (isNull) ? 10000 : Integer.valueOf(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        min = (min > comp) ? comp : min;
      }
      Output.add("," + min);
    }
    Output.add(String.format("%n"));
  }
  
  public void Max()
  {
    for (var questionNum : QuestionList) //最大値
    {
      int max = 0;
      int comp;
      for (var studentNum : StudentsMap.keySet())
      {
        var isNull = NullChecker.NullCheck(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        comp = (isNull) ? 0 : Integer.valueOf(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        max = (max < comp) ? comp : max;
      }
      Output.add("," + max);
    }
    Output.add(String.format("%n"));
  }
}