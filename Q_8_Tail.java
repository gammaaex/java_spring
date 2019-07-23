import java.io.*;
import java.util.*;

class Q_8_Tail{
  public static void main(String[] args){
    Q_8_Tail tail = new Q_8_Tail();
    tail.Run(args);
  }
  public void Run(String[] args){
    int num = Integer.parseInt(args[0]);
    String fileName = args[1];
    
    if(num<0){
      num = num*-1;
    }
    if(num>5){ //デバッグ
      num = -1;
    }
  
    try
    {
      File file = new File(fileName);
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Shift-JIS"));
      String line;
      String[] data = new String[10];
      int count = 0;
  
      while ((line = br.readLine()) != null)
      {
        count++;
        data[count] = line;
      }
      if(num == -1)num = count;
      for(int i=count-num+1;i<count+1;i++){
        System.out.println(data[i]);
      }
    } catch (IOException e)
    {
      System.out.println(e);
    }
  }
}