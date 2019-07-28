/**
 * 学籍番号  : 854003
 * 氏名 　　 : 山内龍我
 * ScoreAnalyzer5以降で使用する平均時間を持ったステータス
 */

import java.util.*;
import java.io.*;

public class Stats5
{
  public int Max;
  public int Min;
  public int Sum;
  public double Average;
  public double AverageTime;
  public Stats5(int _max, int _min, int _sum , double _average,double _averageTime){
    Max = _max;
    Min = _min;
    Sum = _sum;
    Average = _average;
    AverageTime = _averageTime;
  }
}