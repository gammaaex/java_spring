import java.awt.Color;

class Bound
{
  public static void main(String[] args) throws
          InterruptedException
  {
    Bound bound = new Bound();
    bound.Run();
  }
  
  public void Run() throws
          InterruptedException
  {
    EZ.initialize(400, 400);
    EZCircle circle = EZ.addCircle(
            100, 100, 5, 5, Color.BLUE, true);
    Round(circle);
  }
  
  void Round(EZCircle circle) throws
          InterruptedException
  {
    float time = 0;
    float v_ini = 85.0f;
    float v = 0;
    float y_ini = 10.0f;
    float y = 0;
    final float gravity = 9.8f;
    boolean isFalling = true;
  
    while (true)
    {
      time += 0.1;
      v = v_ini - (gravity * time);
      y = y_ini + ((v * time) - (gravity * time * time * 0.5f));
      
      if(isFalling(v,y,isFalling)){
        time = 0.0;
        v_ini = -1 * v;
        y_ini = y;
        isFalling = !isFalling;
      }
  
  
      EZ.refreshScreen();
      circle.translateTo(circle.getXCenter(), y);
      Thread.sleep(100);
      //System.out.printf("time : %f, v : %f, y : %f%n",time,v,y);
    }
  }
  
  boolean IsSwitch(float v, float y, boolean isFalling)
  {
    if (isFalling && v < 0)
    {
      return true;
    } else if (!isFalling && y > 10)
    {
  
      return true;
    }
    return false;
  }
}