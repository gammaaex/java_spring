//学籍番号  : 854003
//氏名 　　 : 山内龍我

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
    int QuestionNum = Integer.valueOf(args[0]);
    File file = new File(args[1]);
    HashMap<String, Integer> map = new HashMap<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Shift-JIS"));
    String line;
    int examineeCount = 0;
    while ((line = br.readLine()) != null)
    {
      String[] data = line.split(",");
      if (Integer.valueOf(data[2]) != QuestionNum) continue;
      String score = data[4];
      map.put(score, (map.get(score) == null) ? 1 : map.get(score) + 1);
      examineeCount++;
    }
    PrintResult(map, examineeCount);
  }
  
  void PrintResult(HashMap<String, Integer> map, int examineeCount)
          throws
          IOException
  {
    ArrayList<String> output = new ArrayList<>();
    for (String score : map.keySet())
    {
      if (score.equals(" ")) score = String.valueOf(0);
      float result = ((float) map.get(score) / (float) examineeCount) * 100.0f;
      output.add(String.format("%2s: %6.3f (%d,%d)%n", score, result, map.get(score), examineeCount));
    }
    File outputFile = new File("ScoreAnalyzerResult1.csv");
    PrintResultWriter.WriteToFile(outputFile,output);
    PrintResultWriter.WriteToConsole(output);
  }
  
}