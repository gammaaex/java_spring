class PracticeLambda{
  public static void main(String[] args){
    Runnable runner = () -> { System.out.println("Hello Lambda!"); };
    runner.run(); //Hello Lambda!
  }
}