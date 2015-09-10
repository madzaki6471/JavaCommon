package com.sinllychen.sort;

public class HeapSortMin {
      private int[] data={3,5,1,7,8,4,9,0};
      private int heapSize = data.length;
      public void printArray()
      {
    	  for(int i=0;i<data.length;i++)
    		  System.out.print(data[i]+" ");
      }
      public void buildHeap()
      {
    	  for(int i=heapSize/2-1;i>=0;i--)
    	  {
    		  keepMinProperty(i);
    	  }
      }
      public void heapSort()
      {
    	for(int i=0;i<data.length;i++)
    	{
    		swap(0,heapSize-1);
    		heapSize = heapSize - 1;
    		keepMinProperty(0);
    	}
      }
      public void swap(int i,int j)
      {
    	  int tmp = data[i];
    	  data[i] = data[j];
    	  data[j] = tmp;
      }
      public void keepMinProperty(int index)
      {
    	  int left = 2*(index+1)-1;
    	  int right = 2*(index+1);
    	  int min=index;
    	  if(left<heapSize && data[left]<data[min])
    		  min = left;
    	  if(right<heapSize && data[right] < data[min])
    		  min = right;
    	  if(min == index || min>=heapSize)
    		  return;
    	  else
    		  swap(min,index);
    	  keepMinProperty(min);
      }
      public static void main(String[] args)
      {
    	  HeapSortMin heapSort = new HeapSortMin();
    	  heapSort.buildHeap();
    	  heapSort.heapSort();
    	  heapSort.printArray();
      }
}
