// import edu.princeton.cs.algs4.Interval1D;
// import edu.princeton.cs.algs4.Interval2D;
// import edu.princeton.cs.algs4.Counter;
// import edu.princeton.cs.algs4.StdDraw;
// import edu.princeton.cs.algs4.Out;
// import edu.princeton.cs.algs4.In;
// import edu.princeton.cs.algs4.Point2D;
// import java.util.Arrays;
// import edu.princeton.cs.algs4.StdIn;
// import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Date;
// import java.lang.*;
// import java.util.concurrent.*;
public class SmartDate extends Date{
    public SmartDate(int month, int day, int year){
        super(month,day,year);
        if (!isValidDate(month, day, year)){
            throw new IllegalArgumentException("Invalid date");
        }
        
    }
    public SmartDate(String date){
        super(date);
        String[] fields = date.split("/");
        if (fields.length != 3){
            throw new IllegalArgumentException("Invalid date");

        }
        int month = Integer.parseInt(fields[0]);
        int day = Integer.parseInt(fields[1]);
        int year = Integer.parseInt(fields[2]);
        if (!isValidDate(month, day, year)){
            throw new IllegalArgumentException("Invalid date");
        }
    }

    private static boolean isValidDate(int month, int day, int year){
        if (month < 1 || month > 12){
            return false;
        }
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(year)){
            daysInMonth[3] = 29;
        }
        return day > 0 &&  day < daysInMonth[month];
    }
    private static boolean isLeapYear(int year){
        if (year % 400 == 0){
            return true;
        }
        if (year % 100 == 0){
            return false;
        }
        return year%4 == 0;
    }

    public String dayOfTheWeek() {
        int m = month(); 
        int d = day();   
        int y = year();  
        if (m < 3) {
            m += 12;
            y -= 1;
        }
    
        int k = y % 100;   
        int j = y / 100;      
        int h = (d + (13 * (m + 1)) / 5 + k + (k / 4) + (j / 4) + 5 * j) % 7;
    
        
        String[] daysOfWeek = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        return daysOfWeek[h];
    }

    public static void main(String[] args) {
        try {
            SmartDate date1 = new SmartDate(2, 29, 2020);
            System.out.println("Valid date: " + date1 + " - Day of the week: " + date1.dayOfTheWeek());
    
            SmartDate date2 = new SmartDate("3/15/2021");
            System.out.println("Valid date: " + date2 + " - Day of the week: " + date2.dayOfTheWeek());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    
        try {
            SmartDate date3 = new SmartDate(4, 1, 2021);
            System.out.println("Valid date: " + date3 + " - Day of the week: " + date3.dayOfTheWeek());
        } catch (IllegalArgumentException e) {
            System.err.println("Expected exception for invalid date: " + e.getMessage());
        }
    
        try {
            SmartDate date4 = new SmartDate("7/4/2020");
            System.out.println("Valid date: " + date4 + " - Day of the week: " + date4.dayOfTheWeek());
        } catch (IllegalArgumentException e) {
            System.err.println("Expected exception for invalid date: " + e.getMessage());
        }
    }

}