package com.Bean;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.Utility.MidService;

public class Group {
	
	private double density = 0.0;
	private int[] minCoord = {Integer.MAX_VALUE, Integer.MAX_VALUE};
	private int[] maxCoord = {Integer.MIN_VALUE, Integer.MIN_VALUE};
//	private int[] minCoord = {0, 0};
//	private int[] maxCoord = {0, 0};
	
	private ArrayList<int[]> points = new ArrayList<>();
	private  ArrayList<Group> groupList = new ArrayList<>();
	private  ArrayList<BufferedImage[]> combination = new ArrayList<>();

	private int weightCounter = 0;
	
	public Group() {
		
	}
	
	private BufferedImage image;
	public void addPoint(int x, int y, double weight) {
		
//		System.out.println()
		if(x < minCoord[0]) {
			minCoord[0] = x;
		}
		if(x > maxCoord[0]) {
			maxCoord[0] = x;
		}
		
		if(y < minCoord[1]) {
			minCoord[1] = y;
		}
		if(y > maxCoord[1]) {
			maxCoord[1] = y;
		}
		points.add(new int[]{x, y});
		weightCounter += weight;
	}
	
	int width;
	int height;
	
	public void generateCombination() {
		image = this.formShape();
		this.groupSelection();
		
		for(Group p: groupList) {
//			p.formShape();
			BufferedImage shape = this.formShape();
			
			for(int[] point: p.getPoints()) {
				shape.setRGB(point[0], point[1], -16777216);
			}
			combination.add(new BufferedImage[]{shape, p.formShape()});
		}
	}
	public BufferedImage formShape() {
		width = maxCoord[0] - minCoord[0]+1;
		height = maxCoord[1] - minCoord[1]+1;
		BufferedImage tempImage = new BufferedImage(width,
								height,
								BufferedImage.TYPE_INT_ARGB);

		for(int[] point: points) {
			tempImage.setRGB(point[0]-minCoord[0], point[1]-minCoord[1], -16777216);
		}
		
		return tempImage;
	}
	
	private int recursiveCounter = 0;
	private Queue<int[]> recursionQueue= new LinkedList<>();
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
//					group.findDensity();
					groupList.add(group);
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
			if(image.getRGB(x, y) == 0) 
			{
				group.addPoint(x, y, image.getRGB(x, y));
//				MidService.initProcessedImage.setRGB(x, y, -16777216);
				image.setRGB(x, y, -16777216);
			
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
			if(image.getRGB(x, y) == 0) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void findDensity() {
		
		double gridAmount = (maxCoord[0] - minCoord[0] + 1) * (maxCoord[1] - minCoord[1] + 1);
		this.density = (double)weightCounter / gridAmount;
		
		if(gridAmount < MidService.minGridAmount || gridAmount > MidService.maxGridAmount) {
			this.density = 2;
		}
		
//		this.density = (double)colorCounter;
	}
	

	
	public double getDensity() {
		return this.density;
	}
	
	public int[] getMaxCoord() {
		return this.maxCoord;
	}
	
	public int[] getMinCoord() {
		return this.minCoord;
	}
	
	public ArrayList<int[]> getPoints() {
		return this.points;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	public ArrayList<BufferedImage[]> getCombination() {
		return combination;
	}
}
