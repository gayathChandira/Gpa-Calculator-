package gpacalculator;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Calculation {

    double count;
    double sumOfCredit;
    double gpa;
    String gpavalue;

    public static void main(String args[]) {

    }

    public void multiple(ArrayList credit, ArrayList userResult) {
        System.out.println("cal");
        for (int i = 0; i < credit.size(); i++) {
            count += (double) credit.get(i) * (double) userResult.get(i);
            System.out.println("count  //" + count);
        }
        System.out.println("calrulation class " + count);
        finalone(count, credit);
    }

    ;
    public String finalone(double count, ArrayList credit) {
        for (int i = 0; i < credit.size(); i++) {
            sumOfCredit += (double) credit.get(i);

        }
        System.out.println("sum of credt  " + sumOfCredit);
        gpa = count / sumOfCredit;
        System.out.println("gpa //// " + gpa);
        DecimalFormat df = new DecimalFormat(".####");

        gpavalue = df.format(gpa);
        return gpavalue;
    }
}
