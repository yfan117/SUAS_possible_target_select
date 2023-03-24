package com.Utility;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

import com.Bean.Group;
import com.Bean.ImageData;

public class FindTarget {
	
	Color color;
	String shape;
	String character;
	double[][] processedArray;
	ImageData imageData;
	int height;
	int width;
	Queue<int[]> recursionQueue= new LinkedList<>();
	int recursiveCounter = 0;
	
	public FindTarget(ImageData imageData, Color color, String shape, String character) {
		
		MidService.imageData = imageData;
		this.imageData = imageData;
		this.color = color;
		this.shape = shape;
		this.character = character;
		
		width = this.imageData.getSize()[0];
		height = this.imageData.getSize()[1];
	}
	
	public void findColor() {
		
		int tolerance = 255/3;
		
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		
		int maxColor = 0;
		if(red >= green && red >= blue) {
			maxColor = 0;
		}
		else if(green >= red && green >= blue) {
			maxColor = 1;
		}
		else if(blue >= red && blue >= green) {
			maxColor = 2;
		}
		
		int[][] imageArray = this.imageData.getImageArray();
		MidService.initProcessedImage = new BufferedImage((int)MidService.resized[0],
															(int)MidService.resized[1], 
															BufferedImage.TYPE_INT_ARGB);
		
//		int width = this.imageData.getSize()[0];
//		int height = MidService.imageData.getSize()[1];
		processedArray = new double[width][height];
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
		    
			int hex = imageArray[x][y];
		    int r = (hex & 0xFF0000) >> 16;
		    int g = (hex & 0xFF00) >> 8;
		    int b = (hex & 0xFF);
		    
			int tempMax = 0;
			if(r >= g && r >= b) {
				tempMax = 0;
			}
			else if(g >= r && g >= b) {
				tempMax = 1;
			}
			else if(b >= r && b >= g) {
				tempMax = 2;
			}
		    
			if(tempMax == maxColor) {
				
			    if(r <= red+tolerance && r >= red-tolerance
				    &&
				    g <= green+tolerance && g >= green-tolerance
				    &&
				    b <= blue+tolerance && b >= blue-tolerance) {

					processedArray[x][y] = 1;
				    }
				}
			}

		}	
	}
	
	public void groupSelection() {
		
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				
				if(isValid(x, y) == true) {
					recursionQueue.add(new int[]{x, y});
					Group group = new Group();

					while(recursionQueue.isEmpty() == false) {
						recursiveCounter = 0;
						int[] tempCoord = recursionQueue.poll();
						recursiveDectect(group, tempCoord[0], tempCoord[1]);
					}
					group.findDensity();
					MidService.orderByDensity(group);
//					System.out.println(MidService.generateGroup.size());
				}
			} 
		}
	}
	
	
	int[][] direction = {{-1, 0},{0, -1},{1, 0},{0, 1}};
	public boolean recursiveDectect(Group group, int x, int y) {

		if(recursiveCounter > MidService.maxRecursion) {
			return false;
		}
		if(x >= 0 && x < width && y >= 0 && y <height) 
		{
			if(processedArray[x][y] != 0) 
			{
				group.addPoint(x, y, processedArray[x][y]);
				MidService.initProcessedImage.setRGB(x, y, -16777216);
				processedArray[x][y] = 0;
			
				for(int[] element: direction)
				{
					int newX = x + element[0];
					int newY = y + element[1];
					recursionQueue.add(new int[]{newX, newY});
					recursiveCounter++;
					recursiveDectect(group, newX, newY);
				}
			}
		}
		
		return true;
	}
	
	public boolean isValid(int x, int y) {
		
		if(x >= 0 && x < width && y >= 0 && y <height) {
			if(processedArray[x][y] == 1) {
				return true;
			}
		}
		
		return false;
	}

}
