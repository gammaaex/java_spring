import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;


class MakePicture{
  public static void main(String[] args) throws
          InterruptedException,IOException{
    MakePicture makePicture = new MakePicture();
    makePicture.Run();
  }
  public void Run()throws InterruptedException,IOException{
    EZ.initialize(400, 400);
    int[] xp = {100,200,200};
    int[] yp = {100,150,200};
    EZCircle circle = EZ.addCircle(100, 100, 5, 5, Color.BLUE, true);
    EZPolygon polygon = EZ.addPolygon(xp,yp,Color.RED,true);
//    outputImage(400,400);
  }
  void outputImage(Integer width, Integer height) throws IOException {
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics g = image.createGraphics();
    EZ.app.paintComponent(g); // image に図形を描画する．
  }
}