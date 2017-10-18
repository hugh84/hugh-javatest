package com.hugh.javatest;

public class NumSeg {

	private static long nBegin=2210006;
	private static long nEnd=2213944;
	
	private static int getLevel(long num){
		int i=0;
		while(num% Math.pow(10, i)==0){
			i++;
		}
		return --i;
	}
	
	public static void main(String[] args) {
		int nCnt=0,nLevel=0;
		boolean upLevel=true;
		long mLevel=1;
		for(long num=nBegin;num<nEnd;){
			if(upLevel)
				System.out.println((++nCnt)+"----"+num/(long) Math.pow(10, getLevel(num)));
			else
				System.out.println((++nCnt)+"----"+num/(long) Math.pow(10, nLevel));
			if(upLevel)	nLevel=getLevel(num);
			mLevel = (long) Math.pow(10, nLevel);
			num += mLevel;
			if (num + mLevel > nEnd) {
				upLevel = false;
				nLevel--;
			}
		}
		System.out.println((++nCnt)+"----"+nEnd);
	}
}
