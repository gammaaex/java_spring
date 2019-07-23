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
class ScoreAnalyzer1
{
  public static void main(String[] args)
          throws
          IOException
  {
    ScoreAnalyzer1 scoreAnalyzer1 = new ScoreAnalyzer1();
    scoreAnalyzer1.Run(args);
  }
  
  private void Run(String[] args)
          throws
          IOException
  {
    int QuestionNum = Integer.valueOf(args[0]); //指定の問題番号
    File file = new File(args[1]); //ファイル名
    HashMap<String, Integer> map = new HashMap<>(); //点数とその数のMap
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Shift-JIS"));
    String line;
    int examineeCount = 0;
    while ((line = br.readLine()) != null)
    {
      //SetMap
      String[] data = line.split(",");
      if (Integer.valueOf(data[2]) != QuestionNum) continue;
      String score = data[4];
      map.put(score, (map.get(score) == null) ? 1 : map.get(score) + 1);
      examineeCount++;
    }
    PrintResult(map, examineeCount);
  }
  
  //必要な情報をMapからoutputに入れ、PrintResultWriterクラスに全投げ
  void PrintResult(HashMap<String, Integer> map, int examineeCount)
          throws
          IOException
  {
    ArrayList<String> output = new ArrayList<>();
    for (String score : map.keySet())
    {
      //割合を算出
      float result = ((float) map.get(score) / (float) examineeCount) * 100.0f;
      output.add(String.format("%2s: %6.3f (%d,%d)%n", score, result, map.get(score), examineeCount));
    }
    //Print用のクラスに全部投げる
    File outputFile = new File("ScoreAnalyzerResult1.csv");
    PrintResultWriter.WriteToFile(outputFile, output);
    PrintResultWriter.WriteToConsole(output);
  }
}