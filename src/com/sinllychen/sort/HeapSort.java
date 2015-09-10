package com.sinllychen.sort;

public class HeapSort {
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
    		  keepMaxProperty(i);
    	  }
      }
      public void heapSort()
      {
    	for(int i=0;i<data.length;i++)
    	{
    		swap(0,heapSize-1);
    		heapSize = heapSize - 1;
    		keepMaxProperty(0);
    	}
      }
      public void swap(int i,int j)
      {
    	  int tmp = data[i];
    	  data[i] = data[j];
    	  data[j] = tmp;
      }
      public void keepMaxProperty(int index)
      {
    	  int left = 2*(index+1)-1;
    	  int right = 2*(index+1);
    	  int largest=index;
    	  if(left<heapSize && data[left]>data[largest])
    		  largest = left;
    	  if(right<heapSize && data[right] > data[largest])
    		  largest = right;
    	  if(largest == index || largest>=heapSize)
    		  return;
    	  else
    		  swap(largest,index);
    	  keepMaxProperty(largest);
      }
      public static void main(String[] args)
      {
    	  HeapSort heapSort = new HeapSort();
    	  heapSort.buildHeap();
    	  heapSort.heapSort();
    	  heapSort.printArray();
      }
}
