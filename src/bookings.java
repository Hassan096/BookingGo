import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class bookings {
    public static void main(String[] args) throws Exception {
    	
      String inputLine = "";
      String inputLine2 = "";
      String inputLine3 = "";
      String totalInput = "";
      String CurrentLine;
      Object pricePrevious = "999999999";
      Object priceNow = "999999999";
      Object carAvailable = null;
      Object supplierID = null;
      Object pricePrevious1 = "999999999";
      Object priceNow1 = "999999999";
      Object carAvailable1 = null;
      Object supplierID1 = null;
      Object pricePrevious2 = "999999999";
      Object priceNow2 = "999999999";
      Object carAvailable2 = null;
      Object supplierID2 = null;
      Object pricePrevious3 = "999999999";
      Object priceNow3 = "999999999";
      Object carAvailable3 = null;
      Object supplierID3 = null;
      Object pricePrevious4 = "999999999";
      Object priceNow4 = "999999999";
      Object carAvailable4 = null;
      Object supplierID4 = null;
      Object pricePrevious5 = "999999999";
      Object priceNow5 = "999999999";
      Object carAvailable5 = null;
      Object supplierID5 = null;
      BufferedReader br = null;

      //take pickup and dropoff locations as arguments
      String pickup = args[0];
      String dropoff = args[1];
      String numberOfPassengers = args[2];
      int noOfPassengers = Integer.parseInt(numberOfPassengers);
           
      //define the URL for connection
      URL myURL = new URL("https://techtest.rideways.com/dave/" + "?pickup=" + pickup + "&dropoff=" + dropoff);
      URL myURL2 = new URL("https://techtest.rideways.com/eric/" + "?pickup=" + pickup + "&dropoff=" + dropoff);
      URL myURL3 = new URL("https://techtest.rideways.com/jeff/" + "?pickup=" + pickup + "&dropoff=" + dropoff);
      
      //open the connection to the URL
      HttpURLConnection connection = (HttpURLConnection)myURL.openConnection();
      connection.setConnectTimeout(2000);
      HttpURLConnection connection2 = (HttpURLConnection)myURL2.openConnection();
      connection2.setConnectTimeout(2000);
      HttpURLConnection connection3 = (HttpURLConnection)myURL3.openConnection();
      connection3.setConnectTimeout(2000);
      
      //Get the response code
      int responseCode = connection.getResponseCode();
      int responseCode2 = connection2.getResponseCode();
      int responseCode3 = connection3.getResponseCode();
      
      //System.out.println("The response code is " + responseCode2);
      
      //if connection fails throw an exception
      if (responseCode != 200)
      {
    	  throw new RuntimeException("Response code is: " +responseCode);
      }
      
      else{
    	    Scanner scan = new Scanner(myURL.openStream());
    	    while (scan.hasNext())
    	        inputLine += scan.nextLine();
    	    	//System.out.println(inputLine);
    	    scan.close();
      }
      
      //if connection fails throw an exception
      if (responseCode2 != 200)
      {
    	  throw new RuntimeException("Response code is: " +responseCode2);
      }
      
      else{
    	    Scanner scan = new Scanner(myURL2.openStream());
    	    while (scan.hasNext())
    	        inputLine2 += scan.nextLine();
    	    	//System.out.println(inputLine2);
    	    scan.close();
      }
      
      //if connection fails throw an exception
      if (responseCode3 != 200)
      {
    	  throw new RuntimeException("Response code is: " +responseCode3);
      }
      
      else{
    	    Scanner scan = new Scanner(myURL3.openStream());
    	    while (scan.hasNext())
    	        inputLine3 += scan.nextLine();
    	    	//System.out.println(inputLine3);
    	    scan.close();
      }
      
      //combine all the data from the three apis and put lines inbetween so we can parse them
      totalInput = inputLine + "\n" + inputLine2 + "\n" + inputLine3;
      System.out.println(totalInput);

      try{
    	  
    	  Reader inputString = new StringReader(totalInput);
    	  br = new BufferedReader(inputString);
    	  //feed the data line by line to the parser
    	  while ((CurrentLine = br.readLine()) != null) {
    	  //parse the combines data
    		  JSONParser parser = new JSONParser();
      
    		  JSONObject object = (JSONObject) parser.parse(CurrentLine);
    		  JSONArray optionsArray = (JSONArray) object.get("options");
    		  ArrayList<JSONObject> list = new ArrayList<JSONObject>();

    		  for (int i = 0; i < optionsArray.size(); i++) {
    			  list.add((JSONObject) optionsArray.get(i));
    		  }//for
          
    		  Collections.sort(list, new JSONComparator());

    		  for (JSONObject obj : list) {
    			  Object o = obj.get("car_type");
        	  
    			  if(o.equals("STANDARD") && noOfPassengers <= 4){
    				  priceNow = obj.get("price");
    				  if(Integer.parseInt(String.valueOf(priceNow)) < Integer.parseInt(String.valueOf(pricePrevious))){
    					  pricePrevious = priceNow;
    					  carAvailable = String.valueOf(obj.get("car_type"));
    					  supplierID = String.valueOf(object.get("supplier_id"));
        			  	}//if
        	  	  	}//if
    			  
    			  else if(o.equals("EXECUTIVE") && noOfPassengers <= 4){
    				  priceNow1 = obj.get("price");
    				  if(Integer.parseInt(String.valueOf(priceNow1)) < Integer.parseInt(String.valueOf(pricePrevious1))){
    					  pricePrevious1 = priceNow1;
    					  carAvailable1 = String.valueOf(obj.get("car_type"));
    					  supplierID1 = String.valueOf(object.get("supplier_id"));
        			  	}//if
        	  	  	}//if
    			  
    			  else if(o.equals("LUXURY") && noOfPassengers <= 4){
    				  priceNow2 = obj.get("price");
    				  if(Integer.parseInt(String.valueOf(priceNow2)) < Integer.parseInt(String.valueOf(pricePrevious2))){
    					  pricePrevious2 = priceNow2;
    					  carAvailable2 = String.valueOf(obj.get("car_type"));
    					  supplierID2 = String.valueOf(object.get("supplier_id"));
        			  	}//if
        	  	  	}//if
        	  
    			  else if (o.equals("PEOPLE_CARRIER") && noOfPassengers <= 6){
    				  priceNow3 = obj.get("price");
    				  if(Integer.parseInt(String.valueOf(priceNow3)) < Integer.parseInt(String.valueOf(pricePrevious3))){
    					  pricePrevious3 = priceNow3;
    					  carAvailable3 = String.valueOf(obj.get("car_type"));
    					  supplierID3 = String.valueOf(object.get("supplier_id"));
    				  	}//if
            	  	}//else if
    			  
    			  else if (o.equals("LUXURY_PEOPLE_CARRIER") && noOfPassengers <= 6){
    				  priceNow4 = obj.get("price");
    				  if(Integer.parseInt(String.valueOf(priceNow4)) < Integer.parseInt(String.valueOf(pricePrevious4))){
    					  pricePrevious4 = priceNow4;
    					  carAvailable4 = String.valueOf(obj.get("car_type"));
    					  supplierID4 = String.valueOf(object.get("supplier_id"));
    				  	}//if
            	  	}//else if
        	  
    			  else if (o.equals("MINIBUS") && noOfPassengers <= 16){
    				  priceNow5 = obj.get("price");
    				  if(Integer.parseInt(String.valueOf(priceNow5)) < Integer.parseInt(String.valueOf(pricePrevious5))){
    					  pricePrevious5 = priceNow5;
    					  carAvailable5 = String.valueOf(obj.get("car_type"));
    		    		  supplierID5 = String.valueOf(object.get("supplier_id"));
        			  	}//if
            	  	}//else if
    		  }//for
    	  }//while
    	  
    	  	if (Integer.parseInt(String.valueOf(pricePrevious)) != 999999999){       		  
    	  			System.out.println("\nThe available taxis and prices are:");    	  			
    	  			System.out.println("\nCar Type: " + carAvailable);
    	  			System.out.println("Supplier ID: " + supplierID);
    	  			System.out.println("Price: " + pricePrevious);
    	  	}
    	  	if (Integer.parseInt(String.valueOf(pricePrevious1)) != 999999999){    	  			
    	  			System.out.println("\nCar Type: " + carAvailable1);
    	  			System.out.println("Supplier ID: " + supplierID1);
    	  			System.out.println("Price: " + pricePrevious1);
    	  	}
    	  	
    	  	if (Integer.parseInt(String.valueOf(pricePrevious2)) != 999999999){    	  			
    	  			System.out.println("\nCar Type: " + carAvailable2);
    	  			System.out.println("Supplier ID: " + supplierID2);
    	  			System.out.println("Price: " + pricePrevious2);
    	  	}
    	  	if (Integer.parseInt(String.valueOf(pricePrevious3)) != 999999999){    	  			
    	  			System.out.println("\nCar Type: " + carAvailable3);
    	  			System.out.println("Supplier ID: " + supplierID3);
    	  			System.out.println("Price: " + pricePrevious3);
    	  	}
    	  	if (Integer.parseInt(String.valueOf(pricePrevious4)) != 999999999){    	  			
    	  			System.out.println("\nCar Type: " + carAvailable4);
    	  			System.out.println("Supplier ID: " + supplierID4);
    	  			System.out.println("Price: " + pricePrevious4);
    	  	}
    	  	if (Integer.parseInt(String.valueOf(pricePrevious5)) != 999999999){	  			
    	  			System.out.println("\nCar Type: " + carAvailable5);
    	  			System.out.println("Supplier ID: " + supplierID5);
    	  			System.out.println("Price: " + pricePrevious5);    	  	
    	  	}//if
      } //try  
      catch (Exception e) {
            e.printStackTrace();
        }//catch    
  }//static void
}//class
    
  //compare the prices and return in descending order
  class JSONComparator implements Comparator<JSONObject> {

  @Override
  	public int compare(JSONObject object1, JSONObject object2) {
	  	String one = (String.valueOf(object1.get("price"))); object1.get("price");
	  	String two = (String.valueOf(object2.get("price"))); object2.get("price");
	  	return two.compareTo(one);
  }   
 }



 