public class XPrinter{
   public static void main(String[] args)
   {
     for(int i=0;i<10;i++)
     {
       for(int j=0;j<10;j++)
       {
         if(i==j ||i+j==9)
         {
           System.out.printf("X");
         }
         else
         {
           System.out.printf(".");
         }
       }
       System.out.println();
     }
  }
}
