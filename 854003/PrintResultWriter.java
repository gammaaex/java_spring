//学籍番号  : 854003
//氏名 　　 : 山内龍我

import java.util.*;
import java.io.*;

class PrintResultWriter
{
  public static void WriteToFile(File _file, ArrayList<String> _messages) throws
          IOException
  {
    PrintWriter out = new PrintWriter(new FileWriter(_file));
    _messages.forEach(t -> { out.print(t); });
    out.close();
  }
  
  public static void WriteToConsole(ArrayList<String> _messages)
  {
    _messages.forEach(t -> { System.out.printf(t); });
  }
}