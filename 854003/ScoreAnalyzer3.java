/**
 * 学籍番号  : 854003
 * 氏名   　: 山内龍我
 *
 * data[0] : 日付（使わない）
 * data[1] : readingかwriting（使わない）
 * data[2] : 問題番号
 * data[3] : 学生番号
 * data[4] : 点数
 * data[5] : 課題を開始した時刻
 * data[6] : 課題を提出した時刻
 */
import java.util.*;
import java.io.*;
public class ScoreAnalyzer3 extends ScoreAnalyzer
{
  HashMap<String, HashMap<String, String>> StudentsMap;
  ArrayList<Integer> questionList;
  ArrayList<String> output;
  
  public static void main(String[] args)
          throws
          IOException
  {
    ScoreAnalyzer3 scoreAnalyzer3 = new ScoreAnalyzer3();
    scoreAnalyzer3.Run(args);
  }
  
  void Initialize(){
    StudentsMap = new HashMap<>(); //学生番号、問題番号、スコア
    questionList = new ArrayList<>();
    output = new ArrayList<>();
  }
  
  private void Run(String[] args)
          throws
          IOException
  {
    Initialize();
    File file = new File(args[0]);
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Shift-JIS"));
    String line;
    while ((line = br.readLine()) != null)
    {
      String[] data = line.split(",");
      SetMap(data);
    }
    Collections.sort(questionList);
    PrintResult();
  }
  
  //学生番号、問題番号、スコアをMapにセットする
  private void SetMap(String[] data)
  {
    if (StudentsMap.get(data[3]) == null)
    {
      StudentsMap.put(data[3], new HashMap<String, String>());
      StudentsMap.get(data[3]).put(data[2], data[4]);//生徒番号、問題番号、スコア
    } else
    {
      StudentsMap.get(data[3]).put(data[2], data[4]);//生徒番号、問題番号、スコア
    }
    if (!(questionList.contains(Integer.valueOf(data[2]))))
    {
      questionList.add(Integer.valueOf(data[2]));
    }
  }
  
  //必要な情報をMapからoutputに入れ、PrintResultWriterクラスに全投げ
  public void PrintResult()
          throws
          IOException
  {
    for (var studentNum : StudentsMap.keySet())
    {
      StudentScore studentScore = new StudentScore(StudentsMap, studentNum);
      output.add(studentNum);
      for (var questionNum : questionList)
      {
        boolean isNull = NullChecker.NullCheck(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        output.add("," + ((isNull) ? "" : StudentsMap.get(studentNum).get(String.valueOf(questionNum))));
      }
      output.add("," + studentScore.Status.Max);
      output.add("," + studentScore.Status.Min);
      output.add("," + studentScore.Status.Average + String.format("%n"));
    }
    //WriteCalcのインスタンスを生成して、outputに追加してくれる。
    WriteCalc writeCalc = new WriteCalc(questionList, StudentsMap, output);
    writeCalc.Max();
    writeCalc.Min();
    writeCalc.Average();
    File outputFile = new File("ScoreAnalyzerResult3.csv");
    PrintResultWriter.WriteToFile(outputFile, output);
    PrintResultWriter.WriteToConsole(output);
  }
}