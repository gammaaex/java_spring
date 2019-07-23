import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.lang.Math;

class PictureDrawer{
  HashMap<String, HashMap<String, String>> StudentsMap;
  ArrayList<Integer> QuestionList;
  Integer MaxScore;
  ArrayList<String> SortedStudentList;
  String FileName;
  public PictureDrawer(HashMap<String, HashMap<String, String>> studentsMap,
                       ArrayList<Integer> questionList, Integer maxScore,String fileName)
          throws IOException
  {
   StudentsMap = studentsMap;
   QuestionList = questionList;
   MaxScore = maxScore;
   FileName = fileName;
  }
  
  public PictureDrawer(HashMap<String, HashMap<String, String>> studentsMap,
                       ArrayList<Integer> questionList,
                       int maxScore,ArrayList<String> sortedStudentList,String outputFileName)
          throws
          IOException
  {
    StudentsMap = studentsMap;
    QuestionList = questionList;
    MaxScore = maxScore;
    FileName = outputFileName;
    SortedStudentList = sortedStudentList;
  }
  
  public void DrawSortedPicture()
          throws
          IOException
  {
    int width = Math.round(StudentsMap.size() * 3);
    NullChecker<String> nullChecker = new NullChecker<String>();
    EZ.initialize(width, 15); //view
    int x = 0;
    int y = 0;
    for (var studentNum : SortedStudentList)
    {
      for (var questionNum : QuestionList)
      {
        int[] xp = {x, x, x + 5, x + 5};
        int[] yp = {y, y + 5, y + 5, y};
        var isNull = nullChecker.NullCheck(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        int score = (isNull) ? 0 : Integer.valueOf(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        EZPolygon polygon = EZ.addPolygon(xp, yp, calculatePixelColor(score, MaxScore), true);
        y += 3;
      }
      y = 0;
      x += 3;
    }
    outputImage(width, 15,FileName);
  }
  public void DrawPicture()
          throws
          IOException
  {
    NullChecker<String> nullChecker = new NullChecker<String>();
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
        var isNull = nullChecker.NullCheck(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        int score = (isNull) ? 0 : Integer.valueOf(StudentsMap.get(studentNum).get(String.valueOf(questionNum)));
        EZPolygon polygon = EZ.addPolygon(xp, yp, calculatePixelColor(score, MaxScore), true);
        
        y += 3;
      }
      y = 0;
      x += 3;
    }
    outputImage(width, 15,FileName);
  }
  void outputImage(Integer width, Integer height,String outputFileName) throws
          IOException
  {
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics g = image.createGraphics();
    EZ.app.paintComponent(g);
    ImageIO.write(image, "png", new File(outputFileName));
  }
  Color calculatePixelColor(Integer score, Integer maxScore)
  {
    if (score == 0)
    {
      return new Color(0xff, 0xff, 0xff, 0xff); // 白の透明色
    }
    Float hue = Float.valueOf((1.0f - 1.0f * score / maxScore) * (240.0f / 360.0f));
    return new Color(Color.HSBtoRGB(hue, 1.0f, 1.0f)); // 赤の場合
  }
}