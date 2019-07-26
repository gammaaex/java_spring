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
class ScoreAnalyzer2 extends ScoreAnalyzer
{
  HashMap<String, HashMap<String, Integer>> map;
  ArrayList<Integer> scoreList;
  HashMap<String, Integer> examinCounts;
  ArrayList<String> output;
  public static void main(String[] args)
          throws
          IOException
  {
    ScoreAnalyzer2 scoreAnalyzer2 = new ScoreAnalyzer2();
    scoreAnalyzer2.Run(args);
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
      //SetMap
      String[] data = line.split(",");
      examinCounts.put(data[2], (examinCounts.get(data[2]) != null) ? examinCounts.get(data[2]) + 1 : 1);
      if (map.get(data[2]) == null)
      {
        map.put(data[2], new HashMap<String, Integer>());
        map.get(data[2]).put(data[4], 1);
      } else
      {
        //mapの問題番号に登録されている点数がnullなら1、nullじゃないなら＋１をput
        map.get(data[2]).put(data[4], (map.get(data[2]).get(data[4]) != null) ? map.get(data[2]).get(data[4]) + 1 : 1);
      }
      NotNullAddList(scoreList, data[4]);
    }
    Collections.sort(scoreList);
    PrintResult();
  }
  
  void Initialize(){
    map = new HashMap<>(); //問題番号、点数、人の数
    scoreList = new ArrayList<>();    //重複しない動的な点数
    examinCounts = new HashMap<>(); //問題番号、問題を受けた人の数
    output = new ArrayList<>();
  }
  
  //入ってきたStringがnullか""じゃなかったらaddする。参照。
  void NotNullAddList(ArrayList<Integer> scoreList, String score)
  {
    if(!(score.equals("")))
    {
      if (!(scoreList.contains(Integer.valueOf(score)))){
        scoreList.add(Integer.valueOf(score));
      }
    }
  }
  
  //必要な情報をMapからoutputに入れ、PrintResultWriterクラスに全投げ
  void PrintResult()
          throws
          IOException
  {
    for (var score : scoreList)
    {
      output.add("," + score);
    }
    output.add(String.format("%n"));
  
    for (var questionNum : map.keySet())
    { //1~5
      output.add(questionNum);
      for (var score : scoreList)
      {
        PrintNotNullKey(questionNum, score);
      }
      output.add(String.format("%n"));
    }
    File outputFile = new File("ScoreAnalyzerResult2.csv");
    PrintResultWriter.WriteToFile(outputFile, output);
    PrintResultWriter.WriteToConsole(output);
  }
  
  //ネストを回避するため、nullじゃなかったら計算してoutputに入れる、ここのoutputも参照
  void PrintNotNullKey(String questionNum, Integer score)
  {
    if (map.get(questionNum).get(String.valueOf(score)) != null)
    {
      var resultNum = Double.valueOf(map.get(questionNum).get(String.valueOf(score))
                                             / Double.valueOf(examinCounts.get(questionNum)) * 100.0f);
      output.add(String.format(",%3.3f", resultNum));
    } else
    {
      output.add(",");
    }
  }
}