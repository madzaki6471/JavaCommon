package com.sinllychen.sort;

public class BubbleSort {
	private static void BubbleSort(long[] A) 
    {
	  int j, k;
	  int flag;
	  flag = A.length-1;
	  while (flag >0)
	  {
		k = flag;
		flag = 0;
		for (j = 0; j <= k-1; j++)
			if(j==k-1)
				System.out.println();
			if (A[j] > A[j+1])
			{
				Common.swap(A, j+1, j);
				flag = j;
			}
	   }
   }
	
	private static void commonBubbleSort(long[] A)
	{
		int length = A.length;
		for(int i=0;i<length;i++)
		{
			for(int j = 0 ; j<length-i-1;j++)
			{
				if(A[j] > A[j+1] )
				{
					long tmp = A[j];
					A[j] = A[j+1];
					A[j+1] = tmp;
				}
			}
		}
	}
	public static void main(String[] args)
	{
		long[] a={1,5,7,9,3,2,5,0,12,6,4,3};
		BubbleSort(a);
		for(int i=0;i<a.length;i++)
			 System.out.print(a[i]+"");
	}

}
