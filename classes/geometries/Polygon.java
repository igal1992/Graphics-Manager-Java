package geometries;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import primitives.*;


public class Polygon extends Geometry {
	private List <Point3D> points;

public Polygon(List<Point3D> points, Color color, Material material) { //constructor with parameter
	this.setEmission(color);
	this.points = points;
	this.setMaterial(material);
}
public Polygon() { //default constructor
	this.points=new ArrayList<Point3D>();
}
public Polygon(Polygon temp) { //copy constructor
	this.points = temp.getPoints();
	this.setEmission(temp.getEmission());
	this.setMaterial(temp.getMaterial());
}
public List<Point3D> getPoints() {
	return points;
}
public void setPoints(List<Point3D> points) {
	this.points = points;
}
public Polygon(Color color,Point3D... vertices) {
	if (vertices.length < 3)
		throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
	points = Util.listOf(vertices);
	Plane _plane = new Plane(vertices[0], vertices[1], vertices[2],new Material());
	if (vertices.length == 3)
		return; // no need for more tests for a Triangle

	Vector n = _plane.getNormal(null);

	Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
	Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);
	boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
	for (int i = 1; i < vertices.length; ++i) {
		if (!Util.isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
			throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
		edge1 = edge2;
		edge2 = vertices[i].subtract(vertices[i - 1]);
		if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
			throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
	}
	this.setEmission(color);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Polygon other = (Polygon) obj;
	if (points == null) {
		if (other.points != null)
			return false;
	} else if (!points.equals(other.points))
		return false;
	return true;
}
@Override
public String toString() {
	return "Polygon [points=" + points + "]";
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
