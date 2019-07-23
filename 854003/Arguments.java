/**
 * 学籍番号  : 854003
 * 氏名 　　 : 山内龍我
 * 指定されたオプションの情報を持つ
 */

import java.util.*;
import java.io.*;

public class Arguments
{
  protected String dest;
  protected String InputFileName;
  protected Boolean Help;
  protected String sortKey;
  protected String heatMap;
  
  //コンストラクタ　argsの中身をみてデフォルトを指定
  public Arguments(String[] args)
  {
    dest = "heatmap.png";
    sortKey = "";
    Help = false;
    heatMap = "score";
    InputFileName = args[args.length - 1];
    parse(args);
  }
  
  //--で始まる数を探す
  void parse(String[] args)
  {
    for (Integer i = 0; i < args.length; i++)
    {
      if (args[i].startsWith("--"))
      {
        i = parseOption(args, i);
      }
    }
  }
  
  //パースされたオプションに対応した処理を行う
  Integer parseOption(String[] args, Integer i)
  {
    if (Objects.equals(args[i], "--dest"))
    {
      i++;
      String fileName = args[i];
      fileName = updateExtension(fileName, ".png");
      this.dest = args[i];
    }
    if (Objects.equals(args[i], "--sort"))
    {
      i++;
      this.sortKey = args[i];
    }
    if (Objects.equals(args[i], "--heatMap"))
    {
      i++;
      this.heatMap = args[i];
    }
    if (Objects.equals(args[i], "--help"))
    {
      i++;
      this.Help = true;
    }
    return i;
  }
  
  //拡張子をいい感じにしてくれる
  String updateExtension(String fileName, String wontExtention)
  {
    if (fileName.endsWith(wontExtention))
    {
      return fileName;
    }
    return fileName + wontExtention;
  }
}