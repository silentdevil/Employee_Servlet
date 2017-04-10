package com.exist.employee;

public class InputManager {

	private static java.util.Scanner scan = new java.util.Scanner(System.in);
	
	public static int getPositiveNumber(String str, String identifier) throws Exception {
		String X;
		int i = 0;
		do {
			System.out.print("Enter a non-negative integer for "+str+" : ");
			X = scan.nextLine();
			
			if(isNumeric(X) && !X.isEmpty() && Integer.parseInt(X) >= 0) {
				i = Integer.parseInt(X);
			}
			else {
				i = 0;
			}
		} while((X == null || X.isEmpty()) && identifier.equals("EMPTY_NOT_ALLOWED"));		
		return i;
	}

	public static String enterString(String str, String identifier) {
		String output = "";
		do{
			System.out.println("Enter "+str+" :");
			output = scan.nextLine();
		}  while((output == null || output.isEmpty()) && identifier.equals("EMPTY_NOT_ALLOWED"));
		
		return output;
	}
	
	public static boolean isNumeric(String s){ 
		try {
			int i = Integer.parseInt(s);
			return true;
		} catch(NumberFormatException ex) {
			return false;
		}
	}
	
	public static float getPositiveFloat(String str, String identifier) throws Exception {
		String X = null;
		float i = 0;
		
		do {
			try {
				System.out.print("Enter a float for "+str+" : ");
				X = scan.nextLine();
				i = (Float.parseFloat(X) >= 0) ? Float.parseFloat(X) : 0;

			} catch(Exception ex){
				System.err.println("Not valid float");
			}
		} while((X == null || X.isEmpty()) && identifier.equals("EMPTY_NOT_ALLOWED"));		
		return i;
	}
	
	public static boolean getBoolean(String str) throws Exception {
		System.out.print("IS "+str+" : ");
		String bool = scan.nextLine().toUpperCase();
		return (bool.toUpperCase().matches("Y|YES|T|TRUE"));
	}

	public static void output(String display) {
		System.out.println(display);
		scan.nextLine();
	}
}	
