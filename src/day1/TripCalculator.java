package day1;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class TripCalculator {
	
	public static final Character UP_SYMBOL = '(';
	public static final Character DOWN_SYMBOL = ')';
	
	public static int calculate(String inputFile){
		
		//load input stuffs
		File file = new File(inputFile);
		StringBuilder stops = new StringBuilder("");

		try{
			
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				stops.append(line);
			}
			scanner.close();
			
		} catch(Exception e){
			System.out.println("File exploded. No idea why.");
			e.printStackTrace();
		}
		
		//figure out how many of each stop type Santa visited
		char[] allStops = new char[stops.length()];
		stops.getChars(0, stops.length(), allStops, 0);
		HashMap<Character, Integer> stopTypes = new HashMap<Character, Integer>();
		for( int i = 0; i < allStops.length; ++i){
			char currentStop = allStops[i];
			if(stopTypes.containsKey(currentStop)){
				stopTypes.put(currentStop, stopTypes.get(currentStop)+1);
			}
			else {
				stopTypes.put(currentStop, 1);
			}
		}

		//calculate where Santa ended up
		//TODO make this configurable
		
		int floorsUp = 0;
		if(stopTypes.containsKey(UP_SYMBOL)){
			floorsUp = stopTypes.get(UP_SYMBOL);
		}
		
		int floorsDown = 0;
		if(stopTypes.containsKey(DOWN_SYMBOL)){
			floorsDown = stopTypes.get(DOWN_SYMBOL);
		}
		
		return floorsUp - floorsDown;
	}
	
	public static void main(String[] args){
		System.out.println(calculate("resources/day1.txt"));
		
		System.out.println("Math test.");
		System.out.println(47/3);
	}
	
}
