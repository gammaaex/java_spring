/**
 * 学籍番号  : 854003
 * 氏名 　　 : 山内龍我
 * ScoreAnalyzer4以前で使用するステータス
 */

import java.util.*;
import java.io.*;
class Stats
{
  public int Max;
  public int Min;
  public int Sum;
  public double Average;
  public Stats(int _max, int _min, int _sum , double _average){
    Max = _max;
    Min = _min;
    Sum = _sum;
    Average = _average;
  }
}