package primitives;

public class Point3D  {
private Coordinate x;
private Coordinate y;
private Coordinate z;

public Point3D(Coordinate x,Coordinate y,Coordinate z) { //constructor with parameters
	this.x = x;
	this.y = y;
	this.z = z;
}
public Point3D() { //default constructor
	this.x = new Coordinate();
	this.y = new Coordinate();
	this.z = new Coordinate();
}
public Point3D(Point3D temp) { //copy constructor
	this.x = new Coordinate(temp.getX());
	this.y = new Coordinate(temp.getY());
	this.z = new Coordinate(temp.getZ());
}
public Point3D(int x, int y, int z) {
	this.x=new Coordinate(x);
	this.y=new Coordinate(y);
	this.z=new Coordinate(z);
	}
	public Point3D(double x, double y, double z) {
		this.x=new Coordinate(x);
		this.y=new Coordinate(y);
		this.z=new Coordinate(z);
	}
public Coordinate getX() {
	return x;
}
public void setX(Coordinate x) {
	this.x = x;
}
public Coordinate getY() {
	return y;
}
public void setY(Coordinate y) {
	this.y = y;
}
public Coordinate getZ() {
	return z;
}
public void setZ(Coordinate z) {
	this.z = z;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Point3D other = (Point3D) obj;
	if (x == null) {
		if (other.x != null)
			return false;
	} else if (!x.equals(other.x))
		return false;
	if (y == null) {
		if (other.y != null)
			return false;
	} else if (!y.equals(other.y))
		return false;
	if (z == null) {
		if (other.z != null)
			return false;
	} else if (!z.equals(other.z))
		return false;
	return true;
}
@Override
public String toString() {
	return "Point3D [x=" + x + ", y=" + y + ", z=" + z + "]";
}

public Point3D add(Vector v) { //method that adds point to vector
	double x,y,z;
	x=this.x.getCoordinate()+v.getHead().getX().getCoordinate();
	y=this.y.getCoordinate()+v.getHead().getY().getCoordinate();
	z=this.z.getCoordinate()+v.getHead().getZ().getCoordinate();
	return (new Point3D(x,y,z));
}
public Vector subtract(Point3D other) { //method that substract point from vector
	double x,y,z;
	 if(this.equals(other)){
         throw new IllegalArgumentException("Subtract P from P is illegal");
     }
	x=this.x.getCoordinate()-other.getX().getCoordinate();
	y=this.y.getCoordinate()-other.getY().getCoordinate();
	z=this.z.getCoordinate()-other.getZ().getCoordinate();
	return (new Vector(new Point3D(x,y,z)));
}
public double distance(Point3D other) { //calculate the distance between two 3D Points
	  double xValue1 = this.getX().getCoordinate();
      double yValue1 = this.getY().getCoordinate();
      double zValue1 = this.getZ().getCoordinate();
      double xValue2 = other.getX().getCoordinate();
      double yValue2 = other.getY().getCoordinate();
      double zValue2 = other.getZ().getCoordinate();
      return  Math.sqrt(Math.pow((xValue1-xValue2),2)+Math.pow((yValue1-yValue2),2)+Math.pow((zValue1-zValue2),2));
}
}
