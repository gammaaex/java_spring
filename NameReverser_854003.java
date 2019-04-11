public class NameReverser_854003{
  public static void main(String args[]){
    String name = new String("YamauchiRyoga");
    System.out.printf(name+"\n");
    for(int i = name.length()-1;i>=0;i--){
      System.out.printf(String.valueOf(name.charAt(i)));
    }
    System.out.println();
  }
}
