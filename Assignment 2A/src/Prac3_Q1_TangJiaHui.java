import java.io.File;
import java.util.Scanner;

public class Prac3_Q1_TangJiaHui {
	public static void main (String [] args) throws Exception{
		String name;
		int result1 = 0;
		int result2 = 0;
		double average;
		String status = null;

		//print headline
		System.out.printf("%s %2s %10s %7s %9s", "No.", "Name", "Test 1", "Test 2", "Status");
		System.out.println();
		System.out.println("---------------------------------------");

		//construct file object and scanner object
		File file = new File("studMark.txt");
		Scanner scan = new Scanner (file);

		for (int cnt=1; cnt<=10; cnt++) {
			//scanner scan data
			name = scan.next();
			result1 = scan.nextInt();
			result2 = scan.nextInt();
			//calculate average
			average = ((result1 + result2)/2);
			//status, if average less than 70 print fail
			if (average >=70)
				status ="Pass";
			else if (average <=70)
				status ="Fail";
			//prints name, results and status
			System.out.printf("%-3s %-7s %5d %7s %10s", cnt, name, result1, result2, status);
			System.out.println();
		}
	}
}
