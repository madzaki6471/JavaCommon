﻿package com.sinllychen.algorithm.closet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.*;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 画图事件
 * @author sinllychen
 *
 */
public class DrawPanel extends JPanel  {
   /**
	 * 
	 */
	private static final long serialVersionUID = -1247937026439191218L;
   private static Stack<MyShape>  shapes =new Stack<MyShape>();//保存所有点的图形
   private static ArrayList<MPoint> points=new ArrayList<MPoint>();//保存所有点
   private static MPoint pntA=new MPoint();
   private static MPoint pntB=new MPoint();//保存最近点对
   private JLabel statusLabel;//状态标签
   private MPoint myPoint=new MPoint();//画的点
   private boolean mouseClick=false;//是否点击鼠标
   private boolean ifDraw=false;//是否画点
   private boolean ifcmtClosest=false;//是否显示最小距离
   public static int pointSize=7;
   private static Color drawColor=Color.RED;
   public DrawPanel(JLabel label)
   {
	   setStatusLabel(label);
	   this.setBackground(Color.WHITE);
	   this.addMouseListener(new MouseHandler()); 
   }
   public void setDrawStatus(boolean ifDraw)
   {
	   this.ifDraw=ifDraw;
   }
   public void undoDrawShape()
   {
	   shapes.pop();
	   points.remove(shapes.size()-1);
	   this.ifDraw=false;
	   this.mouseClick=false;
	   setIfcmtClosest(false);
	   repaint();
   }
   public void clearDrawing()
   {
	   shapes=new Stack<MyShape>();
	   points=new ArrayList<MPoint>();
	   pntA=new MPoint();
	   pntB=new MPoint();
	   this.ifDraw=false;
	   this.mouseClick=false;
	   setIfcmtClosest(false);
	   repaint();
   }
   public void  paintComponent(Graphics g)
   {
	   
	   super.paintComponent(g);
	   Graphics2D g2d=(Graphics2D)g;
	   for(int i=0;i<shapes.size();i++)
	   {
/*		   System.out.println(shapes.size());*/
		   shapes.get(i).Draw(g2d);
	   }
      if(!(pntA.getX()==0&&pntA.getY()==0&&pntB.getX()==0&&pntB.getY()==0))
      {
	    g2d.setColor(drawColor);
		Ellipse2D circle=new Ellipse2D.Double(pntA.getX(),pntA.getY(),pointSize,pointSize);
		g2d.fill(circle);
		Ellipse2D circle2=new Ellipse2D.Double(pntB.getX(),pntB.getY(),pointSize,pointSize);
		g2d.fill(circle2); 
		Line2D  line=new Line2D.Double(pntA.getX()+3, pntA.getY()+3, pntB.getX()+3, pntB.getY()+3);
		g2d.draw(line);
		g2d.drawString("("+pntA.getX()+","+pntA.getY()+")",(int)pntA.getX()-12, (int)(pntA.getY()-12));
		g2d.drawString("("+pntB.getX()+","+pntB.getY()+")",(int)pntB.getX()+12, (int)(pntB.getY()+12));
      }
   }

/**
 * @return the mouseClick
 */
public boolean isMouseClick() {
	return mouseClick;
}
/**
 * @param mouseClick the mouseClick to set
 */
public void setMouseClick(boolean mouseClick) {
	this.mouseClick = mouseClick;
}

/**
 * @return the ifDraw
 */
public boolean isIfDraw() {
	return ifDraw;
}
/**
 * @param ifDraw the ifDraw to set
 */
public void setIfDraw(boolean ifDraw) {
	this.ifDraw = ifDraw;
}

/**
 * @return the statusLabel
 */
public JLabel getStatusLabel() {
	return statusLabel;
}
/**
 * @param statusLabel the statusLabel to set
 */
public void setStatusLabel(JLabel statusLabel) {
	this.statusLabel = statusLabel;
}

/**
 * @return the shapes
 */
public static Stack<MyShape> getShapes() {
	return shapes;
}
/**
 * @param shapes the shapes to set
 */
public void setShapes(Stack<MyShape> shapes) {
	this.shapes = shapes;
}

/**
 * @return the points
 */
public  static ArrayList<MPoint> getPoints() {
	return points;
}
/**
 * @param points the points to set
 */
public  void setPoints(ArrayList<MPoint> points) {
	DrawPanel.points = points;
}

/**
 * @return the pntA
 */
public static MPoint getPntA() {
	return pntA;
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
public static MPoint getPntB() {
	return pntB;
}
/**
 * @param pntB the pntB to set
 */
public void setPntB(MPoint pntB) {
	this.pntB = pntB;
}

/**
 * @return the ifcmtClosest
 */
public  boolean isIfcmtClosest() {
	return ifcmtClosest;
}
/**
 * @param ifcmtClosest the ifcmtClosest to set
 */
public  void setIfcmtClosest(boolean ifcmtClosest) {
	ifcmtClosest = ifcmtClosest;
}

/**
 * @return the drawColor
 */
public static Color getDrawColor() {
	return drawColor;
}
/**
 * @param drawColor the drawColor to set
 */
public static void setDrawColor(Color drawColor) {
	DrawPanel.drawColor = drawColor;
}

private class MouseHandler   implements  MouseListener
   {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		statusLabel.setText(String.format("(%d,%d)",e.getX(),e.getY()));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		myPoint=new MPoint(e.getX(),e.getY(),pointSize);
		statusLabel.setText(String.format("(%d,%d)",e.getX(),e.getY()));
	    mouseClick=true;
	    if(mouseClick&&ifDraw&&CommonMethod.searchIfNotSame(points, myPoint))
	    { shapes.push(myPoint);
	      points.add(myPoint);
	       repaint();}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		statusLabel.setText(String.format("(%d,%d)",e.getX(),e.getY()));	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		statusLabel.setText(String.format("(%d,%d)",e.getX(),e.getY()));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		statusLabel.setText(String.format("(%d,%d)",e.getX(),e.getY()));
	}

}
}

