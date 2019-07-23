import java.util.*;
import java.io.*;

class PrintResultWriter
{
//  private File file;
//  private ArrayList<String> messages;
  
//  public PrintResultWriter(File _file, ArrayList<String> _messages) throws
//          IOException
//  {
//    file = _file;
//    messages = _messages;
//    WriteToConsole();
//    WriteToFile();
//  }
  
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