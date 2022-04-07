package tests;

import java.util.*;


public class Test {
	private static final int HOURS = 24;
	private static final int DAYS = 99999;
	private static float[][] myarr = new float[DAYS][HOURS];
	
	private static void loop(float[][] myarr) {
		for (int k = 0; k < myarr.length; k++) {
			for (int i = 0; i < myarr[k].length; i++) {
				if (myarr[k][i] != 0.0f) {
				}
			}
		}
	}
	
	private static void set_loop(float[][] myarr) {
		for (int k = 0; k < myarr.length; k++) {
			 Set<Float> mySet = new HashSet<>();
			 for (int i = 0; i < myarr[k].length; i++) {
					mySet.add(myarr[k][i]);
				}	
		   
		    if (!mySet.contains(0.0f)) {
		    }
			
		}
	}
	
	public static void main(String[] args) {
		for (int k = 0; k < myarr.length; k++) {
			for (int i = 0; i < myarr[k].length; i++) {
				if (k == DAYS - 2) {
					myarr[k][i] = 9;
				} else {
					myarr[k][i] = 0;
				}
			}			
		}
		long t0 = System.currentTimeMillis();
		set_loop(myarr);
		long t1 = System.currentTimeMillis();
		loop(myarr);
		long t2 = System.currentTimeMillis();
		System.out.println(t1-t0);
		System.out.println(t2-t1);
		
	}
}