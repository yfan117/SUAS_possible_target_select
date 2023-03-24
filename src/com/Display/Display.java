package com.Display;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.Controller.KeyHandler;
import com.Utility.MidService;

public class Display {
	
	JFrame frame;
	public Render render;
	
	public Display() throws IOException {
		frame = new JFrame();
		frame.setSize(MidService.windowSize[0], MidService.windowSize[1]);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		render = new Render();
		frame.addKeyListener(new KeyHandler());
		frame.add(render);
		
		
		frame.setVisible(true);
		frame.validate();
	}

}
