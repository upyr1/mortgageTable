import java.util.*;
import java.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;



public class TestTable {

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
		
		

String labels[] = {"Interest Rate", "Times Compounded Anually"};





/*INTERESTRATES
 * A random float for the interest rates 
 * */


/*Build a gui box for the program
 * 
 * 
 * **/


// Create a button with text OK
JButton jbtOK = new JButton("OK");
// Create a button with text Cancel
JButton jbtCancel = new JButton("Cancel");
// Create a label with text "Enter your name: "
JLabel jlblPrinciple = new JLabel("Enter the principle $: ");
// Create a text field with text "Type Name Here"


//Spinner for interest rate
SpinnerNumberModel interestSpinnerModel = new SpinnerNumberModel(5,1,100,1);

//JLabel jblCompound new JLabel("How often is the mortgage compouneded anually");
//setting up spiner for compouned times

SpinnerNumberModel compoundSpinnerModel = new SpinnerNumberModel(2,1,12,1 );


// Create a panel to group components

JPanel panel = new JPanel();




JSpinner InterestSpinner =  addLabeledSpinner(panel,   labels[0],	interestSpinnerModel);
double rate =  ((Integer)interestSpinnerModel.getValue()).doubleValue(); 
JSpinner compoundSpinner =  addLabeledSpinner(panel,   labels[1],	compoundSpinnerModel);
double compoundTimes =  ((Integer)compoundSpinner.getValue()).doubleValue(); 

panel.add(jbtOK); // Add the OK button to the panel
panel.add(jbtCancel); // Add the Cancel button to the panel


// Register listeners
OKListenerClass listener1 = new OKListenerClass();
CancelListenerClass listener2 = new CancelListenerClass();
jbtOK.addActionListener(listener1);
jbtCancel.addActionListener(listener2);

JFrame frame = new JFrame(); // Create a frame
frame.add(panel); // Add the panel to the frame
frame.setTitle("Mortgage information");
frame.setSize(450, 100);
frame.setLocation(200, 100);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);


double total = 0;



double principle = 50000;
double time = 2;
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




total = (double) Math.round (total * 100)/100;
double interestPayment = GetInterestPayment( principle , monthlyInterestRate);


double principlePayment = payment - interestPayment;
double remain = 0 ;

//principlePayment = Math.round(principlePayment * 100)/100;

System.out.println("principle    | payment |   interest payment |      Principle Paid          principle remaining" );



 while (noPayments > 0)
 {    
	 principlePayment = payment - interestPayment; 
	remain = principle - principlePayment;
	 System.out.println("$"+DF.format(principle)+"  $"+DF.format(payment)+"    $"+DF.format(interestPayment)+"   $"+DF.format(principlePayment)+"     $"+DF.format(remain)+"   "+noPayments);	
	 noPayments --;

	 principle = remain;
	 interestPayment = GetInterestPayment( principle , monthlyInterestRate);
 }


	



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

public void actionPerformed(double principle, double monthlyInterestRate, double noPayments) {
	double payment =  LoanPayment(principle,monthlyInterestRate, noPayments);
  
}


static double LoanPayment(double principle, double monthlyInterest  , double paymentTimes ) {
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

