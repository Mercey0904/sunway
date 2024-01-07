import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Assignment2A_TangJiaHui {
	//constant variable
	String ERROR = ("Your input is invalid. Please enter again");

	//Arrays
	int Display []= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
	String []dispatch = new String[3];
	{dispatch[0] = "Lee";
	dispatch[1] = "Afiq";
	dispatch[2] = "Ann";}
	int [][] OrderArray = new int[3][15];
	int noOrDis[]= {0,0,0};

	Scanner scan = new Scanner (System.in);

	//main method
	public static void main (String [] args) {
		Assignment2A_TangJiaHui MM = new Assignment2A_TangJiaHui();
		MM.MMenu();
	}

	//main menu option
	public void MMenu() {
		int opt =0;
		while (opt<1||opt>4) {
			System.out.println("Welcome to JH Delivery Service. Please select the option below");
			System.out.println("1. Enter new order");
			System.out.println("2. Modify Order");
			System.out.println("3. Display all order");
			System.out.println("4. Exit");
			//prompt user to enter their option.
			opt=scan.nextInt();

			if (opt == 1) {
				newOrder();
			}
			else if (opt ==2) {
				modify();
				System.out.println();}
			else if (opt ==3) {
				Display();
				System.out.println();}	
			else if (opt ==4) {
				exitOr();
				System.out.println();}
			else 
				System.out.println (ERROR);
		}	
	}

	public void newOrder () {
		int OrDispatcher =0;
		char sZone = ' ', eZone = ' ';
		int frArea = 0, toArea = 0, weight = 0;
		int OrderSum =0;
		int roundedwgt=0, size =0, order =1, indexNum=0 ;

		System.out.println();

		do {
			OrDispatcher =0; sZone = ' '; eZone = ' ';
			roundedwgt=0; size =0; weight = 0;
			//prompt user to select dispatcher
			System.out.println("Please select the dispatcher below");
			for (int n = 0; n<3; n++) {
				System.out.println((n+1) +". " + dispatch[n]);
			}

			OrDispatcher = scan.nextInt();

			if (OrDispatcher<1||OrDispatcher>3) {
				System.out.println("Error");}
		} while (OrDispatcher<1||OrDispatcher>3);	

		do {
			if (noOrDis[OrDispatcher-1] == 15) {
				System.out.println("This dispatcher is full. Please select other dispatcher.");
				OrDispatcher=scan.nextInt();} 
		} while (noOrDis[(OrDispatcher-1)] == 15);

		System.out.println();

		do{ 
			//prompt user to choose a starting point
			System.out.println("Choose start point A or B");
			sZone = scan.next().charAt(0);
			System.out.println();
			if (sZone =='a'||sZone =='A') {
				frArea = 1;}
			if (sZone =='B'||sZone =='b') {
				frArea = 2;}
			if (sZone != 'A' && sZone != 'B' && sZone !='a' && sZone !='b') {
				System.out.println("error");}
		} while(sZone != 'A' && sZone != 'B' && sZone !='a' && sZone !='b');

		//prompt user to choose the ending point
		while(eZone != 'A' && eZone != 'B' && eZone !='a' && eZone !='b'){
			System.out.println("Choose end point A or B");
			eZone = scan.next().charAt(0);
			switch (eZone) {
			case 'a':
			case 'A': toArea =1; break;
			case 'b': 
			case 'B': toArea =2; break;
			default: System.out.println("error");}
			System.out.println();
		}

		//prompt user to enter weight
		while(weight<100) {
			System.out.println("Please enter the weight in grams");
			weight = scan.nextInt();
			roundedwgt = ((weight + 50) / 100 ) * 100;
		}	

		//calculate sum
		if (frArea == toArea) {
			OrderSum = 6 + ((roundedwgt-100)/100);}
		else {
			OrderSum = 7 + ((roundedwgt-100)/100);}

		//creating order number
		System.out.println();
		indexNum = ++noOrDis[(OrDispatcher-1)];
		System.out.print("Your order number is ");
		System.out.printf("%d",OrDispatcher);
		System.out.printf("%02d",indexNum);
		System.out.printf("%d",frArea);
		System.out.printf("%d",toArea);

		//display information of the order
		System.out.println();
		System.out.printf("The order is RM %d",	OrderSum );
		System.out.println();
		System.out.println("Index Number: "+ indexNum);
		System.out.println ("Dispatcher: " + dispatch[(OrDispatcher-1)]);
		System.out.println ("From zone "+ sZone);
		System.out.println ("To zone "+ eZone);
		OrderArray[(OrDispatcher-1)][(indexNum-1)]=OrderSum;

		//requesting to add new value
		order = 3;
		while (order !=1 && order !=2) {
			System.out.println("Would you like to add a new order? (1 - yes or 2 - no)");
			order = scan.nextInt();
			if (order!=1 && order!=2) {
				System.out.println("invalid value");
				System.out.println();}
			else if (order == 2) {
				System.out.println();
				MMenu();}
			else {
				newOrder();}}
	}

	public void modify() {
		String OrNum = " ";
		String orderNum;
		int idispatch;
		int orderNum2, len;
		char dispatchs;
		int frArea, toArea, mass = 0, extra,fnum = 0;
		int sum=0, fsum, rounded2, refund=0, modify =1;

		System.out.println();

		do {
			//prompt user to enter order number that needs to be modify
			Scanner scan = new Scanner (System.in);
			System.out.println("Please enter your order number");
			OrNum = scan.nextLine();
			len = OrNum.length();
			dispatchs =  OrNum.charAt(0);
			idispatch = Integer.parseInt("" + dispatchs);
			orderNum ="" + OrNum.charAt(1) + OrNum.charAt(2);
			orderNum2 = Integer.parseInt(orderNum);
			frArea = OrNum.charAt(3);
			toArea = OrNum.charAt(4);

			//validate order number
			if (OrderArray[idispatch-1][orderNum2-1] >0) {
				System.out.println("valid");
				System.out.println("Dispatcher: "+ (dispatch[idispatch-1]));
				System.out.println("Order Number: "+ (orderNum));
				System.out.println("From Area: " +(frArea-48));
				System.out.println("To Area: " +(toArea-48));

				//prompt user to enter new weight
				if (frArea==toArea) {
					while(mass<100) {
					(OrderArray[idispatch-1][orderNum2-1]) -=6;
					System.out.println("Please enter your new weight");
					mass = scan.nextInt();	}
				}
				else {
					while(mass<100) {
					(OrderArray[idispatch-1][orderNum2-1]) -=7;
					System.out.println("Please enter your new weight");
					mass = scan.nextInt();}
				}
				rounded2=((mass + 50) / 100 );
				fnum = rounded2-1;

				//Display new top up amount, same amount or refund amount
				if ((fnum)>(OrderArray[idispatch-1][orderNum2-1])) {
					fnum-=(OrderArray[idispatch-1][orderNum2-1]);
					System.out.println("Your top up amount will be RM "+ fnum);
					if (frArea==toArea) {
						fsum= (6+fnum);}
					else {
						fsum=(7+fnum);}
				}
				else if((fnum)==(OrderArray[idispatch-1][orderNum2-1])){
					System.out.println("There is no change in price");
					if (frArea==toArea) {
						fsum= (6+fnum);}
					else {
						fsum=(7+fnum);}
				}
				else {
					refund = ((OrderArray[idispatch-1][orderNum2-1]) -fnum);
					System.out.println("Your refund will be RM "+ refund);
					if (frArea==toArea) {
						fsum= (6-refund);}
					else {
						fsum=(7-refund);}
				}
				OrderArray[idispatch-1][orderNum2-1]=fsum;}

			//invalid order number
			else if ((OrderArray[idispatch-1][orderNum2-1]) ==0){
				System.out.println(ERROR);
			}
		}while ((OrderArray[idispatch-1][orderNum2-1])<=0);

		modify = 3;
		while (modify !=1 && modify !=2) {
			//prompt user to start a new order
			System.out.println("Would you like to modify another order? (1 - yes or 2 - no)");
			//system scan the input
			modify = scan.nextInt();
			System.out.println();
			if (modify!=1 && modify!=2) {
				System.out.println("invalid value");
				System.out.println();}
			else if (modify == 2) {
				MMenu();}
			else {
				modify();}}
	}
	public void Display() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		//variable for total number of order, groass wage, commission, and total wage
		int TotOrD1 =0, TotOrD2 =0, TotOrD3 =0, TotOrAD=0;
		int GrWaD1 =0, GrWaD2 =0, GrWaD3 =0, ToGrWaD;
		int sum1 =0, sum2=0, sum3=0;
		double CommD1 =0, CommD2 =0,CommD3 =0, ToCommD =0;
		double ToWaD1 =0, ToWaD2 =0, ToWaD3 =0, ToWaD =0;

		System.out.println();

		//display dispatcher and order number
		System.out.printf("%9s %s"," ", "JH Delivery daily report on ");
		System.out.println(formatter.format(date));
		System.out.printf("%s %15s %10s %10s %10s", "Dispatcher", dispatch[0],dispatch[1], dispatch[2], "Total");
		System.out.println();
		for (int o = 0; o<9; o++) {
			System.out.printf("%s %17s %10s %10s ","Order " + Display[o], OrderArray[0][o],OrderArray[1][o], OrderArray[2][o]);//, sumOr);
			System.out.println();
		}
		for (int c = 9; c<15; c++) {
			System.out.printf("%s %16s %10s %10s ","Order " + Display[c], OrderArray[0][c],OrderArray[1][c], OrderArray[2][c]);//, sumOr);
			System.out.println();
		}

		//calculation and display for total number of order
		for (int b = 0; b<15; b++) {
			if (OrderArray[0][b]>0) {
				++TotOrD1;
			}
			if (OrderArray[1][b]>0) {
				++TotOrD2;
			}
			if (OrderArray[2][b]>0) {
				++TotOrD3;
			}
		}
		System.out.println();
		TotOrAD = TotOrD1 + TotOrD2 +TotOrD3;
		System.out.printf("%s %s %10s %10s %10s","Total Number of Order: ", TotOrD1 ,TotOrD2, TotOrD3, TotOrAD );
		System.out.println();

		//calculation and display for gross wage
		GrWaD1 = 3*TotOrD1;
		GrWaD2 = 3*TotOrD2;
		GrWaD3 = 3*TotOrD3;
		ToGrWaD = GrWaD1 + GrWaD2 + GrWaD3;
		System.out.printf("%s %12s %10s %10s %10s","Gross Wage: ", GrWaD1, GrWaD2, GrWaD3, ToGrWaD);
		System.out.println();

		//calculation and display for commission
		for (int a = 0; a<15; a++) {
		sum1 += OrderArray[0][a];
		sum2 += OrderArray[1][a];
		sum3 += OrderArray[2][a];}
		CommD1 = sum1*0.05;
		CommD2 = sum2*0.05;
		CommD3 = sum3*0.05;
		ToCommD = CommD1 + CommD2 + CommD3;
		System.out.printf("%s %13.2f %10.2f %10.2f %10.2f","Commission: ", CommD1, CommD2, CommD3, ToCommD);
		System.out.println();

		//calculation and display for total wages
		ToWaD1 = CommD1+GrWaD1;
		ToWaD2 = CommD2+GrWaD2;
		ToWaD3 = CommD3+GrWaD3;
		ToWaD = ToWaD1 + ToWaD2 + ToWaD3;
		System.out.printf("%s %12.2f %10.2f %10.2f %10.2f","Total Wages: ", ToWaD1, ToWaD2, ToWaD3, ToWaD);
		System.out.println();
		System.out.println();

		//return to menu
		MMenu(); 
	}

	//exit main menu
	public void exitOr() {
		System.out.println("Thank you for using JH Delivery");
	}
}
