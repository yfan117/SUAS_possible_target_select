package com.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.Utility.MidService;

public class KeyHandler implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar() == 'a') {
			if(MidService.groupIndex > 0) {
				MidService.groupIndex--;
				MidService.combinationIndex = 0;
			}
		}
		else if(e.getKeyChar() == 'd') {
			if(MidService.groupIndex < MidService.orderedGroup.size()-1) {
				MidService.groupIndex++;
				MidService.combinationIndex = 0;
			}
		}
		if(e.getKeyChar() == 'z') {
			MidService.combinationIndex--;
		}
		else if(e.getKeyChar() == 'c') {
			MidService.combinationIndex++;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
