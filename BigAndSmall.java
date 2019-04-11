public class BigAndSmall{
    public static void main(String[] args){
        Double value = Math.random();
        System.out.printf("value: %f: ", value);
        if(value<0.5f){
          System.out.printf("Small%n");
        }
        else{
          System.out.printf("Big%n");
        }
    }
}
