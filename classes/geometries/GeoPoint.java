package geometries;

import primitives.Material;
import primitives.Point3D;

public class GeoPoint {

    private Geometry geometry;
	private Point3D point;

	public GeoPoint(Geometry geometry2, Point3D point2) { //constructor with 3DPoint
		this.geometry = geometry2;
		this.point = point2;
	}

	public GeoPoint(Geometry geometry, GeoPoint geoPoint) { // constructor with GeoPoint
		this.geometry=geometry;
		this.point=geoPoint.getPoint();
	}

	public Geometry getGeometry() {
		return geometry;
	} //getters and setters

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public Point3D getPoint() {
		return point;
	}

	public void setPoint(Point3D point) {
		this.point = point;
	}


	@Override
    public boolean equals(Object obj) { //equals method
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof GeoPoint)) return false;
        GeoPoint oth = (GeoPoint)obj;
        return this.geometry == oth.geometry && this.point.equals(oth.point);
    }
	@Override
	public String toString() { //toString method
		return "GeoPoint{" +
				"geometry=" + geometry +
				", point=" + point +
				'}';
	}
}
