//学籍番号  : 854003
//氏名 　　 : 山内龍我

import java.util.*;
import java.io.*;
public class ScoreAnalyzer3
{
  public static void main(String[] args)
          throws IOException
  {
    ScoreAnalyzer3 scoreAnalyzer3 = new ScoreAnalyzer3();
    scoreAnalyzer3.Run(args);
  }
  private void Run(String[] args)
          throws IOException
  {
    File file = new File(args[0]);
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Shift-JIS"));
    String line;
    HashMap<String, HashMap<String, String>> StudentsMap = new HashMap<>();
    ArrayList<Integer> questionList = new ArrayList<>();
    while ((line = br.readLine()) != null)
    {
      String[] data = line.split(",");
      SetMap(StudentsMap,questionList,data);
    }
    PrintResult(StudentsMap, questionList);
  }

  private void SetMap(HashMap<String, HashMap<String, String>> StudentsMap ,ArrayList<Integer> questionList,String[] data){
    if (StudentsMap.get(data[3]) == null)
    {
      StudentsMap.put(data[3], new HashMap<String, String>());
      StudentsMap.get(data[3]).put(data[2], data[4]);
    } else
    {
      StudentsMap.get(data[3]).put(data[2], data[4]);//生徒番号、問題番号、スコア
    }
    NotNullAddList(questionList, data[2]);
  }
  void NotNullAddList(ArrayList<Integer> scoreList, String score){
    if(!(scoreList.contains(Integer.valueOf(score)))) scoreList.add(Integer.valueOf(score));
  }
  public void PrintResult(HashMap<String,HashMap<String,String>> StudentsMap,ArrayList<Integer> questionList)
          throws IOException
  {
    NullChecker<String> nullChecker = new NullChecker<String>();
    ArrayList<String> output = new ArrayList<>();
    for (var studentNum : StudentsMap.keySet())
    {
      StudentScore studentScore = new StudentScore(StudentsMap, studentNum);
      output.add(studentNum);
      for (var questionNum : questionList)
      {
        boolean isNull = nullChecker.NullCheck(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        output.add("," + ((isNull) ? "" : StudentsMap.get(studentNum).get(String.valueOf(questionNum))));
      }
      output.add("," + studentScore.Status.Max);
      output.add("," + studentScore.Status.Min);
      output.add("," + studentScore.Status.Average + String.format("%n"));
    }
    WriteCalc writeCalc = new WriteCalc(questionList,StudentsMap,output);
    writeCalc.Max();
    writeCalc.Min();
    writeCalc.Average();
    File outputFile = new File("ScoreAnalyzerResult3.csv");
    PrintResultWriter printResultWriter = new PrintResultWriter(outputFile,output);
  }
}