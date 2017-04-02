package com.exist.employee;

public class InputManager {

	private static java.util.Scanner scan = new java.util.Scanner(System.in);
	
	public static int getPositiveNumber(String str) throws Exception {
		System.out.print("Enter a non-negative integer for "+str+" : ");
		String X = scan.nextLine();
		int i = 0;
		
		if(isNumeric(X) && !X.isEmpty() && Integer.parseInt(X) >= 0) {
			i = Integer.parseInt(X);
		}
		else {
			throw new Exception();
		}
		return i;
	}
	
	public static String enterKey(String name) {
		System.out.println("Enter "+ name +" key: ");
		String str = scan.nextLine();
		String[] split = str.split(" ");
		
		return str = (str.isEmpty()) ? ("null") : String.join("",split);
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
	
	public static float getPositiveFloat(String str) throws Exception {
		System.out.print("Enter a non-negative float for "+str+" : ");
		return scan.nextFloat();
	}
	
	public static boolean getBoolean(String str) throws Exception {
		System.out.print("IS "+str+" : ");
		return scan.nextBoolean();
	}
}	
