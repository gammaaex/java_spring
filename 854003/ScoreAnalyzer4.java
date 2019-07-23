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
    File file = new File(args[0]);
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Shift-JIS"));
    String line;
    HashMap<String, HashMap<String, String>> StudentsMap = new HashMap<>(); //生徒番号、問題番号、スコア
    ArrayList<Integer> questionList = new ArrayList<>(); //問題番号のリスト。重複しないようにする用。
    String outputFileName = "heatmap4.png"; //デフォルト
    int maxScore = 1;
    while ((line = br.readLine()) != null)
    {
      String[] data = line.split(",");
      SetMap(StudentsMap, questionList, data);
      maxScore = SetMaxScore(data[4]) > maxScore ? SetMaxScore(data[4]) : maxScore;
    }
    Collections.sort(questionList);
    PrintResult(StudentsMap, questionList);
    PictureDrawer.DrawPicture(StudentsMap, questionList, maxScore, outputFileName);
  }
  
  //画像に起こす際に必要な最大値を算出
  private int SetMaxScore(String num)
  {
    NullChecker nullChecker = new NullChecker();
    if (nullChecker.NullCheck(num) || num.equals(""))
    {
      return 0;
    } else
    {
      return Integer.valueOf(num);
    }
  }
  
  //学生番号、問題番号、スコアをMapにセットする
  private void SetMap(HashMap<String, HashMap<String, String>> StudentsMap,
                      ArrayList<Integer> questionList, String[] data)
  {
    if (StudentsMap.get(data[3]) == null)
    {
      StudentsMap.put(data[3], new HashMap<String, String>());
      StudentsMap.get(data[3]).put(data[2], data[4]);
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
    
    //WriteCalcのインスタンスを生成して、outputに追加してくれる。
    WriteCalc writeCalc = new WriteCalc(questionList, StudentsMap, output);
    writeCalc.Max();
    writeCalc.Min();
    writeCalc.Average();
    output.add(String.format("%n"));
    File outputFile = new File("ScoreAnalyzerResult4.csv");
    PrintResultWriter.WriteToFile(outputFile, output);
    PrintResultWriter.WriteToConsole(output);
  }
}