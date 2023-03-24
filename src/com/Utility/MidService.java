package com.Utility;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;

import com.Bean.Group;
import com.Bean.ImageData;

public class MidService {
	
	public static int[] windowSize = {1920/2, 1080/2};
//	public static int[] imageSize = {571, 542};
//	public static int[] imageSize = {984, 656};
	public static int[] imageSize = {1920/2, 1080/2};

	public static String URL = "Resource\\";
//	public static String imageName = "david.jpg";
	public static ImageData imageData;
	public static BufferedImage initProcessedImage;
	public static BufferedImage resizedImage;
	
//	public static double[][] processedArray;
	public static ArrayList<Group> groupList = new ArrayList<>();
	
	public static int[] minCoord = new int[2];
	public static int[] maxCoord = new int[2];
	public static Group maxDesnity;
	
	static int temp = 1;
	public static double[] resized = {imageSize[0]/temp, imageSize[1]/temp};
//	public static int minGridAmount = (int) (imageSize[0]*imageSize[1])/100;
	public static int minGridAmount = 100;
	public static int maxGridAmount = (int) (imageSize[0]*imageSize[1]);

//	public static int maxGridAmount = (int) (resized[0]*resized[1]*1/10);
	public static int maxRecursion = 7500;
	
	public static int groupIndex = 0;
	public static int combinationIndex = 0;
	//startX, startY, endX, endY, density
	public static ArrayList<Group> orderedGroup = new ArrayList<>();
	public static Queue<Group> generateGroup = new LinkedList<>();
	
	public static long time1 = System.currentTimeMillis();
	
	public static boolean orderByDensity(Group p) {
		
//		double startX = (double)p.getMinCoord()[0]/resized[0];
//		double startY = (double)p.getMinCoord()[1]/resized[1];
//		double endX = (double)p.getMaxCoord()[0]/resized[0];
//		double endY = (double)p.getMaxCoord()[1]/resized[1];
//		System.out.println(p.getDensity());
		
		//can't have 100% density.  Change depending on situation
		if(p.getDensity() >= 1.0) {
			return true;
		}
//		p.formShape();
		for(int i = 0; i < orderedGroup.size(); i++) {
			
			if(p.getDensity() >= orderedGroup.get(i).getDensity()) {
				
				orderedGroup.add(i, p);	
//				generateGroup.add(p);
				Thread thread = new Thread(new MutiThreadGenerate(p));
				thread.start();
				return true;
			}
		}
		orderedGroup.add(p);
		Thread thread = new Thread(new MutiThreadGenerate(p));
		thread.start();
//		generateGroup.add(p);
//		System.out.println(MidService.generateGroup.size());
		return true;
	}
}
