/**
 * 学籍番号  : 854003
 * 氏名 　　 : 山内龍我
 * EZで出力＆画像生成を行う
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.lang.Math;

class PictureDrawer
{
  HashMap<String, HashMap<String, String>> StudentsMap;
  ArrayList<Integer> QuestionList;
  Integer MaxScore;
  ArrayList<String> SortedStudentList;
  String FileName;
  //ソートされた学生番号のリストを元にMapからヒートマップを出力する
  public static void DrawSortedPicture(HashMap<String, HashMap<String, String>> StudentsMap,
                                       ArrayList<Integer> QuestionList,
                                       int MaxScore, ArrayList<String> SortedStudentList, String FileName)
          throws
          IOException
  {
    int width = Math.round(StudentsMap.size() * 3);
    EZ.initialize(width, 15); //view
    int x = 0;
    int y = 0;
    for (var studentNum : SortedStudentList)
    {
      for (var questionNum : QuestionList)
      {
        int[] xp = {x, x, x + 5, x + 5};
        int[] yp = {y, y + 5, y + 5, y};
        var isNull = NullChecker.NullCheck(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        int score = (isNull) ? 0 : Integer.valueOf(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        EZPolygon polygon = EZ.addPolygon(xp, yp, calculatePixelColor(score, MaxScore), true);
        y += 3;
      }
      y = 0;
      x += 3;
    }
    outputImage(width, 15, FileName);
  }
  
  //ソートされてないMapを元にヒートマップの出力を行う。
  public static void DrawPicture(HashMap<String, HashMap<String, String>> StudentsMap,
                                 ArrayList<Integer> QuestionList, Integer MaxScore, String FileName)
          throws
          IOException
  {
    int width = Math.round(StudentsMap.size() * 3);
    EZ.initialize(width, 15); //view
    int x = 0;
    int y = 0;
    for (var studentNum : StudentsMap.keySet())
    {
      for (var questionNum : QuestionList)
      {
        int[] xp = {x, x, x + 5, x + 5};
        int[] yp = {y, y + 5, y + 5, y};
        var isNull = NullChecker.NullCheck(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        int score = (isNull) ? 0 : Integer.valueOf(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        EZPolygon polygon = EZ.addPolygon(xp, yp, calculatePixelColor(score, MaxScore), true);
        y += 3;
      }
      y = 0;
      x += 3;
    }
    outputImage(width, 15, FileName);
  }
  
  //出力されたヒートマップを画像に起こす
  static void outputImage(Integer width, Integer height, String outputFileName) throws
          IOException
  {
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics g = image.createGraphics();
    EZ.app.paintComponent(g);
    ImageIO.write(image, "png", new File(outputFileName));
  }
  
  //いい感じの色にしてくれる
  static Color calculatePixelColor(Integer score, Integer maxScore)
  {
    if (score == 0)
    {
      return new Color(0xff, 0xff, 0xff, 0xff); // 白の透明色
    }
    Float hue = Float.valueOf((1.0f - 1.0f * score / maxScore) * (240.0f / 360.0f));
    return new Color(Color.HSBtoRGB(hue, 1.0f, 1.0f)); // 赤の場合
  }
}