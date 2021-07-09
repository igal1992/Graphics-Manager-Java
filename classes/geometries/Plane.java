package geometries;
import primitives.Material;
import primitives.Vector;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

public class Plane extends Geometry{
private Point3D q;
private Vector n;

public Plane(Point3D p, Vector n, Color color, Material material) { //constructor with parameters
    this.n = n.normalize();
    this.q = new Point3D(p);
    this.setEmission(color);
    this.setMaterial(material);
}
public Plane() { //default constructor
	this.q = new Point3D();
	this.n = new Vector();
}
public Plane(Plane temp) { //copy constructor
this.q=temp.getQ();
this.n=temp.getN();
this.setEmission(temp.getEmission());
this.setMaterial(temp.getMaterial());
}
public Plane(Point3D p1, Point3D p2, Point3D p3,Material material) { // constructor of 3 Points
    // if p1=p2 or p1=p3 - an exception will be thrown
    Vector v1 = p2.subtract(p1);
    Vector v2 = p3.subtract(p1);

//if the points are in the same line - X-product will throw an exception
    this.n = v1.crossProduct(v2).normalize();
    this.n = new Vector(Double.parseDouble(new DecimalFormat("##.##").format(this.getN().getHead().getX().getCoordinate())),Double.parseDouble(new DecimalFormat("##.##").format(this.getN().getHead().getY().getCoordinate())),Double.parseDouble(new DecimalFormat("##.##").format(this.getN().getHead().getZ().getCoordinate())));
    q = new Point3D(p1);
    this.setMaterial(material);
}


public Point3D getQ() { //getters and setters
	return q;
}

public void setQ(Point3D q) {
	this.q = q;
}

public Vector getN() {
	return n;
}

public void setN(Vector n) {
	this.n = n;
}



@Override
public boolean equals(Object obj) { //override of equals
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Plane other = (Plane) obj;
	if (n == null) {
		if (other.n != null)
			return false;
	} else if (!n.equals(other.n))
		return false;
	if (q == null) {
		if (other.q != null)
			return false;
	} else if (!q.equals(other.q))
		return false;
	return true;
}
@Override
public String toString() { //override of toString
	return "Plane [q=" + q + ", n=" + n + "]";
}
@Override
public List<GeoPoint> findIntersections(Ray ray) {// method that finds the intersections of ray with Plane
	double numerator=0,denominator=0,t=0;
    List<GeoPoint> myPoints= new ArrayList<GeoPoint>();
    if(!this.getQ().equals(ray.getP0())) {
         numerator = this.getN().dotProduct(this.getQ().subtract(ray.getP0()));
         denominator = this.getN().dotProduct(ray.getDirection());
         t = numerator/denominator;
        if(t<0 || numerator==0 ||  denominator==0) {
            return null;
        }else{
            Vector tv = new Vector(ray.getDirection().scale(t));
            myPoints.add(new GeoPoint(this,ray.getP0().add(tv)));
            return myPoints;
        }}else if(this.getQ().equals(ray.getP0())){
            return null;
        }
        return myPoints;
}


@Override
public Vector getNormal(Point3D point) { // method that returns the normal of the Plane
	return this.getN();
}
}
