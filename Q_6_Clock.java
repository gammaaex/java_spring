import java.awt.Color;
import java.util.*;
class Line{
  public double StartX;
  public double StartY;
  public double EndX;
  public double EndY;
}
class Q_6_Clock
{
  public static void main(String[] args) throws
          InterruptedException
  {
    Q_6_Clock q_6_clock = new Q_6_Clock();
    q_6_clock.Run();
  }
  
  public void Run() throws
          InterruptedException
  {
    double currentSeconds;
    double currentMinutes;
    double currentHours;
  
    EZ.initialize(400, 400);
  
  
    while (true)
    {
      Date date = new Date();
      EZ.removeAllEZElements();
      EZCircle circle = EZ.addCircle(
              200, 200, 200, 200, Color.BLACK, false);
      currentSeconds = Math.toRadians(date.getSeconds() * 6.0 - 90); //秒のラジアン値
      currentMinutes = Math.toRadians(date.getMinutes() * 6.0 - 90); //分のラジアン値
      currentHours = Math.toRadians((date.getHours() * 5 + date.getMinutes() / 12.0) * 6.0 - 90); // 時間のラジアン値
      
      Line SecondsLine = new Line();
      Line MinutesLine = new Line();
      Line HoursLine = new Line();
      
      SecondsLine.EndX = Math.cos(currentSeconds) * 100 + 200;
      SecondsLine.EndY = Math.sin(currentSeconds) * 100 + 200;
      
      MinutesLine.EndX = Math.cos(currentMinutes) * 100 + 200;
      MinutesLine.EndY = Math.sin(currentMinutes) * 100 + 200;
      
      HoursLine.EndX = Math.cos(currentHours) * 80 + 200;
      HoursLine.EndY = Math.sin(currentHours) * 80 + 200;
      
      System.out.println("時間 " + HoursLine.EndX + "   :   " + HoursLine.EndY);
      System.out.println("分   " + MinutesLine.EndX + "   :   " + MinutesLine.EndY);
      System.out.println("秒   " + SecondsLine.EndX + "   :   " + SecondsLine.EndY);
      
      EZ.addLine(200, 200, (int) HoursLine.EndX, (int) HoursLine.EndY, Color.RED); //x,y,x,y 時間
      EZ.addLine(200, 200, (int) MinutesLine.EndX, (int) MinutesLine.EndY, Color.BLUE); //x,y,x,y 分
      EZ.addLine(200, 200, (int) SecondsLine.EndX, (int) SecondsLine.EndY, Color.GREEN); //x,y,x,y 秒
      Thread.sleep(1000);
    }
    
  }
  
}