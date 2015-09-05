package com.sinllychen.sort;

public class Quick {
       public static void swap(int[] data,int i, int j)
       {
    	   int tmp=data[i];
    	   data[i] = data[j];
    	   data[j]=tmp;
       }
       public static int partition(int[] data,int i ,int j)
       {
    	   int key = data[i];
    	   int m = i +1;
    	   int n=j;
    	   while(m<=n)
    	   {
    		   while(m<=n && data[n] >= key) n--;
    		   while(m<=n && data[m] <=key) m++;
    		   if(m>=n)
    			   break;
    		   swap(data,m,n);
    	   }
    	   data[i]=data[n];
    	   data[n]=key;
    	   return n;
       }
       public static void quickSort(int[] data,int i,int j)
       {
    	   if(i<j)
    	   {
    		   int p=partition(data,i,j);
    		   quickSort(data,i,p-1);
    		   quickSort(data,p+1,j);
    	   }
       }
       public static void main(String[] args)
       {
    	   int[] data={7,6,4,1,9,10,34,2};
    	   quickSort(data,0,data.length-1);
    	   for(int i =0;i<data.length;i++)
    	   System.out.println(data[i]);
       }
}
