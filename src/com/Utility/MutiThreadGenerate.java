package com.Utility;

import com.Bean.Group;

public class MutiThreadGenerate implements Runnable{


	@Override
	public void run() {
		// TODO Auto-generated method stub
		p.generateCombination();
//		System.out.println(System.currentTimeMillis() - MidService.time1);
	}
	
	Group p;
	public MutiThreadGenerate(Group p) {
		this.p = p;
	}

}
