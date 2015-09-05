package com.sinllychen.algorithm.closet;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import javax.swing.JScrollPane;

/**
 * 界面及事件
 * @author sinllychen
 *
 */
public class DrawPanelFrame extends JFrame{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton undo;
	   private JButton clear;
	   private JPanel component;
	   private JPanel dataPanel;
	   private JPanel topPanel;
	   private JLabel statuslabel;
       private JButton drawPntBtn;//画点
       private JButton randomPntBtn;//随机生成点
       private JTextArea pointNum;//输入的点的个数
       private JLabel pointNumlbl;
       private JButton cptMinDistBtn;//计算最短距离
       private JButton cptGeneralBtn;//计算最近点对普通算法
       private JLabel minlbl;//最近点距离标签
       private JTextArea minValuelbl;//最近点距离
       private JLabel exeTime;//算法执行时间
       private JTextArea exeTimeLabel;//算法执行时间值
       private JLabel pointAlbl;
       private JTextArea pointAtxt;
       private JLabel pointBlbl;
       private JTextArea pointBtxt;
	   private DrawPanel shape;
	   private MPoint pntA=new MPoint();
	   private MPoint pntB=new MPoint();//保存最近点对
       boolean ifDraw=false;
	   boolean mousedraw=false;
	   private JScrollPane scrollPane;
	   private static int pointSize=7;
	   public DrawPanelFrame()
	   {
		   undo=new JButton("撤销");
		   clear=new JButton("清除屏幕");
		   drawPntBtn=new JButton("画点");
		   randomPntBtn=new JButton("随机生成点");
		   pointNum=new JTextArea();
		   pointNum.setText("1000000");
		   pointNum.setColumns(7);
		   pointNumlbl=new JLabel("随机产生点个数:");
		   cptMinDistBtn=new JButton("最近点对分治法(nlgn)");
		   cptGeneralBtn=new JButton("最近点对普通算法(n2)");
		   component=new JPanel(new FlowLayout(FlowLayout.CENTER));
		   dataPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		   topPanel =new JPanel(new GridLayout(2,1));
		   statuslabel=new JLabel();
		   minlbl=new JLabel("最近点距为:");
		   minValuelbl=new JTextArea();
		   minValuelbl.setColumns(10);
		   minValuelbl.setEditable(false);
		   pointAlbl=new JLabel("PointA:");
		   pointAtxt=new JTextArea();
		   pointAtxt.setColumns(20);
		   pointAtxt.setEditable(false);
		   pointBlbl=new JLabel("PointB:");
		   pointBtxt=new JTextArea();
		   pointBtxt.setColumns(20);
		   pointBtxt.setEditable(false);
		   exeTime=new JLabel("算法执行时间:");
		   exeTimeLabel=new JTextArea();
		   exeTimeLabel.setColumns(6);
		   exeTimeLabel.setEditable(false);
		   shape=new DrawPanel(statuslabel);
		   shape.setPreferredSize(new Dimension(10000,10000));
		   scrollPane=new JScrollPane(shape);
		//   scrollPane.setBounds(10, 10, 175, 70);
		   component.add(undo);
		   component.add(clear);
		   component.add(drawPntBtn);
		   component.add(randomPntBtn);
		   component.add(pointNumlbl);
		   component.add(pointNum);
		   component.add(cptMinDistBtn);
		   component.add(cptGeneralBtn);
		   dataPanel.add(minlbl);
		   dataPanel.add(minValuelbl);
		   dataPanel.add(pointAlbl);
		   dataPanel.add(pointAtxt);
		   dataPanel.add(pointBlbl);
		   dataPanel.add(pointBtxt);
		   dataPanel.add(exeTime);
		   dataPanel.add(exeTimeLabel);
		   topPanel.add(component);
		   topPanel.add(dataPanel);
		   add(topPanel,BorderLayout.NORTH);
		   add(scrollPane,BorderLayout.CENTER);
		   add(statuslabel,BorderLayout.SOUTH);
		   undo.addActionListener(
				   new ActionListener()
				   {
					  
					  public void actionPerformed(ActionEvent e) {
						shape.undoDrawShape();
						
					}
				   }
				   );
		   clear.addActionListener(
				   new ActionListener()
				   {
					   public void actionPerformed(ActionEvent e)
					   {
						   shape.clearDrawing();
						   minValuelbl.setText("");
						   exeTimeLabel.setText("");
						   pointAtxt.setText("");
						   pointBtxt.setText("");
					   }
				   }
				   );
		   drawPntBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ifDraw=true;
				shape.clearDrawing();
				shape.setDrawStatus(ifDraw);
				shape.setIfcmtClosest(false);
			}
			   
		   });
		   randomPntBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ifDraw=false;
				shape.setDrawStatus(ifDraw);
				shape.clearDrawing();
				Random random=new Random();
				Stack<MyShape> myshape=new Stack<MyShape>();
				ArrayList<MPoint> myPoints=new ArrayList<MPoint>();
				int n=Integer.valueOf(pointNum.getText().toString());
				long startTime=System.currentTimeMillis(); 
				for(int i=0;i<n;i++)
				{
					MPoint point=new MPoint();
					point.setX(random.nextDouble()*10000);
					point.setY(random.nextDouble()*10000);
					point.setRadius(pointSize);
					myshape.push(point);
					myPoints.add(point);
				}
				long endTime=System.currentTimeMillis(); 
				System.out.println("随机生成点时间:"+(endTime-startTime)+"ms");
				shape.setShapes(myshape);
				shape.setPoints(myPoints);
				shape.setIfcmtClosest(false);
				shape.repaint();
			}
		   });
		   cptMinDistBtn.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					ifDraw=false;
					shape.setDrawStatus(ifDraw);
					shape.setIfcmtClosest(true);
					long startTime=System.nanoTime(); 
					CommonMethod.quickSortX(DrawPanel.getPoints(), 0, DrawPanel.getPoints().size()-1);
/*					CommonMethod.mergeSortX(DrawPanel.getPoints(), 0, DrawPanel.getPoints().size()-1);*/
					double d=CommonMethod.closest(DrawPanel.getPoints(), 0, DrawPanel.getPoints().size()-1, pntA, pntB);
					long endTime=System.nanoTime();
					System.out.println("分治法算法运行时间"+(endTime-startTime)/1000000+"ms");
					DecimalFormat df=new DecimalFormat(".####");
					if(String.valueOf((double)(endTime-startTime)/1000000).substring(0,1).equals("0"))
					    exeTimeLabel.setText("0"+df.format((double)(endTime-startTime)/1000000)+"ms");
					else
						exeTimeLabel.setText(df.format((double)(endTime-startTime)/1000000)+"ms");
					if(String.valueOf(d).substring(0,1).equals("0"))
					    minValuelbl.setText("0"+String.valueOf(df.format(d)));
					else 
						minValuelbl.setText(String.valueOf(df.format(d)));
					pointAtxt.setText("("+df.format(pntA.getX())+","+df.format(pntA.getY())+")");
					pointBtxt.setText("("+df.format(pntB.getX())+","+df.format(pntB.getY())+")");
					shape.setPntA(pntA);
					System.out.println(String.valueOf(d)+"      "+"最近点对为:"+"A="+"("+pntA.getX()+","+pntA.getY()+")"+","+"B="+"("+pntB.getX()+","+pntB.getY()+")");
					shape.setPntB(pntB);
					shape.setDrawColor(Color.RED);
					shape.repaint();
				}
				   
			   });
		   cptGeneralBtn.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					ifDraw=false;
					shape.setDrawStatus(ifDraw);
					shape.setIfcmtClosest(true);
					long startTime=System.nanoTime(); 
					double d=CommonMethod.generalClosest(DrawPanel.getPoints(), 0, DrawPanel.getPoints().size()-1, pntA, pntB);
					long endTime=System.nanoTime();
					System.out.println("普通算法运行时间"+(endTime-startTime)/1000000+"ms");
					DecimalFormat df=new DecimalFormat(".####");
					if(String.valueOf((double)(endTime-startTime)/1000000).substring(0,1).equals("0"))
					    exeTimeLabel.setText("0"+df.format((double)(endTime-startTime)/1000000)+"ms");
					else
						exeTimeLabel.setText(df.format((double)(endTime-startTime)/1000000)+"ms");
					if(String.valueOf(d).substring(0,1).equals("0"))
					    minValuelbl.setText("0"+String.valueOf(df.format(d)));
					else 
						minValuelbl.setText(String.valueOf(df.format(d)));
					pointAtxt.setText("("+df.format(pntA.getX())+","+df.format(pntA.getY())+")");
					pointBtxt.setText("("+df.format(pntB.getX())+","+df.format(pntB.getY())+")");
					shape.setPntA(pntA);
					System.out.println(String.valueOf(d)+"      "+"最近点对为:"+"A="+"("+pntA.getX()+","+pntA.getY()+")"+","+"B="+"("+pntB.getX()+","+pntB.getY()+")");
					shape.setPntB(pntB);
					shape.setDrawColor(Color.GREEN);
					shape.repaint();
				}
				   
			   });
		   
	   }
	   
	/**
	 * @return the drawPntBtn
	 */
	public JButton getDrawPntBtn() {
		return drawPntBtn;
	}
	/**
	 * @param drawPntBtn the drawPntBtn to set
	 */
	public void setDrawPntBtn(JButton drawPntBtn) {
		this.drawPntBtn = drawPntBtn;
	}
	/**
	 * @return the randomPntBtn
	 */
	public JButton getRandomPntBtn() {
		return randomPntBtn;
	}
	/**
	 * @param randomPntBtn the randomPntBtn to set
	 */
	public void setRandomPntBtn(JButton randomPntBtn) {
		this.randomPntBtn = randomPntBtn;
	}
	/**
	 * @return the cptMinDistBtn
	 */
	public JButton getCptMinDistBtn() {
		return cptMinDistBtn;
	}
	/**
	 * @param cptMinDistBtn the cptMinDistBtn to set
	 */
	public void setCptMinDistBtn(JButton cptMinDistBtn) {
		this.cptMinDistBtn = cptMinDistBtn;
	}
	/**
	 * @return the minlbl
	 */
	public JLabel getMinlbl() {
		return minlbl;
	}
	/**
	 * @param minlbl the minlbl to set
	 */
	public void setMinlbl(JLabel minlbl) {
		this.minlbl = minlbl;
	}
	/**
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	/**
	 * @param scrollPane the scrollPane to set
	 */
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	/**
	 * @param pntA the pntA to set
	 */
	public void setPntA(MPoint pntA) {
		this.pntA = pntA;
	}
	/**
	 * @return the pntB
	 */
	public MPoint getPntB() {
		return pntB;
	}
	/**
	 * @param pntB the pntB to set
	 */
	public void setPntB(MPoint pntB) {
		this.pntB = pntB;
	}
	  /**
		 * @return the pntA
		 */
		public MPoint getPntA() {
			return pntA;
		}
}
