//学籍番号  : 854003
//氏名 　　 : 山内龍我
import java.util.*;
import java.io.*;

class ScoreAnalyzer6
{
  public static void main(String[] args)
          throws IOException
  {
    ScoreAnalyzer6 scoreAnalyzer6 = new ScoreAnalyzer6();
    Arguments arguments = new Arguments(args);
    if (arguments.Help == true)
    {
      scoreAnalyzer6.PrintHelp();
    }
    scoreAnalyzer6.Run(arguments);
  }
  public void Run(Arguments arguments)
          throws IOException
  {
    File file = new File(arguments.InputFileName);
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Shift-JIS"));
    String line;
    HashMap<String, HashMap<String, String>> StudentsMap = new HashMap<>(); //学生番号、問題番号、score
    HashMap<String, HashMap<String, Integer>> StudentsTimeMap = new HashMap<>();  //学生番号、問題番号、Time
    ArrayList<Integer> questionList = new ArrayList<>();
    ArrayList<String> StudentList = new ArrayList<>();
    
    int maxScore = 0;
    while ((line = br.readLine()) != null)
    {
      String[] data = line.split(",");
      SetMap(StudentsMap,questionList,data);
      SetMapTime(StudentsTimeMap,data);
      maxScore = SetMaxScore(data[4]) > maxScore ? SetMaxScore(data[4]) : maxScore;
    }
    StudentList = SortMap(StudentsMap, StudentsTimeMap, arguments);
    PrintResult(StudentsMap, questionList, StudentsTimeMap, StudentList);
    PictureDrawer.DrawSortedPicture(StudentsMap,questionList,maxScore,StudentList,arguments.dest);
  }
  
  private int SetMaxScore(String num){
    if(NullChecker.NullCheck(num) || num.equals("")) {
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
  
  private void SetMapTime(HashMap<String, HashMap<String, Integer>> StudentsTimeMap ,String[] data){
    if (StudentsTimeMap.get(data[3]) == null)
    {
      StudentsTimeMap.put(data[3], new HashMap<String, Integer>());
      StudentsTimeMap.get(data[3]).put(data[2], SubmittedExam(data, StudentsTimeMap));//dataの大きさで時間を返す
    } else
    {
      StudentsTimeMap.get(data[3]).put(data[2], SubmittedExam(data, StudentsTimeMap));//dataの大きさで時間を返す
    }
  }
  
  //未提出の時、nullを返す
  public Integer SubmittedExam(String[] data, HashMap<String, HashMap<String, Integer>> StudentsTimeMap)
  {
    if (data.length > 6)
    {
      return ConvertTimeCorrect(data[5], data[6]);
    }
    return null;
  }
  public Integer ConvertTimeCorrect(String startTime, String endTime)
  {
    String[] _startTime = startTime.split(":");
    String[] _endTime = endTime.split(":");
    
    int IntStartTime = Integer.valueOf(_startTime[1])+ Integer.valueOf(_startTime[0]) * 60;
    int IntEndTime = Integer.valueOf(_endTime[1])+ Integer.valueOf(_endTime[0]) * 60;
    
    return IntEndTime-IntStartTime;
  }
  
  
  void NotNullAddList(ArrayList<Integer> scoreList, String score)
  {
    if (!(scoreList.contains(Integer.valueOf(score)))) scoreList.add(Integer.valueOf(score));
  }
  
  public void PrintResult(HashMap<String, HashMap<String, String>> StudentsMap, ArrayList<Integer> questionList,
                          HashMap<String, HashMap<String, Integer>> StudentsTimeMap,ArrayList<String> sortedStudentList)
          throws IOException
  {
    ArrayList<String> output = new ArrayList<>();
    for (var studentNum : sortedStudentList)
    { //学生番号を取得
      StudentScore5 studentScore = new StudentScore5(StudentsMap, StudentsTimeMap, studentNum);
      output.add(studentNum);
      for (var questionNum : questionList)
      {
        var isNull = NullChecker.NullCheck(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        output.add("," + (isNull ? "" : StudentsMap.get(studentNum).get(String.valueOf(questionNum))));
        isNull = StudentsTimeMap.get(studentNum).get(String.valueOf(questionNum)) ==null;
        String time = (isNull ? "" : String.valueOf(StudentsTimeMap.get(studentNum).get(String.valueOf(questionNum))));
        output.add("," + time);
      }
      output.add("," + studentScore.Status.Max);
      output.add("," + studentScore.Status.Min);
      output.add("," + studentScore.Status.Average + String.format("%n"));
    }
    
    WriteCalc writeCalc = new WriteCalc(questionList,StudentsMap,output);
    writeCalc.Max();
    writeCalc.Min();
    writeCalc.Average();
    File outputFile = new File("ScoreAnalyzerResult5.csv");
    PrintResultWriter.WriteToFile(outputFile,output);
    PrintResultWriter.WriteToConsole(output);
  }
  public void PrintHelp()
          throws IOException
  {
    File file = new File("help.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
    String line;
    while ((line = br.readLine()) != null) System.out.println(line);
  }
  public ArrayList<String> SortStudentsId(HashMap<String,HashMap<String,String>> StudentsMap){ //Mapを入れたらIdでソート
    ArrayList<Integer> list = new ArrayList<>();
    ArrayList<String> sortedStudentsList = new ArrayList<>();
    HashMap<String,HashMap<String,String>> resultMap = new HashMap<>();
    for(var studentNum : StudentsMap.keySet()){
      list.add(Integer.valueOf(studentNum));
    }
    Collections.sort(list);
    for(var item : list){
      sortedStudentsList.add(String.valueOf(item)) ;
    }
    return sortedStudentsList;
  }
  public ArrayList<String> SortStudentsScore(HashMap<String,HashMap<String,String>> StudentsMap,
                                             HashMap<String,HashMap<String,Integer>> StudentsTimeMap){ //Mapを入れたらスコアの平均でソート
    ArrayList<Double> sortedStudentAverageList = new ArrayList<>();
    HashMap<String,Double> StudentAverageMap = new HashMap<>();
    ArrayList<String> sortedStudentList = new ArrayList<>();
    for(var studentNum : StudentsMap.keySet()) //学生番号と平均値のリレーションを設定
    {
      StudentScore5 studentScore = new StudentScore5(StudentsMap, StudentsTimeMap, studentNum);
      StudentAverageMap.put(studentNum,studentScore.Status.Average);
      sortedStudentAverageList.add(studentScore.Status.Average);
    }
    Collections.sort(sortedStudentAverageList);
    for( var averageNum : sortedStudentAverageList){ //平均がソートされている
      for(var studentNum : StudentAverageMap.keySet()) //対応ずけを確認
      {
        sortedStudentList = ContainsList(StudentAverageMap.get(studentNum),averageNum,sortedStudentList,studentNum);
      }
    }
    return sortedStudentList;
  }
  
  public ArrayList<String> SortStudentsTime(HashMap<String,HashMap<String,String>> StudentsMap,
                                            HashMap<String,HashMap<String,Integer>> StudentsTimeMap){ //Mapを入れたらスコアの平均でソート
    ArrayList<Double> sortedStudentAverageList = new ArrayList<>();
    HashMap<String,Double> StudentAverageMap = new HashMap<>();
    ArrayList<String> sortedStudentList = new ArrayList<>();
    for(var studentNum : StudentsMap.keySet()) //学生番号と平均値のリレーションを設定
    {
      StudentScore5 studentScore = new StudentScore5(StudentsMap, StudentsTimeMap, studentNum);
      StudentAverageMap.put(studentNum,studentScore.Status.AverageTime);
      sortedStudentAverageList.add(studentScore.Status.AverageTime);
    }
    Collections.sort(sortedStudentAverageList);
    for( var averageNum : sortedStudentAverageList){ //平均がソートされている
      for(var studentNum : StudentAverageMap.keySet()) //対応ずけを確認
      {
        sortedStudentList = ContainsList(StudentAverageMap.get(studentNum),averageNum,sortedStudentList,studentNum);
      }
    }
    return sortedStudentList;
  }
  public ArrayList<String> SortMap(HashMap<String,HashMap<String,String>> StudentsMap ,
                                   HashMap<String,HashMap<String,Integer>> StudentsTimeMap, Arguments arguments){
    if(arguments.sortKey.equals("id")){
      return SortStudentsId(StudentsMap);
    }
    if(arguments.sortKey.equals("score")){
      return SortStudentsScore(StudentsMap,StudentsTimeMap);
    }
    if(arguments.sortKey.equals("time")){
      return SortStudentsTime(StudentsMap,StudentsTimeMap);
    }
    return new ArrayList<String>(StudentsMap.keySet());
  }
  public ArrayList<String> ContainsList(Double Average1,Double Average2,
                                        ArrayList<String> sortedStudentList,String studentNum){
    if(Average1.equals(Average2)) {
      if(!sortedStudentList.contains(studentNum)){
        sortedStudentList.add(studentNum);
        return sortedStudentList;
      }
    }
    return sortedStudentList;
  }
}