import java.io.*;
import java.util.*;
import java.util.zip.GZIPOutputStream;

class Q_9_Gzip
{
  public static void main(String[] args)
  {
    Q_9_Gzip q_9_gzip = new Q_9_Gzip();
    q_9_gzip.Run(args);
  }
  
  public void Run(String[] args)
  {
    try
    {
      String fileName = args[0];
      String outputFileName = fileName + ".gz";
      String encoding = "Shift-JS";
      InputStream fileStream = new FileInputStream(fileName);
  
      Reader reader = new InputStreamReader(fileStream);
  
//      double currentFileSize = file.length();
      
  
      GZIPOutputStream outputFile = null;
      outputFile = new GZIPOutputStream(new FileOutputStream(outputFileName));
      
      outputFile.write(fileStream.getBytes(encoding));
      outputFile.close();
      
      System.out.println(outputFile.length());
      
      
    } catch (IOException e)
    {
      System.out.println(e);
    }
  }
}