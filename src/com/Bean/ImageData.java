package com.Bean;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Utility.MidService;

public class ImageData {
	
	private BufferedImage image;
	private int[][] imageArray;
	private int[] size = {0, 0};
//	private int[] resizedSize = {0, 0};
	
	public ImageData(String imageName) throws IOException {
		
//		File file = new File(imageName);
////		System.out.println(System.getProperty("user.dir"));
//		System.out.println(file.getPath());

		image = ImageIO.read(new FileInputStream(imageName));
		
//		size[0] = image.getWidth();
//		size[1] = image.getHeight();
		
		int newW = (int)MidService.resized[0];
		int newH = (int)MidService.resized[1];
		size[0] = newW;
		size[1] = newH;
		
	    MidService.resizedImage = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D bGr =MidService.resizedImage.createGraphics();
	    bGr.drawImage(image, 0, 0, newW, newH, null);
	    bGr.dispose();
		
	    
	    imageArray = new int[newW][newH];
		for(int x = 0; x < newW; x++) {
			for(int y = 0; y < newH; y++) {
				imageArray[x][y] = MidService.resizedImage.getRGB(x, y);
			}
		}
		
		
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int[][] getImageArray() {
		return imageArray;
	}

	public void setImageArray(int[][] imageArray) {
		this.imageArray = imageArray;
	}

	public int[] getSize() {
		return size;
	}
	
//	public int[] getResizedSize() {
//		return resizedSize;
//	}


	public void setSize(int[] size) {
		this.size = size;
	}
	
	
	

}
