package com.sinllychen.algorithm.closet;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
/**
 * 画点
 * @author sinllychen
 *
 */
public class MPoint extends MyShape{
	private double x;
	private double y;
	private double radius;
	Color mycolor=Color.BLACK;
	public MPoint(){};
	public MPoint(MPoint point)
	{
		this.x=point.getX();
		this.y=point.getY();
		this.radius=point.getRadius();
	}
	public MPoint(double x,double y,double radius)
	{
		this.x=x;
		this.y=y;
		this.radius=radius;
	}
	public void Draw(Graphics2D g)
	{
		g.setColor(mycolor);
		Ellipse2D circle=new Ellipse2D.Double(x,y,radius,radius);
		g.fill(circle);
	}
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}
	/**
	 * @param radius the radius to set
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}
}
