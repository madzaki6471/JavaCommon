package com.sinllychen.algorithm.closet;
import java.util.ArrayList;
import java.util.List;

/**
 * 一些公共算法
 * @author sinllychen
 *
 */
public class CommonMethod {
	/**
	 * 查找是否重复点
	 * @param points
	 * @param pnt
	 * @return
	 */
	public static boolean searchIfNotSame(List<MPoint> points,MPoint pnt)
	{
           for(int i=0;i<points.size();i++)
           {	if(points.get(i).getX()==pnt.getX()&&points.get(i).getY()==pnt.getY())
                 {     	   
        	        return false;       
                 }
           }
           return true;
	}
	/**
	 * 数组合并，按照x坐标合并
	 * @param points
	 * @param i
	 * @param q
	 * @param j
	 */
	public static void mergeX(ArrayList<MPoint> points,int i,int q,int j)
	{
		int n1=q-i+1;
		int n2=j-q;
		MPoint[] l=new MPoint[n1+1];
		MPoint[] r=new MPoint[n2+1];
		int m,n;
		for(m=0;m<n1;m++)
		{
			l[m]=points.get(i+m);
		}
		for(n=0;n<n2;n++)
		{
			r[n]= points.get(q+n+1);
		}
		l[n1]= new MPoint(100000,1000000,10);
		r[n2]= new MPoint(100000,1000000,10);
		m=0;n=0;
		for(int p=i;p<=j;p++)
		{
			if(l[m].getX()<=r[n].getX())
			{
				points.set(p, l[m]);
				m++;
			}
			else 
				{
				points.set(p,r[n]);
				n++;
				}
		}
		
	}
	/**
	 * 归并排序，按照点的X坐标进行排序
	 * @param points
	 */
	public static void mergeSortX(ArrayList<MPoint> points ,int i,int j)
	{
		int q;
		if(i<j)
		{
			q=(int) Math.floor((i+j)/2);
			mergeSortX(points,i,q);
			mergeSortX(points,q+1,j);
			mergeX(points,i,q,j);
		}
	}
	/**
	 * 数组合并，按照Y坐标合并
	 * @param points
	 * @param i
	 * @param q
	 * @param j
	 */
	public static void mergeY(ArrayList<MPoint> points,int i,int q,int j)
	{
		int n1=q-i+1;
		int n2=j-q;
		MPoint[] l=new MPoint[n1+1];
		MPoint[] r=new MPoint[n2+1];
		int m,n;
		for(m=0;m<n1;m++)
		{
			l[m]=points.get(i+m);
		}
		for(n=0;n<n2;n++)
		{
			r[n]= points.get(q+n+1);
		}
		l[n1]= new MPoint(100000,1000000,10);
		r[n2]= new MPoint(100000,1000000,10);
		m=0;n=0;
		for(int p=i;p<=j;p++)
		{
			if(l[m].getY()<=r[n].getY())
			{
				points.set(p, l[m]);
				m++;
			}
			else 
				{
				points.set(p,r[n]);
				n++;
				}
		}
		
	}
	/**
	 * 归并排序，按照点的Y坐标进行排序
	 * @param points
	 */
	public static void mergeSortY(ArrayList<MPoint> points ,int i,int j)
	{
		int q;
		if(i<j)
		{
			q=(int) Math.floor((i+j)/2);
			mergeSortY(points,i,q);
			mergeSortY(points,q+1,j);
			mergeX(points,i,q,j);
		}
	}
	/**
	 * 交换两个点坐标
	 * @param p
	 * @param q
	 */
	public static void swap(MPoint p,MPoint q)
	{
		MPoint temp=new MPoint();
		copyPoint(temp,p);
		copyPoint(p,q);
		copyPoint(q,temp);
	}

	/**
	 * 获得分割点,通过x坐标的大小
	 * @param points
	 * @param p
	 * @param r
	 * @return
	 */
	public static int partitionX(ArrayList<MPoint> points,int low,int high)
	{
		int i=low+1,j=high;
		MPoint pt=points.get(low);
		while(i<=j)
		{
			while(i<=j&&points.get(i).getX()<=pt.getX()) i++;
			while(i<=j&&points.get(j).getX()>=pt.getX()) j--;
			if(i<j)
			{
			  swap(points.get(i),points.get(j));
			}
		}
		points.set(low, points.get(j));
		points.set(j, pt);
		return  j;
	
	}
	/**
	 * 对坐标点通过x坐标大小进行快速排序
	 * @param points
	 * @param low
	 * @param high
	 */
	public  static void quickSortX(ArrayList<MPoint> points,int low,int high)
	{
		if(low<high)
		{
			int q=partitionX(points,low,high);
			quickSortX(points,low,q-1);
			quickSortX(points,q+1,high);
		}
	}
	
	/**
	 * 获得分割点，通过y坐标的大小
	 * @param points
	 * @param p
	 * @param r
	 * @return
	 */
	public static int partitionY(ArrayList<MPoint> points,int low,int high)
	{
		int i=low+1,j=high;
		MPoint pt=points.get(low);
		while(i<=j)
		{
			while(i<=j&&points.get(i).getY()<=pt.getY()) i++;
			while(i<=j&&points.get(j).getY()>=pt.getY()) j--;
			if(i<j)
			{
			  swap(points.get(i),points.get(j));
			}
		}
		points.set(low, points.get(j));
		points.set(j, pt);
		return  j;
	
	}
	/**
	 * 对点的y坐标进行快速排序
	 * @param points
	 * @param low
	 * @param high
	 */
	public  static void quickSortY(ArrayList<MPoint> points,int low,int high)
	{
		if(low<high)
		{
			int q=partitionY(points,low,high);
			quickSortY(points,low,q-1);
			quickSortY(points,q+1,high);
		}
	}

	/**
	 * 计算两点之间的距离
	 * @param a
	 * @param b
	 * @return
	 */
	public static double distance(MPoint a,MPoint b)
	{
		return Math.sqrt((a.getX()-b.getX())*(a.getX()-b.getX())+(a.getY()-b.getY())*(a.getY()-b.getY()));
	}
	/**
	 * 计算最小距离
	 * @param a
	 * @param b
	 * @return
	 */
	public static double minDistance(double a,double b)
	{
		if(a<b)
			return a;
		else 
			return b;
	}
	/**
	 * 点复制
	 * @param a
	 * @param b
	 */
	public static  void copyPoint(MPoint a,MPoint b)
	{
		a.setX(b.getX());
		a.setY(b.getY());
		a.setRadius(b.getRadius());
	}
	
	/**
	 * 求最近点对
	 * @param points  输入的数组
	 * @param i    需要求的最近点对的初始index
	 * @param j   需要求的最近点对的终点index
	 * @param a   保存最近点对中的一个点
	 * @param b   保存最近点对中的另一个点
	 * @return  返回最短距离
	 */
	public static double closest(ArrayList<MPoint> points,int i,int j,MPoint a,MPoint b)
	{
		   if((j-i)==1)//两点的情况
		   {
			   copyPoint(a,points.get(i));
	           copyPoint(b,points.get(j));
			   return distance(points.get(i),points.get(j));
		   }
		   if((j-i)==2)//三点的情况
		   {
			   double da=distance(points.get(i),points.get(i+1));
			   double db=distance(points.get(i+1),points.get(j));
			   double dc=distance(points.get(i),points.get(j));
			   if(da<=db&&da<=dc)
			   {
				   copyPoint(a,points.get(i));
				   copyPoint(b,points.get(i+1));
				   return da;
			   }
			   else  if(db<=dc&&db<=da)
			   {
				   copyPoint(a,points.get(i+1));
				   copyPoint(b,points.get(j));
				   return db;
			   }
			   else if(dc<=da&&dc<=db)
			   {
				   copyPoint(a,points.get(i));
				   copyPoint(b,points.get(j));
				   return dc;
			   }
		   }
	        int m=(int) Math.floor((i+j)/2);
	        double d1=closest(points,i,m,a,b);
	        MPoint a1=new MPoint();
	        MPoint b1=new MPoint();
	        double d2=closest(points,m+1,j,a1,b1);
	        double d=minDistance(d1,d2);
	        if(d1>d2)
	        {
	        	copyPoint(a,a1);
	        	copyPoint(b,b1);
	        }
	        ArrayList<MPoint> s1=new ArrayList<MPoint>();
	        for(int k=i;k<=j;k++)
	        {
	            if(Math.abs(points.get(k).getX()-points.get(m).getX())<=d)
	            {
	            	s1.add(points.get(k));
	            }
	        }
	        int cnt=s1.size()-1;
	        quickSortY(s1,0,cnt);
	        for(int k=0;k<=cnt;k++)
	        {
	        	int n=(k+7)>cnt?cnt:(k+7);
	        	for(int z=k+1;z<=n;z++)
	        	{
	        	    if(s1.get(z).getY()-s1.get(k).getY()>d) break;
	        		double tem=distance(s1.get(k), s1.get(z));
	        		if(tem<d)
	        		{
	        			d=tem;
                     copyPoint(a,s1.get(k));
                     copyPoint(b,s1.get(z));
	        		}
	        	}
	        }        
	        return d;        
	}   
	/**
	 * 普通方法求解最近点对
	 * @param points  输入的数组
	 * @param i    需要求的最近点对的初始index
	 * @param j   需要求的最近点对的终点index
	 * @param a   保存最近点对中的一个点
	 * @param b   保存最近点对中的另一个点
	 * @return  返回最短距离
	 */
	public static double generalClosest(ArrayList<MPoint> points,int i,int j,MPoint a,MPoint b)
	{
		double d=1000000;
		for(int m=i;m<=j;m++)
		{
			for(int n=m+1;n<=j;n++)
			{
				if(distance(points.get(m),points.get(n))<d)
				{
					d=distance(points.get(m),points.get(n));
					copyPoint(a,points.get(m));
					copyPoint(b,points.get(n));
				}
			}
		}
		return d;
	}
	public static void main(String[] args)
	{
		MPoint p=new MPoint(2,1,10);
		MPoint p1=new MPoint(4,2,10);
		MPoint p3=new MPoint(3,2,10);
		MPoint p4=new MPoint(5,3,10);
		ArrayList<MPoint> points=new ArrayList<MPoint>();
		points.add(p);points.add(p1);points.add(p3);points.add(p4);
		quickSortY(points,0,points.size()-1);
		for(int i=0;i<points.size();i++)
		{
			System.out.println(points.get(i).getX()+","+points.get(i).getY());
		}
		System.out.println(searchIfNotSame(points,new MPoint(2,1,10)));
		System.out.println(searchIfNotSame(points,new MPoint(2,2,10)));
	}
	
}
