package geometries;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Sphere extends Geometry {
	private Point3D center;
	private double radius;
public Sphere(Point3D center, double radius, Color color, Material material) { //constructor with parameters
	this.center = center;
	this.radius = radius;
	this.setEmission(color);
	this.setMaterial(material);
}
	public Sphere(Point3D center, double radius, Color color) { //constructor with parameters
		this.center = center;
		this.radius = radius;
		this.setEmission(color);
	}
public Sphere() { //default constructor
	this.center = new Point3D();
	this.radius = 0.0;
}
public Sphere(Sphere temp) { //copy constructor
	this.center =new Point3D( temp.getCenter());
	this.radius = temp.getRadius();
	this.setEmission(temp.getEmission());
	this.setMaterial(temp.getMaterial());
}

public Point3D getCenter() { //getters and setters
	return center;
}

public void setCenter(Point3D center) {
	this.center = center;
}

public double getRadius() {
	return radius;
}

public void setRadius(double radius) {
	this.radius = radius;
}
@Override
public boolean equals(Object obj) { //override of equals
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Sphere other = (Sphere) obj;
	if (center == null) {
		if (other.center != null)
			return false;
	} else if (!center.equals(other.center))
		return false;
	if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
		return false;
	return true;
}
@Override
public String toString() {  //override of toString
	return "Sphere [center=" + center + ", radius=" + radius + "]";
}
@Override
public List<GeoPoint> findIntersections(Ray ray) { // method that finds the intersections of ray with Sphere
    ArrayList<GeoPoint> myPoints = new ArrayList<>();
    Point3D P0 = ray.getP0();  // start point of the ray
    Point3D O = this.getCenter(); // center of the Sphere 0,0,100
    double r = this.getRadius(); // radius of the Sphere 20
    Vector v = ray.getDirection().normalize(); //the ray vector
    Vector u = O.subtract(P0); //
    double tm = v.dotProduct(u); //
    double d = Math.sqrt((u.length()*u.length())-tm*tm); //
    if(d>r) // if d>r than the ray is out of the Sphere bounds
        return  null;

    else { // else there are 1 or 2 intersections (t1,t2)
        double th = Math.sqrt(r*r-d*d);//
        double t1 = tm+th; //
        double t2 = tm-th; //

        if(t1>0){
            Point3D P1 = P0.add(v.scale(t1));
            myPoints.add(new GeoPoint(this,P1));
        }
        if(t2>0){
            Point3D P2 = P0.add(v.scale(t2));
            myPoints.add(new GeoPoint(this,P2));
        }

        if(myPoints.size()==0)
            return null;

        return myPoints;
    }
}
@Override
public Vector getNormal(Point3D point) { // method that return the normal of the Sphere
	 Vector normalVector = new Vector((point.subtract(this.getCenter()).normalize()));
     return normalVector;
}
}
