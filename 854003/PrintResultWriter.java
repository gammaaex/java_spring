import java.util.*;
import java.io.*;

class PrintResultWriter
{
  private File file;
  private ArrayList<String> messages;
  
  public PrintResultWriter(File _file, ArrayList<String> _messages) throws
          IOException
  {
    file = _file;
    messages = _messages;
    WriteToConsole();
    WriteToFile();
  }
  
  public void WriteToFile() throws
          IOException
  {
    PrintWriter out = new PrintWriter(new FileWriter(file));
    messages.forEach(t -> { out.print(t); });
    out.close();
  }
  
  public void WriteToConsole()
  {
    messages.forEach(t -> { System.out.printf(t); });
  }
}