class ComplexCalculator{
  Double real;
  Double imag;
  public static void main(String[] args){
    ComplexCalculator complexCalculator = new ComplexCalculator();
    complexCalculator.run();
  }
  public void run()
  {
    Complex c1 = this.createComplex(5.0, -6.0);
    Complex c2 = this.createComplex(3.0, 2.0);
  
    System.out.printf("absolute(%s) = %f%n",c1,c1.absolute());
    System.out.printf("conjugate(%s) = ",c2,c2.conjugate());
    c2.println();
    System.out.printf("%s + %s = %s%n",c1,c2,c1.add(c2));
    System.out.printf("%s + %s = %s%n",c1,c2,c1.substract(c2));
    System.out.printf("%s + %s = %s%n",c1,c2,c1.multiply(c2));
    System.out.printf("%s + %s = %s%n",c1,c2,c1.divide(c2));


    /*
    5.00 + -6.00 i +  3.00 +  2.00 i =  8.00 + -4.00 i
    5.00 + -6.00 i -  3.00 +  2.00 i =  2.00 + -8.00 i
    5.00 + -6.00 i *  3.00 +  2.00 i = 27.00 + -8.00 i
    5.00 + -6.00 i /  3.00 +  2.00 i =  0.23 + -2.15 i
    */
    
  }
  public Complex createComplex(double num1,double num2)
  {
    Complex temp = new Complex();
    temp.imag = num1;
    temp.real = num2;
    return temp;
  }
}