package com.Display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

import com.Bean.Group;
import com.Bean.ImageData;
import com.Utility.MidService;

public class Render extends JPanel{
	
	public Render() throws IOException {
		this.setBackground(Color.DARK_GRAY);
//		MidService.imageData = new ImageData(MidService.URL+MidService.imageName);
	}
	
	public void update() {
		repaint();
	}
	
	Graphics2D g2d;
    public void paintComponent(Graphics g) {
    	g2d = (Graphics2D)g;
        super.paintComponent(g2d);
        
//        g2d.drawImage(MidService.imageData.getImage(),
//        		0,
//        		0,
//        		MidService.imageSize[0],
//        		MidService.imageSize[1],
//        		null);
        
//        g2d.drawImage(MidService.resizedImage,
//        		0,
//        		0,
//        		MidService.resizedImage.getWidth(),
//        		MidService.resizedImage.getHeight(),
//        		null);
        
        g2d.drawImage(MidService.initProcessedImage,
        		0,
        		0,
        		MidService.imageSize[0],
        		MidService.imageSize[1],
        		null);
        
        if(MidService.orderedGroup.size() != 0) {
	        g2d.setColor(Color.GREEN);
	        g2d.setStroke(new BasicStroke(4));
        	int i = MidService.groupIndex;
        	g2d.drawRect((int)((MidService.orderedGroup.get(i).getMinCoord()[0])),
			    		(int)((MidService.orderedGroup.get(i).getMinCoord()[1])), 
			    		-(int)((MidService.orderedGroup.get(i).getMinCoord()[0]))+MidService.orderedGroup.get(i).getMaxCoord()[0], 
			    		-(int)((MidService.orderedGroup.get(i).getMinCoord()[1]))+MidService.orderedGroup.get(i).getMaxCoord()[1]);
		    g2d.drawString(String.valueOf(i), 
		    		(int)((MidService.orderedGroup.get(i).getMinCoord()[0])),
		    		(int)((MidService.orderedGroup.get(i).getMinCoord()[1])));
	       
		    try {
		    BufferedImage[] combination = MidService.orderedGroup.get(i).getCombination().get(MidService.combinationIndex);
//		    g2d.drawRect(MidService.imageSize[0] + 50,
//			    		0,
//		        		combination[0].getWidth()*2,
//		        		combination[0].getHeight()*2);
		    
		    g2d.drawImage(combination[0],
		    		MidService.imageSize[0] + 50,
	        		0,
	        		combination[0].getWidth()*2,
	        		combination[0].getHeight()*2,
	        		null);
		    
//		    g2d.drawRect(MidService.imageSize[0] + 50,
//		    		combination[0].getHeight()*2 + 50,
//	        		combination[1].getWidth()*2,
//	        		combination[1].getHeight()*2);
		    
		    g2d.drawImage(combination[1],
		    		MidService.imageSize[0] + 50,
		    		combination[0].getHeight()*2 + 50,
	        		combination[1].getWidth()*2,
	        		combination[1].getHeight()*2,
	        		null);
		    }catch(Exception e) {
		    	
		    }

//		    BufferedImage shape = MidService.orderedGroup.get(i).getShape();
//		    g2d.drawImage(shape,
//		    		MidService.imageSize[0] + 50,
//	        		0,
//	        		shape.getWidth()*5,
//	        		shape.getHeight()*5,
//	        		null);
        }

        
//        if(MidService.orderedGroup.size() != 0) {
//	        g2d.setColor(Color.GREEN);
//	        g2d.setStroke(new BasicStroke(4));
//	        
//			for(int i = 0 ; i < MidService.orderedGroup.size(); i++) {
//	        	g2d.drawRect((int)((MidService.orderedGroup.get(i).getMinCoord()[0])),
//				    		(int)((MidService.orderedGroup.get(i).getMinCoord()[1])), 
//				    		-(int)((MidService.orderedGroup.get(i).getMinCoord()[0]))+MidService.orderedGroup.get(i).getMaxCoord()[0], 
//				    		-(int)((MidService.orderedGroup.get(i).getMinCoord()[1]))+MidService.orderedGroup.get(i).getMaxCoord()[1]);
//			    g2d.drawString(String.valueOf(i), 
//			    		(int)((MidService.orderedGroup.get(i).getMinCoord()[0])),
//			    		(int)((MidService.orderedGroup.get(i).getMinCoord()[1])));
//			}
//        }

    }
    
    public void markDensity(int x, int y, int x2, int y2) {
    	g2d.drawRect(x, y, x2, y2);
    }


}
