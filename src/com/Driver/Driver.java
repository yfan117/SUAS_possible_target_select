package com.Driver;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import com.Bean.Group;
import com.Bean.ImageData;
import com.Display.Display;
import com.Utility.FindTarget;
import com.Utility.SingleThreadGenerate;
import com.Utility.MidService;

public class Driver {
	
	public static void main(String[] args) throws IOException, InterruptedException {
				
//		SingleThreadGenerate generateCombination = new SingleThreadGenerate();
//		Thread thread = new Thread(generateCombination);
//		thread.start();
		
		ImageData imageData = new ImageData(MidService.URL+"david.jpg");
		FindTarget ft = new FindTarget(imageData, Color.BLACK, "", "");
		ft.findColor();
		ft.groupSelection();
//	    for(Group g: MidService.orderedGroup) {
//	    	g.generateCombination();
//	    }
//		System.out.println(MidService.generateGroup.size());
//		if(MidService.generateGroup.isEmpty() == true) {
//			generateCombination.exit();
//		}
		
		Display display = new Display();
//		System.out.println("time taken: " +(System.currentTimeMillis() - time1));
//		thread.stop();
		
		while(true) {
			display.render.update();
			
			Thread.sleep(1000/30);
		}

		
	}

}
