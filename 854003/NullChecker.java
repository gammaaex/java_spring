// 基本的に型引数はStringで入ってくるから型決めてもいいかも


import java.util.*;
import java.io.*;
class NullChecker<T>
{
  boolean NullCheck(String num)
  {
    if (!Objects.isNull(num) && !Objects.equals("", num))
    {
      return false;
    }
    return true;
  }

}



