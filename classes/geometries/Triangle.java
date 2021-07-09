package geometries;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Triangle extends Geometry {
	private Point3D p1;
	private Point3D p2;
	private Point3D p3;

public Triangle(Point3D p1, Point3D p2, Point3D p3, Color color, Material material) { //constructor with parameters
	this.p1 = p1;
	this.p2 = p2;
	this.p3 = p3;
	this.setEmission(color);
	this.setMaterial(material);
}
public Triangle() { //default constructor
	this.p1 = new Point3D();
	this.p2 = new Point3D();
	this.p3 = new Point3D();
}
public Triangle(Triangle temp) { //copy constructor
	this.p1 = temp.getP1();
	this.p2 = temp.getP2();
	this.p3 = temp.getP3();
	this.setEmission(temp.getEmission());
	this.setMaterial(temp.getMaterial());
}

public Triangle(Point3D p1, Point3D p2, Point3D p3, Color color) {
	this.p1 = p1;
	this.p2 = p2;
	this.p3 = p3;
	this.setEmission(color);
}

	public Point3D getP1() { //getters and setters
	return p1;
}
public void setP1(Point3D p1) {
	this.p1 = p1;
}
public Point3D getP2() {
	return p2;
}
public void setP2(Point3D p2) {
	this.p2 = p2;
}
public Point3D getP3() {
	return p3;
}
public void setP3(Point3D p3) {
	this.p3 = p3;
}
@Override
public boolean equals(Object obj) { //override of equals
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Triangle other = (Triangle) obj;
	if (p1 == null) {
		if (other.p1 != null)
			return false;
	} else if (!p1.equals(other.p1))
		return false;
	if (p2 == null) {
		if (other.p2 != null)
			return false;
	} else if (!p2.equals(other.p2))
		return false;
	if (p3 == null) {
		if (other.p3 != null)
			return false;
	} else if (!p3.equals(other.p3))
		return false;
	return true;
}
@Override
public String toString() { //override of toString
	return "Triangle [p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + "]";
}
@Override
public ArrayList<GeoPoint> findIntersections(Ray ray) {
		Vector v1 = new Vector(this.p1.subtract(ray.getP0()));//v1 = p1-p0
		Vector v2 = new Vector(this.p2.subtract(ray.getP0()));//v2 = p2-p0
		Vector v3 = new Vector(this.p3.subtract(ray.getP0()));//v3 = p3-p0
		Vector n1 = new Vector(v1.crossProduct(v2).normalize());//n1 = v1 X v2
		Vector n2 = new Vector(v2.crossProduct(v3).normalize());//n2 = v2 X v3
		Vector n3 = new Vector(v3.crossProduct(v1).normalize());//n3 = v3 X v1
		double vn1 = ray.getDirection().dotProduct(n1);//vn1 = v * n1
		double vn2 = ray.getDirection().dotProduct(n2);//vn2 = v * n2
		double vn3 = ray.getDirection().dotProduct(n3);//vn3 = v * n3
		if(vn1 > 0 && vn2 > 0 && vn3 > 0 || vn1 < 0 && vn2 < 0 && vn3 < 0) {//if all are above zero or below do the following
			ArrayList<GeoPoint> myPoints = (ArrayList<GeoPoint>) new Plane(this.getP1(),this.getNormal(this.getP1()),this.getEmission(),this.getMaterial()).findIntersections(ray);//find the intersection on the plane of the triangle
			return myPoints;
		}
		return null;
	}
@Override
public Vector getNormal(Point3D point) { // method that returns the normal of the Triangle
	Vector v1=new Vector(p1.subtract(p3));
	Vector v2=new Vector(p2.subtract(p3));
	Vector n1=new Vector(v1.crossProduct(v2));
	return n1.normalize();
}
}
