public class LeapYear{
    public static void main(String[] args) {
        Integer year = 2016;
        Boolean leapYear = false;
        if((year%4==0  !(year%100==0)){
            leapYear = true;
        }
        if(year%400==0)){
            leapYear = true;
        }
        if(leapYear){ // leapYearがtrueの場合．
            System.out.printf("%d年はうるう年です．%n", year);
        }
        else{
            System.out.printf("%d年はうるう年ではありません．%n", year);
        }
    }
}
