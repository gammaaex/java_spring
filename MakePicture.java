import java.awt.Color;
class MakePicture{
  public static void main(String[] args){
    MakePicture makePicture = new MakePicture();
    makePicture.Run();
  }
  public void Run()throws InterruptedException{
    EZ.initialize(400, 400);
    EZCircle circle = EZ.addCircle(
            100, 100, 5, 5, Color.BLUE, true);
  }
}