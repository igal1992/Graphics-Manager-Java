package geometries;

import java.awt.Color;
import java.util.List;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Geometry {
private double radius;
private double height;
private Ray axis;
public Cylinder(double radius, double height, Ray axis, Color color, Material material) { //constructor with parameters
	this.radius = radius;
	this.height = height;
	this.axis = axis;
    this.setEmission(color);;
    this.setMaterial(material);
}
public Cylinder() { //default constructor
	this.radius = 0.0;
	this.height = 0.0;
	this.axis = new Ray();
}
public Cylinder(Cylinder temp) { //copy constructor
	this.radius = temp.getRadius();
	this.height = temp.getHeight();
	this.axis = temp.getAxis();
	this.setEmission(temp.getEmission());
	this.setMaterial(temp.getMaterial());
}

public double getRadius() {
	return radius;
}

public void setRadius(double radius) {
	this.radius = radius;
}
public double getHeight() {
	return height;
}
public void setHeight(double height) {
	this.height = height;
}
public Ray getAxis() {
	return axis;
}
public void setAxis(Ray axis) {
	this.axis = axis;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Cylinder other = (Cylinder) obj;
	if (axis == null) {
		if (other.axis != null)
			return false;
	} else if (!axis.equals(other.axis))
		return false;
	if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
		return false;
	if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
		return false;
	return true;
}
@Override
public String toString() {
	return "Cylinder [radius=" + radius + ", height=" + height + ", axis=" + axis + "]";
}
@Override
public List<GeoPoint> findIntersections(Ray ray) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Vector getNormal(Point3D point) {
	// TODO Auto-generated method stub
	return null;
}
}
