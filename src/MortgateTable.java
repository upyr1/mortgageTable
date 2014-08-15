import java.util.*;
import java.text.*;
import java.awt.*;

import javax.swing.*;


public class MortgateTable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
/* Random compound times
 * CompoundTimes is an array that goes from 1-4 to simulate multiple 
 * compound options
 * 
 * */		

		
Scanner keyboard = new Scanner(System.in);		
OKListenerClass listener1 = new OKListenerClass();
		









double total = 0;


System.out.println("How much money do you want to borrow");
double principle =   keyboard.nextDouble();
System.out.println("How many years do you want to pay back the loan");
double time = keyboard.nextDouble();
System.out.println("how often is the interest compounded ?");
double compoundTimes = keyboard.nextDouble();
System.out.println("What is the interest rate as a precent so 5.5% would be 5.5");
double rate = keyboard.nextDouble();
rate = rate  /100;





/*NoPayments
 * number of payments
 * 
 * */

double noPayments = time * 12;

double monthlyInterestRate = GetInterestPerPeriod(rate, compoundTimes);





/*Decimal format to limit the size of doubles*/
DecimalFormat DF = new DecimalFormat("#.00");




/*calcualte minimum payment and total 
 * 
 * */

total = LoanTotal(principle, compoundTimes,rate,time);

double payment =  LoanPayment(principle,monthlyInterestRate, noPayments);


total = (double) Math.round (total * 100)/100;
double interestPayment = GetInterestPayment( principle , monthlyInterestRate);


double principlePayment = payment - interestPayment;
double remain = 0 ;

//principlePayment = Math.round(principlePayment * 100)/100;



JFrame frame = new JFrame();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

Object rowData[][] = new Object[(int) noPayments][5];


for (int i = 0; i < (int) noPayments; i ++)
{
	
	
	rowData[i][0] = "$"+DF.format(principle); 
	rowData[i][1] = "$"+DF.format(payment); 
	interestPayment = GetInterestPayment(principle, monthlyInterestRate);
	rowData[i][2] = "$"+DF.format(interestPayment);
	principlePayment = payment - interestPayment;
	rowData[i][3] = "$"+DF.format(principlePayment);
	remain = principle - principlePayment;
	rowData[i][4] = "$"+DF.format(remain);
	principle = remain;
}


Object columnNames[] = { "principle", "payment", "interest payment","Principle Paid", " principle remaining" };
JTable table = new JTable(rowData, columnNames);

JScrollPane scrollPane = new JScrollPane(table);
frame.add(scrollPane, BorderLayout.CENTER);
frame.setSize(1000, 1500);
frame.setVisible(true);


 while (noPayments > 0)
 {    
	 principlePayment = payment - interestPayment; 
	remain = principle - principlePayment;

	 noPayments --;

	 principle = remain;
	 interestPayment = GetInterestPayment( principle , monthlyInterestRate);
 }


	



}
	


private static void add(JTextField jTextField) {
		// TODO Auto-generated method stub
		
	}



private static double GetInterestPayment(double principle,
			double monthlyInterestRate) {
	double iPayment = principle * monthlyInterestRate;	
	
		return iPayment;
	}



public static double LoanTotal(double principleAmount, double compoundAmount, double rate, double timePaid) {
double intrestExponant = compoundAmount + timePaid;	
	
	double totalAmount = principleAmount * Math.pow((1+ rate / (double)compoundAmount), intrestExponant);

return totalAmount;
	
}
 
private static double GetInterestPerPeriod(double anualRate, double compund)
{
 	double intrestExpon = compund /12;
	
 double	periodInterest = Math.pow(1 +(anualRate /compund),intrestExpon) -1;
 
	return periodInterest ;
	}

//P = r(PV)/1-(1+r)^-n 


private static double LoanPayment(double principle, double monthlyInterest  , double paymentTimes ) {
	double payment = 	principle *(monthlyInterest  *(Math.pow((1+ monthlyInterest), paymentTimes))/(Math.pow((1+ monthlyInterest ), paymentTimes)-1));
			
	
	// TODO Auto-generated method stub
	return payment;
}


static protected JSpinner addLabeledSpinner(Container c,
        String label,
        SpinnerModel model) {
JLabel l = new JLabel(label);
c.add(l);

JSpinner spinner = new JSpinner(model);
l.setLabelFor(spinner);
c.add(spinner);

return spinner;
}



	
	
}