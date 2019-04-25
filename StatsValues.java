import java.util.*;

public class StatsValues
{
  public static void main(String[] args)
  {
    StatsValues statsValues = new StatsValues();
    statsValues.run();
  }
  public void run()
  {
    int[] randomArray = new int[100];
    Random random = new Random();
    for (int i = 0; i < randomArray.length; i++)
    {
      randomArray[i] = random.nextInt(1000);
    }
    ArrayList<Integer> randomList = this.BuildRandomList(randomArray);
    PrintResult(randomList);
    PrintList(randomList);
  }
  public void PrintResult(ArrayList<Integer> randomList){
    System.out.printf("合計 : %5d ",this.Sum(randomList));
    System.out.printf("最大 : %5d ",Max(randomList));
    System.out.printf("最小 : %5d ",Small(randomList));
    System.out.printf("平均 : %5d %n",Average(randomList));
  }
  public int  Sum(ArrayList<Integer> randomList){
    int result = 0;
    for(int item: randomList){
      result+=item;
    }
    return result;
  }
  public int Max(ArrayList<Integer> randomList){
    int result = 0;
    for(int item: randomList){
      if(result<=item){
        result = item;
      }
    }
    return result;
  }
  public int Small(ArrayList<Integer> randomList){
    int result = 10000;
    for(int item: randomList){
      if(result>=item){
        result = item;
      }
    }
    return result;
  }
  public int Average(ArrayList<Integer> randomList){
    int result = 0;
    int sum = this.Sum(randomList);
    result = sum/randomList.size();
    return result;
  }

  public ArrayList<Integer> BuildRandomList(int[] randomArray)
  {
    ArrayList<Integer> randomList = new ArrayList<>();
    for (int i = 0; i < randomArray.length; i++){
      randomList.add(randomArray[i]);
    }
    return randomList;
  }
  public void PrintList(ArrayList<Integer> randomList){
    int cnt = 0;
    for(int item: randomList){
      System.out.printf("%3d ",item);
      cnt++;
      if(cnt%10==0)System.out.println();
    }
  }
}
