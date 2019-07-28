/**
 * 学籍番号  : 854003
 * 氏名 　　 : 山内龍我
 * Stringで渡された文字列がnullか""かチェックする
 * 型引数もとってるけど使ってない
 */

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



