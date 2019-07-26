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

class ScoreAnalyzer6 extends ScoreAnalyzer
{
  HashMap<String, HashMap<String, String>> StudentsMap;
  HashMap<String, HashMap<String, Integer>> StudentsTimeMap;
  ArrayList<Integer> questionList;
  ArrayList<String> StudentList;
  ArrayList<String> output;
  String outputFileName;
  int maxScore;
  
  public static void main(String[] args)
          throws
          IOException
  {
    ScoreAnalyzer6 scoreAnalyzer6 = new ScoreAnalyzer6();
    Arguments arguments = new Arguments(args);
    if (arguments.Help == true)
    {
      scoreAnalyzer6.PrintHelp();
    }
    scoreAnalyzer6.Run(arguments);
  }
  
  void Initialize()
  {
    StudentsMap = new HashMap<>(); //学生番号、問題番号、点数
    StudentsTimeMap = new HashMap<>();  //学生番号、問題番号、Time（計算済み）
    questionList = new ArrayList<>();
    StudentList = new ArrayList<>();
    output = new ArrayList<>();
    maxScore = 0;
    outputFileName = "heatmap5.png";
  }
  
  public void Run(Arguments arguments)
          throws
          IOException
  {
    Initialize();
    File file = new File(arguments.InputFileName);
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Shift-JIS"));
    String line;
    while ((line = br.readLine()) != null)
    {
      String[] data = line.split(",");
      SetMap(data);
      SetMapTime(data);
      maxScore = SetMaxScore(data[4]) > maxScore ? SetMaxScore(data[4]) : maxScore;
    }
    
    Collections.sort(questionList);
    StudentList = SortMap(arguments);
    PrintResult(); //ソートしたStudentListを入れている
    PictureDrawer.DrawSortedPicture(StudentsMap, questionList, maxScore, StudentList ,arguments.Dest);
  }
  
  private int SetMaxScore(String num)
  {
    if (NullChecker.NullCheck(num) || num.equals(""))
    {
      return 0;
    } else
    {
      return Integer.valueOf(num);
    }
  }
  
  //学生番号、問題番号、スコアをMapにセットする
  private void SetMap(String[] data)
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
  
  //学生番号、問題番号、時間をMapにセットする
  private void SetMapTime(String[] data)
  {
    if (StudentsTimeMap.get(data[3]) == null)
    {
      StudentsTimeMap.put(data[3], new HashMap<String, Integer>());
      StudentsTimeMap.get(data[3]).put(data[2], SubmittedExam(data));//dataの大きさで時間を返す
    } else
    {
      StudentsTimeMap.get(data[3]).put(data[2], SubmittedExam(data));//dataの大きさで時間を返す
    }
  }
  
  //未提出の時、nullを返す
  public Integer SubmittedExam(String[] data)
  {
    if (data.length > 6)
    {
      return ConvertTimeCorrect(data[5], data[6]);
    }
    return null;
  }
  
  // かかった時間を返す
  public Integer ConvertTimeCorrect(String startTime, String endTime)
  {
    String[] _startTime = startTime.split(":");
    String[] _endTime = endTime.split(":");
    
    int IntStartTime = Integer.valueOf(_startTime[1]) + Integer.valueOf(_startTime[0]) * 60;
    int IntEndTime = Integer.valueOf(_endTime[1]) + Integer.valueOf(_endTime[0]) * 60;
    
    return IntEndTime - IntStartTime;
  }
  
  //必要な情報をMapからoutputに入れ、PrintResultWriterクラスに全投げ
  public void PrintResult()
          throws
          IOException
  {
    for (var studentNum : StudentList)
    { //学生番号を取得
      StudentScore5 studentScore = new StudentScore5(StudentsMap, StudentsTimeMap, studentNum);
      output.add(studentNum);
      for (var questionNum : questionList)
      {
        var isNull = NullChecker.NullCheck(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        output.add("," + (isNull ? "" : StudentsMap.get(studentNum).get(String.valueOf(questionNum))));
        isNull = StudentsTimeMap.get(studentNum).get(String.valueOf(questionNum)) == null;
        String time = (isNull ? "" : String.valueOf(StudentsTimeMap.get(studentNum).get(String.valueOf(questionNum))));
        output.add("," + time);
      }
      output.add("," + studentScore.Status.Max);
      output.add("," + studentScore.Status.Min);
      output.add("," + studentScore.Status.Average + String.format("%n"));
    }
    WriteCalc writeCalc = new WriteCalc(questionList, StudentsMap, output);
    writeCalc.Max();
    writeCalc.Min();
    writeCalc.Average();
    File outputFile = new File("ScoreAnalyzerResult5.csv");
    PrintResultWriter.WriteToFile(outputFile, output);
    PrintResultWriter.WriteToConsole(output);
  }
  
  //Helpオプションが指定された時に出力
  public void PrintHelp()
          throws
          IOException
  {
    File file = new File("help.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
    String line;
    while ((line = br.readLine()) != null) System.out.println(line);
  }
  
  //Mapを入れたらIdでソート、ソートされた学生番号をリストで返す
  public ArrayList<String> SortStudentsId()
  {
    ArrayList<Integer> list = new ArrayList<>();
    ArrayList<String> sortedStudentsList = new ArrayList<>();
    HashMap<String, HashMap<String, String>> resultMap = new HashMap<>();
    for (var studentNum : StudentsMap.keySet())
    {
      list.add(Integer.valueOf(studentNum));
    }
    Collections.sort(list);
    for (var item : list)
    {
      sortedStudentsList.add(String.valueOf(item));
    }
    return sortedStudentsList;
  }
  
  //Mapを入れたらスコアの平均でソート、ソートされた学生番号をリストで返す
  public ArrayList<String> SortStudentsScore()
  {
    ArrayList<Double> sortedStudentAverageList = new ArrayList<>();
    HashMap<String, Double> StudentAverageMap = new HashMap<>();
    ArrayList<String> sortedStudentList = new ArrayList<>();
    for (var studentNum : StudentsMap.keySet()) //学生番号と平均値のリレーションを設定
    {
      StudentScore5 studentScore = new StudentScore5(StudentsMap, StudentsTimeMap, studentNum);
      StudentAverageMap.put(studentNum, studentScore.Status.Average);
      sortedStudentAverageList.add(studentScore.Status.Average);
    }
    Collections.sort(sortedStudentAverageList);
    for (var averageNum : sortedStudentAverageList)
    {
      for (var studentNum : StudentAverageMap.keySet()) //対応ずけを確認
      {
        sortedStudentList = ContainsList(StudentAverageMap.get(studentNum), averageNum, studentNum);
      }
    }
    return sortedStudentList;
  }
  
  //Mapを入れたら時間の平均でソート、ソートされた学生番号をリストで返す
  public ArrayList<String> SortStudentsTime()
  {
    ArrayList<Double> sortedStudentAverageList = new ArrayList<>();
    HashMap<String, Double> StudentAverageMap = new HashMap<>();
    ArrayList<String> sortedStudentList = new ArrayList<>();
    for (var studentNum : StudentsMap.keySet()) //学生番号と平均値のリレーションを設定
    {
      StudentScore5 studentScore = new StudentScore5(StudentsMap, StudentsTimeMap, studentNum);
      StudentAverageMap.put(studentNum, studentScore.Status.AverageTime);
      sortedStudentAverageList.add(studentScore.Status.AverageTime);
    }
    Collections.sort(sortedStudentAverageList);
    for (var averageNum : sortedStudentAverageList)
    {
      for (var studentNum : StudentAverageMap.keySet()) //対応ずけを確認
      {
        sortedStudentList = ContainsList(StudentAverageMap.get(studentNum), averageNum, studentNum);
      }
    }
    return sortedStudentList;
  }
  
  //オプションで指定されたものに対してどのソートを行うか
  public ArrayList<String> SortMap( Arguments arguments)
  {
    if (arguments.SortKey.equals("id"))
    {
      return SortStudentsId();
    }
    if (arguments.SortKey.equals("score"))
    {
      return SortStudentsScore();
    }
    if (arguments.SortKey.equals("time"))
    {
      return SortStudentsTime();
    }
    return new ArrayList<String>(StudentsMap.keySet());
  }
  
  //ネスト回避
  public ArrayList<String> ContainsList(Double Average1, Double Average2,
                                        String studentNum)
  {
    if (Average1.equals(Average2))
    {
      if (!StudentList.contains(studentNum))
      {
        StudentList.add(studentNum);
        return StudentList;
      }
    }
    return StudentList;
  }
}