

import java.util.*;
import java.io.*;
class NullChecker<T>
{
  static boolean NullCheck(String num)
  {
    if (!Objects.isNull(num) && !Objects.equals("", num))
    {
      return false;
    }
    return true;
  }
  
}