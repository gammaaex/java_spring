/**
 * 学籍番号  : 854003
 * 氏名 　　 : 山内龍我
 * 渡された文字列のリストをFileに出力かコンソールに出力する
 */

import java.util.*;
import java.io.*;
class PrintResultWriter
{
  //ファイル名と文字列のリストを受け取ってファイルに出力する
  public static void WriteToFile(File _file, ArrayList<String> _messages) throws
          IOException
  {
    PrintWriter out = new PrintWriter(new FileWriter(_file));
    _messages.forEach(t -> { out.print(t); });
    out.close();
  }
  
  //文字列のリストを受け取ってコンソールに出力する
  public static void WriteToConsole(ArrayList<String> _messages)
  {
    _messages.forEach(t -> { System.out.printf(t); });
  }
}