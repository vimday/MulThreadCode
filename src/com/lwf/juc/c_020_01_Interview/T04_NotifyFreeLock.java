/**
 * ?????????????????????
 * ????????????????????????add??size
 * д???????????1???10???????????У????2??????????????????????5????????2?????????????
 * 
 * ??lists???volatile???t2???????????????t2??????????????cpu??????????????????????????
 * 
 * ???????wait??notify??????wait???????????notify?????????
 * ???????????????????????????????t2????У????????????t2?????????
 * 
 * ??????????????????????
 * ???????????????????size=5?t2?????????t1?????t2?????????????
 * ?????????????
 * 
 * notify???t1???????????t2??????????notify????t1???????
 * ???????????????
 * @author mashibing
 */
package com.lwf.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class T04_NotifyFreeLock {

	//???volatile???t2????????
	volatile List lists = new ArrayList();

	public void add(Object o) {
		lists.add(o);
	}

	public int size() {
		return lists.size();
	}
	
	public static void main(String[] args) {
		T04_NotifyFreeLock c = new T04_NotifyFreeLock();
		
		final Object lock = new Object();
		
		new Thread(() -> {
			synchronized(lock) {
				System.out.println("t2????");
				if(c.size() != 5) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("t2 ????");
				//??t1???????
				lock.notify();
			}
			
		}, "t2").start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		new Thread(() -> {
			System.out.println("t1????");
			synchronized(lock) {
				for(int i=0; i<10; i++) {
					c.add(new Object());
					System.out.println("add " + i);
					
					if(c.size() == 5) {
						lock.notify();
						//?????????t2???????
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t1").start();
		
		
	}
}
