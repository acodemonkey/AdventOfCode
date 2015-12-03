package day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class WrappingCalculator {

	public static int calculate(String inputFile) throws Exception{
		File file = new File(inputFile);
		FileInputStream inputStream = new FileInputStream(file);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
	 
		String line = null;
		int totalArea = 0;
		while ((line = buffer.readLine()) != null) {
			
			String dimensionString = line;
			String[] dimensionParts = dimensionString.split("x");
			
			if(dimensionParts.length == 3){
				Integer length = Integer.parseInt(dimensionParts[0]);
				Integer width = Integer.parseInt(dimensionParts[1]);
				Integer height = Integer.parseInt(dimensionParts[2]);
				
				Integer[] sideAreas = new Integer[3];
				sideAreas[0] = length * width;
				sideAreas[1] = length * height;
				sideAreas[2] = width * height;
				
				//extra slack is the area of the smallest side
				Integer slack;
				if(sideAreas[0] <= sideAreas[1] && sideAreas[0] <= sideAreas[2]){
					slack = sideAreas[0];
				}else if(sideAreas[1] <= sideAreas[0] && sideAreas[1] <= sideAreas[2]){
					slack = sideAreas[1];
				} else {
					slack = sideAreas[2];
				}
				
				totalArea += slack + sideAreas[0]*2 + sideAreas[1]*2 + sideAreas[2]*2;
			} else {
				throw new Exception("Something is wrong with the input.");
			}
			
		}

		buffer.close();
	 
		return totalArea;
		
	
	}
	
	public static void main(String[] args){
		try {
			System.out.println(calculate("resources/day2.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went horribly horribly wrong!");
			e.printStackTrace();
		}
	}
	
}
