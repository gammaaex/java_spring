import java.util.*;
import java.io.*;

public class Arguments{
  protected String dest;
  protected String InputFileName;
  protected Boolean Help;
  protected String sortKey;
  protected String heatMap;
  public Arguments(String[] args)
  {
    dest = "heatmap.png";
    sortKey = "";
    Help = false;
    heatMap = "score";
    InputFileName = args[args.length-1];
    parse(args);
  }
  void parse(String[] args){
    for(Integer i = 0; i < args.length; i++){
      if(args[i].startsWith("--")){
        i = parseOption(args, i);
      }
    }
  }
  Integer parseOption(String[] args, Integer i) {
    if(Objects.equals(args[i], "--dest")){
      i++;
      String fileName = args[i];
      fileName = updateExtension(fileName,".png");
      this.dest = args[i];
    }
    if(Objects.equals(args[i],"--sort")){
      i++;
      this.sortKey = args[i];
    }
    if(Objects.equals(args[i],"--heatMap")){
      i++;
      this.heatMap = args[i];
    }
    if(Objects.equals(args[i],"--help")){
      i++;
      this.Help = true;
    }
    return i;
  }
  String updateExtension(String fileName, String wontExtention)
  {
    if (fileName.endsWith(wontExtention))
    {
      return fileName;
    }
    return fileName + wontExtention;
  }
  
}