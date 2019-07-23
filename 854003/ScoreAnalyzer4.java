//学籍番号  : 854003
//氏名 　　 : 山内龍我
import java.util.*;
import java.io.*;

public class ScoreAnalyzer4
{
  public static void main(String[] args)
          throws
          IOException
  {
    ScoreAnalyzer4 scoreAnalyzer4 = new ScoreAnalyzer4();
    scoreAnalyzer4.Run(args);
  }
  
  public void Run(String[] args)
          throws
          IOException
  {
    NullChecker nullChecker = new NullChecker();
    File file = new File(args[0]);
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Shift-JIS"));
    String line;
    HashMap<String, HashMap<String, String>> StudentsMap = new HashMap<>();
    HashMap<String, String> questionNums = new HashMap<>();
    ArrayList<Integer> questionList = new ArrayList<>();
    String outputFileName= "heatmap4.png";
  
    int maxScore=1;
    while ((line = br.readLine()) != null)
    {
      String[] data = line.split(",");
      SetMap(StudentsMap,questionList,data);
      maxScore = SetMaxScore(data[4]) > maxScore ? SetMaxScore(data[4]) : maxScore;
    }

    PrintResult(StudentsMap, questionList);
    PictureDrawer.DrawPicture(StudentsMap,questionList,maxScore,outputFileName);
  }
  private int SetMaxScore(String num){
    NullChecker nullChecker = new NullChecker();
    if(nullChecker.NullCheck(num) || num.equals("")) {
      return 0;
    }else{
      return Integer.valueOf(num);
    }
  }
  private void SetMap(HashMap<String, HashMap<String, String>> StudentsMap ,
                      ArrayList<Integer> questionList,String[] data){
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
  
  void NotNullAddList(ArrayList<Integer> scoreList, String score)
  {
    if (!(scoreList.contains(Integer.valueOf(score)))) scoreList.add(Integer.valueOf(score));
  }
  
  public void PrintResult(HashMap<String, HashMap<String, String>> StudentsMap, ArrayList<Integer> questionList)
          throws
          IOException
  {
    ArrayList<String> output = new ArrayList<>();
    for (var studentNum : StudentsMap.keySet())
    { //学生番号を取得
      StudentScore studentScore = new StudentScore(StudentsMap, studentNum);
      output.add(studentNum);
      for (var questionNum : questionList)
      {
        var isNull = NullChecker.NullCheck(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        output.add("," + (isNull ? "" : StudentsMap.get(studentNum).get(String.valueOf(questionNum))));
      }
      output.add("," + studentScore.Status.Max);
      output.add("," + studentScore.Status.Min);
      output.add("," + studentScore.Status.Average + String.format("%n"));
    }
    WriteCalc writeCalc = new WriteCalc(questionList,StudentsMap,output);
    writeCalc.Max();
    writeCalc.Min();
    writeCalc.Average();
    output.add(String.format("%n"));
    File outputFile = new File("ScoreAnalyzerResult4.csv");
    PrintResultWriter.WriteToFile(outputFile,output);
    PrintResultWriter.WriteToConsole(output);  }
  
}