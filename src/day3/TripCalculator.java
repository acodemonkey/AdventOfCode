package day3;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;

public class TripCalculator {

	HashMap<String, Integer> stopCoords;
	
	static final Character UP_SYMBOL = '^';
	static final Character DOWN_SYMBOL = 'v';
	static final Character LEFT_SYMBOL = '<';
	static final Character RIGHT_SYMBOL = '>';
	static final Integer X_COORD = 0;
	static final Integer Y_COORD = 1;
	
	TripCalculator(){
		stopCoords = new HashMap<String,Integer>();
	}
	
	String _getNewCoordinates(String coordinate, Character direction){
		
		String[] currentCoords = coordinate.split(",");
		Integer xCoord = Integer.parseInt(currentCoords[X_COORD]);
		Integer yCoord = Integer.parseInt(currentCoords[Y_COORD]);
		
		if(direction == UP_SYMBOL){
			yCoord += 1;
		} else if(direction == DOWN_SYMBOL){
			yCoord -= 1;
		} else if(direction == LEFT_SYMBOL){
			xCoord -= 1;
		} else {//going right
			xCoord += 1;
		}
		
		return xCoord + "," + yCoord;
		
	}
	
	String getTripPath(String inputFile){
		
		File file = new File(inputFile);
		StringBuilder stops = new StringBuilder();
		
		try{
			
			Scanner inputScanner = new Scanner(file);
			while(inputScanner.hasNextLine()){
				stops.append(inputScanner.nextLine());
			}
			inputScanner.close();
		
		} catch(Exception e){
			
			System.out.println("Input file exploded.");
			e.printStackTrace();
			
		}
		return stops.toString();
	}
	
	String[] divideTrips(String tripInfo, Integer workers){
		//assuming that all workers divide stops round-robin style
		ArrayList<ArrayList<Character>> dividedTrips = new ArrayList<ArrayList<Character>>();
		
		//initialize everything
		for(int i = 0; i < workers; ++i){
			dividedTrips.add(new ArrayList<Character>());
		}
		
		//split up instructions
		for(int j = 0; j < tripInfo.length(); ++j){
			
			Character instruction = tripInfo.charAt(j);
			
			ArrayList<Character> workerInstructions = dividedTrips.get(j % workers);
			workerInstructions.add(instruction);
			
		}
		
		//condense results
		String[] result = new String[workers];
		for(int k = 0; k < workers; ++k){
			ArrayList<Character> workerInstructions = dividedTrips.get(k);
			StringBuilder instructionBuilder = new StringBuilder();
			for( Character instruction : workerInstructions){
				instructionBuilder.append(instruction);
			}
			result[k] = instructionBuilder.toString();
		}
		
		return result;
		
	}
	
	Integer calculateStops(String tripPath){
		
		int stops = 0;
		
		char[] tripStops = new char[tripPath.length()];
		tripPath.getChars(0,tripPath.length(), tripStops, 0);
		
		//assuming that Santa stopped at the first house already
		String currentStop = "0,0";
		stopCoords.put(currentStop, 1);
		Integer presentsDropped;
		for(char nextStop : tripStops){
		
			currentStop = _getNewCoordinates(currentStop, nextStop);
			if(stopCoords.containsKey(currentStop)){
				presentsDropped = stopCoords.get(currentStop) +1;
			} else {
				presentsDropped = 1;
			}

			stopCoords.put(currentStop, presentsDropped);
		}
		
		stops = stopCoords.keySet().size();
		
		return stops;
		
	}
	
	Integer calculateStops(String[] tripPaths){
		
		for(String tripPath : tripPaths){
			calculateStops(tripPath);
		}
		return stopCoords.keySet().size();
	}
	
	public static void main(String[] args){
		
		TripCalculator santaCalculator = new TripCalculator();
		System.out.println("Calculating stops for one Santa.");
		System.out.println(santaCalculator.calculateStops(santaCalculator.getTripPath("resources/day3.txt")));
		
		System.out.println("Calculating stops for Santa as he takes turns with a helper.");
		TripCalculator santaCalculator2 = new TripCalculator();
		String fullInstructionList = santaCalculator2.getTripPath("resources/day3.txt");
		String[] dividedInstructions = santaCalculator2.divideTrips(fullInstructionList, 2);
		System.out.println(santaCalculator2.calculateStops(dividedInstructions));

	
	}
	
}
