package com.Utility;

import com.Bean.Group;

public class SingleThreadGenerate implements Runnable{

	boolean isExit = false;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int counter = 0;
		while(isExit == false) {
			System.out.print("");
		    while(MidService.generateGroup.size() != 0){
		    	MidService.generateGroup.poll().generateCombination();
		    	counter++;
		    }
		    if(counter!= 0 && counter == MidService.orderedGroup.size()) {
		    	break;
		    }
		}
		System.out.println(System.currentTimeMillis() - MidService.time1);
	}
	
	public void exit() {
		isExit = true;
	}

}