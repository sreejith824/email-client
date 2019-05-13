package com.dxc.tech.interview.starstring;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
        System.out.println(starString(Integer.parseInt(args [0])));
        System.out.println("done");
    }
    
    public static String starString(int n) throws Exception{
    	if (n < 0) {
    		throw new Exception("Not a valid input");
    	}
        if (n == 0) {
          return "*";
        }
        else {
        	String s = starString(n-1);
          return s + s;
        }
      }
}
