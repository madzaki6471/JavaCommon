﻿package com.sinllychen.string;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * 请编写一个家庭收支管理程序，要求可以增加/修改/删除/查看家庭的各项收支信息
 * 收入信息至少包括收入的人员姓名，来源（如工资等）、收入发生的日期和金额，支出信息至少应当包括支出
 * 的人员姓名、去向（如购物）、支出发生的日期和金额。而且要求能够对某一段时间的收支情况进行统计
 * @author sinllychen
 *
 */
public class Test5 {
	 static ObjectOutputStream ouput = null;
	 static ObjectInputStream input = null;
	 static long index=0;
	public static void openWriteFile()
	{
		 try {
			ouput=new ObjectOutputStream(new ObjectOutputStream(new FileOutputStream("charge.txt",false)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void openReadFile()
	{
		 try {
			input=new ObjectInputStream(new ObjectInputStream(new FileInputStream("charge.txt")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void initialize()
	{
		 File file=new File("charge.txt");
		 try {
			 if(!file.exists())
				 file.createNewFile();
			 if(file.length()>0)
			 { openReadFile();
			   List<Charge> chargeT=(List<Charge>) input.readObject();
			   index=chargeT.size();
				input.close();
			 }
			 else
				 index=0;	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
    public static void main(String[] args) throws IOException
    {
    	 BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
    	 initialize();
    	 List<Charge> charges=new ArrayList<Charge>();
    	 while(true)
    	 {
    		 try {
    			System.out.println("请输入add,delete,view,modify,sum对收支记录进行操作!");
				String flag=reader.readLine();
				char a=flag.charAt(0);
				switch(a)
				{
				case 'a': 
					System.out.println("请输入收入的人员姓名，来源，发生的日期和金额,及收支类型(1或者-1)表示(通过逗号隔开):");
					String[] inputs=reader.readLine().split(",");
					if(index!=0)
					{
						openReadFile();
						charges=(List<Charge>) input.readObject();
						input.close();
					}
					Charge charge=new Charge(++index,inputs[0],inputs[1],inputs[2],Double.valueOf(inputs[3]),Integer.valueOf(inputs[4]));
					charges.add(charge);
					openWriteFile();
					ouput.writeObject(charges);
					ouput.close();
					break;
				case 'v':
                    System.out.println("收支情况列表如下所示:");
					openReadFile();
					if(index<=0)
					{
						System.out.println("暂时没有记录!");
					}
					else	if(index>0)
					{   
				    charges=(List<Charge>) input.readObject();
				    input.close();
				    System.out.print("序号"+"\t");
				    System.out.print("人员姓名"+"\t");
				    System.out.print("来源"+"\t");
				    System.out.print("时间"+"\t");
				    System.out.print("金额"+"\t");
				    System.out.println("收支类型"+"\t");
					for(Charge g:charges)
					{
						System.out.print(g.getNum()+"\t");
						System.out.print(g.getName()+"\t");
						System.out.print(g.getFrom()+"\t");
						System.out.print(g.getTime()+"\t");
						System.out.print(g.getMoney()+"\t");
						if(g.getType()==1)
							System.out.println("收入"+"\t");
						else 
							System.out.println("支出"+"\t");
					}
					}
					break;
				case 'm':
					System.out.println("请选择需要修改的序号:");
					openReadFile();
				    charges=(List<Charge>) input.readObject();
				    int num=Integer.valueOf(reader.readLine());
				    while(num>charges.size())
				    {
				    	System.out.println("序号错误，请选择需要修改的序号:");
				    	num=Integer.valueOf(reader.readLine());
				    }
				    input.close();
				    System.out.print(charges.get(num-1).getNum()+"\t");
					System.out.print(charges.get(num-1).getName()+"\t");
					System.out.print(charges.get(num-1).getFrom()+"\t");
					System.out.print(charges.get(num-1).getTime()+"\t");
					System.out.println(charges.get(num-1).getMoney()+"\t");
					System.out.println("输入新的人员姓名，来源，发生的日期和金额(通过逗号隔开)");
					String[] modify=reader.readLine().split(",");
					charges.get(num-1).setName(modify[0]);
					charges.get(num-1).setFrom(modify[1]);
					charges.get(num-1).setTime(modify[2]);
					charges.get(num-1).setMoney(Double.valueOf(modify[3]));
					openWriteFile();
					ouput.writeObject(charges);
					ouput.close();
					break;
				case 'd':
					System.out.println("请选择需要删除的记录序号:");
					openReadFile();
				    List<Charge> chargeTemp=(List<Charge>) input.readObject();
				    int num1=Integer.valueOf(reader.readLine());
				    while(num1>chargeTemp.size())
				    {
				    	System.out.println("序号错误，请选择需要修改的序号:");
				    	  num1=Integer.valueOf(reader.readLine());
				    }
				    input.close();
				    System.out.print(charges.get(num1-1).getNum()+"\t");
					System.out.print(charges.get(num1-1).getName()+"\t");
					System.out.print(charges.get(num1-1).getFrom()+"\t");
					System.out.print(charges.get(num1-1).getTime()+"\t");
					System.out.println(charges.get(num1-1).getMoney()+"\t");
		            charges=new ArrayList<Charge>();
					for(int i=0;i<chargeTemp.size();i++)
					{
						if(i<num1-1)
							charges.add(chargeTemp.get(i));
						if(i>num1-1)
						{
							chargeTemp.get(i).setNum(chargeTemp.get(i).getNum()-1);
							charges.add(chargeTemp.get(i));
						}
					}
					openWriteFile();
					ouput.writeObject(charges);
					System.out.println("记录已删除！");
					--index;
					ouput.close();
					break;
				case 's':
					System.out.println("请输入统计的开始时间");
					String startTime=reader.readLine();
					System.out.println("请输入统计的结束时间");
					String endTime=reader.readLine();
					System.out.println("从"+startTime+"~"+endTime+"时间段的收支情况如下:");
					double zcSum=0;
					double srSum=0;
					openReadFile();
					charges=(List<Charge>) input.readObject();
					for(int i=0;i<charges.size();i++)
					{
						if(charges.get(i).getType()==1&&Date.valueOf(charges.get(i).getTime()).after(Date.valueOf(startTime))&&
								Date.valueOf(charges.get(i).getTime()).before(Date.valueOf(endTime)))
							 srSum+=charges.get(i).getMoney();
						if(charges.get(i).getType()==-1&&Date.valueOf(charges.get(i).getTime()).after(Date.valueOf(startTime))&&
								Date.valueOf(charges.get(i).getTime()).before(Date.valueOf(endTime)))
							 zcSum+=charges.get(i).getMoney();
					}
					System.out.println("支出金额共为:"+zcSum);
					System.out.println("收入金额共为:"+srSum);
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 }
    }
}
