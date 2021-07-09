package primitives;

public class Ray {
	private Point3D p0;
	private Vector direction;

public Ray(Point3D p0,Vector direct) { //constructor with parameters
	this.p0=p0;
	this.direction=direct;
}
public Ray() { //default constructor
this.p0=new Point3D();
this.direction=new Vector();
}
public Ray(Ray temp) { //copy constructor
 this.p0=new Point3D(temp.getP0());
 this.direction=new Vector(temp.getDirection());
}
public Point3D getP0() {   //getters and setters
	return p0;
}
public void setP0(Point3D p0) {
	this.p0 = p0;
}
public Vector getDirection() {
	return direction;
}
@Override
public boolean equals(Object obj) {  //override to equals
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Ray other = (Ray) obj;
	if (direction == null) {
		if (other.direction != null)
			return false;
	} else if (!direction.equals(other.direction))
		return false;
	if (p0 == null) {
		if (other.p0 != null)
			return false;
	} else if (!p0.equals(other.p0))
		return false;
	return true;
}
public void setDirection(Vector direction) {
	this.direction = direction;
}
@Override
public String toString() { //override of toString
	return "Ray [p0=" + p0 + ", direction=" + direction + "]";
}
}
