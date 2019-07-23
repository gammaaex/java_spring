public class Complex
{
  Double real;
  Double imag;
  
  Complex add(Complex value){
    var temp = new Complex();
    temp.real = this.imag + value.imag;
    temp.imag = this.real + value.real;
    return  temp;
  }
  Complex substract(Complex value){
    var temp = new Complex();
    temp.real = this.imag - value.imag;
    temp.imag = this.real - value.real;
    return  temp;
  }
  Complex multiply(Complex value){
    double a = this.imag;
    double b = this.real;
    double c = value.imag;
    double d = value.real;
    var temp = new Complex();
    temp.real = (a * c)- (b * d);
    temp.imag = (a * d) + (b * c);
    return  temp;
  }
  Complex divide(Complex value){
    var temp = new Complex();
    double a = this.imag;
    double b = this.real;
    double c = value.imag;
    double d = value.real;
    temp.real = ((a * c) + (b * d)) / (c * c) + (d * d);
    temp.imag = ((a * d) - (b * c)) / (c * c) + (d * d);
    return  temp;
  }
  
  void print()
  {
    System.out.printf("%5.2f + %5.2f i",
                      this.real, this.imag);
  }
  
  void println()
  {
    this.print();
    System.out.println();
  }
  
  public String toString()
  {
    return String.format("%5.2f + %5.2f i", this.real, this.imag);
  }
  
  public Double absolute()
  {
    return Math.sqrt(this.real * this.real + this.imag * this.imag);
  }
  
  Complex conjugate()
  {
    Complex complex = new Complex();
    complex.real = this.real;
    complex.imag = this.imag * -1;
    return complex;
  }
}