import java.util.Arrays;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Number {
	
	final int hexBit=32;
	final int binaryBit=2;
	final char[] hexDigits= {'0','1','2', '3','4','5', '6','7', '8','9','A', 'B', 'C','D','E','F'};
	
	public Number(){
	}
	
	/*Takes a decimal number and converts into binary*/
	public String convertToBinary(int decimal){
		int answer = java.lang.Math.abs(decimal); //absolute value of decimal number
		String binary="";
		String remainder;
		while (answer!=0){
			int rem = answer%binaryBit;
			if (rem>9)
				remainder = Character.toString(hexDigits[rem]);
			else
				remainder = Integer.toString(rem);
			binary = remainder + binary;
			answer=answer/binaryBit;
		}
		int length = binary.length( ); //how long binary is
		while (length<hexBit){ //keep the binary number as 32 bit
			binary='0'+ binary;
			length=binary.length();
		}
		if (decimal<=0){ //if number is negative;
			binary=twosComplement(binary);
		}
		return binary; 	
	}
	
	/*Converts a 32 bit binary string and gives the twos complement*/
	public String twosComplement(String binary){
		char[] binaryArray = binary.toCharArray();
		for (int i=0; i<binaryArray.length;i++){
			if (binaryArray[i]=='0')
				binaryArray[i]='1';
			else if (binaryArray[i]=='1')
				binaryArray[i]='0';
		}
		String twos = String.copyValueOf(binaryArray);
		int ind=1;
		while (twos.charAt(twos.length()-ind)!='0'){ 
			twos=twos.substring(0,twos.length()-ind) + '0' + twos.substring(twos.length()-ind+1);
			ind++;
		}
		//add one
		twos=twos.substring(0,twos.length()-ind) +'1'+twos.substring(twos.length()-ind+1);
		return (twos);
	}
	
	/*This is a helper function for convertToHex*/
	public char getHexValue(char a, char b, char c, char d){
		int sum = 0;
		if (a == '1')
			sum +=8; 
		if (b == '1')
			sum +=4;
		if (c == '1')
			sum +=2; 
		if (d == '1')
			sum +=1;
		char hexValue = hexDigits[sum];
		return hexValue;
	}
	
	/*converts a number from decimal to hex*/
	public String convertToHex(int decimal){
		String binary = convertToBinary(decimal);
		char[] bC = binary.toCharArray(); //binary character array
		char[] d  = new char[8];
		for (int i=0; i<d.length; i++){
			d[i]=getHexValue(bC[4*i],bC[4*i+1],bC[4*i+2],bC[4*i+3]);
		}
		String text = String.copyValueOf(d);
		return text;
	}
	
	
	/*This method returns a decimal number from a hex.*/
	public int convertToDecimal(String hexNumber){
		char[] hex = hexNumber.toCharArray(); 
		int[] hexValue= new int[8];
		double sum=0.0;
		for (int i =0; i<hex.length; i++){
			if (hex[i]=='0')
				hexValue[i]=0;
			else if (hex[i]=='1')
				hexValue[i]=1;
			else if (hex[i]=='2')
				hexValue[i]=2;
			else if (hex[i]=='3')
				hexValue[i]=3;
			else if (hex[i]=='4')
				hexValue[i]=4;
			else if (hex[i]=='5')
				hexValue[i]=5;
			else if (hex[i]=='6')
				hexValue[i]=6;
			else if (hex[i]=='7')
				hexValue[i]=7;
			else if (hex[i]=='8')
				hexValue[i]=8;
			else if (hex[i]=='9')
				hexValue[i]=9;
			else if (hex[i]=='A')
				hexValue[i]=10;
			else if (hex[i]=='B')
				hexValue[i]=11;
			else if (hex[i]=='C')
				hexValue[i]=12;
			else if (hex[i]=='D')
				hexValue[i]=13;
			else if (hex[i]=='E')
				hexValue[i]=14;
			else
				hexValue[i]=15;
		}
		for (int i=0;i<8;i++)
			sum= sum + Math.pow(16,i)*hexValue[7-i];
		int decimalEquiv = (int)sum;
		return decimalEquiv;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Number num = new Number();
		System.out.println("PROBLEM 1");
		System.out.println("EX1:Converting 657 (in decimal) to binary");
		System.out.println(num.convertToBinary(657));
		System.out.println("---------------------");
		System.out.println("EX:Coverting -784 (in decimal) to binary");
		System.out.println(num.convertToBinary(-784));
		System.out.println("---------------------");
		System.out.println("EX:Coverting 657 (in decimal) to hex");
		System.out.println(num.convertToHex(657));
		System.out.println("---------------------");
		System.out.println("EX:Coverting -784 (in decimal) to hex");
		System.out.println(num.convertToHex(-784));
		System.out.println("---------------------");
		System.out.println("PROBLEM 2 : Converting from hex (0004F3A3) to decimal" );
		System.out.println(num.convertToDecimal("0004F3A3"));

	}

}
