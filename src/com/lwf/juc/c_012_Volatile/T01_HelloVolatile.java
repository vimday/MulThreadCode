/**
 * volatile ????????????????????????
 * A B????????????????java?????A????б??????copy?????????B????????????????A???δ?????
 * ???volatile?????????????????????????????????
 * 
 * ???????????У?running????????????t??????
 * ?????t1??????е??????running???????ж???t1????????????????й??????????????copy??????????ζ??
 * ???????棬????????????????running??????t1????????????????????????
 * 
 * ???volatile????????????????????????ж??running???
 * 
 * ????????????????и?????????
 * http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 * 
 * volatile???????????????????running???????????????????????????volatile???????synchronized
 * @author mashibing
 */
package com.lwf.juc.c_012_Volatile;

import java.util.concurrent.TimeUnit;

public class T01_HelloVolatile {
	/*volatile*/ boolean running = true; //??????????volatile???????????????????н????????
	void m() {
		System.out.println("m start");
		while(running) {
		}
		System.out.println("m end!");
	}
	
	public static void main(String[] args) {
		T01_HelloVolatile t = new T01_HelloVolatile();
		
		new Thread(t::m, "t1").start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t.running = false;
	}
	
}


