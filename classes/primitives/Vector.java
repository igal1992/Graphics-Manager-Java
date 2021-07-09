package primitives;

public class Vector {
private Point3D head;

public Vector() { //default constructor
	this.head = new Point3D(1,1,1);
}
public Vector(double x, double y, double z) {
	this.head = new Point3D(new Coordinate(x),new Coordinate(y), new Coordinate(z));
	if ((new Point3D()).equals(this.head))
		throw new IllegalArgumentException("Zero vector is not allowed");
}
public Vector(Point3D p) {
	if ((new Point3D()).equals(p))
		throw new IllegalArgumentException("Zero vector is not allowed");
	this.head = new Point3D(p);
}

public Vector(Vector other) {
	this.head = new Point3D(other.getHead());
}
public Vector(Point3D header,Point3D start){
    this.head = header.subtract(start).getHead();
}

public Vector(int num1, int num2, int num3) {
	this.head=new Point3D(num1,num2,num3);
	}

	public Point3D getHead() {  //getters and setters
	return this.head;
}
public void setHead(Point3D head) { 
	this.head = head;
}


@Override
public boolean equals(Object obj) { //override to equals
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Vector other = (Vector) obj;
	if (head == null) {
		if (other.head != null)
			return false;
	} else if (!head.equals(other.head))
		return false;
	return true;
}
@Override
public String toString() {    //override of toString
	return "Vector [head=" + head + "]";
}

public double length() { //method that calculates the length of the Vector
	double distance,part1,part2,part3;
	part1=this.getHead().getX().getCoordinate();
	part2=this.getHead().getY().getCoordinate();
	part3=this.getHead().getZ().getCoordinate();
	distance=Math.sqrt(Math.pow(part1,2)+Math.pow(part2,2)+Math.pow(part3,2));
	return distance;
}
public Vector normalize() { //method that normalize the Vector
	   Vector answer = new Vector();
       answer.getHead().getX().setCoordinate((this.getHead().getX().getCoordinate())/(this.length()));
       answer.getHead().getY().setCoordinate((this.getHead().getY().getCoordinate())/(this.length()));
       answer.getHead().getZ().setCoordinate((this.getHead().getZ().getCoordinate())/(this.length()));
       return answer;
}
public Vector add(Vector other) { //method that adds one Vector to another
    if(this.equals(other.scale(-1))){
        throw new IllegalArgumentException ("Add v plus -v is illegal");
    }
    Vector answer = new Vector();
    answer.getHead().getX().setCoordinate(this.getHead().getX().getCoordinate()+other.getHead().getX().getCoordinate());
    answer.getHead().getY().setCoordinate(this.getHead().getY().getCoordinate()+other.getHead().getY().getCoordinate());
    answer.getHead().getZ().setCoordinate(this.getHead().getZ().getCoordinate()+other.getHead().getZ().getCoordinate());
    return answer;
}
public Vector subtract(Vector other) { //method that substract one Vector from another
    if(this.equals(other)){
        //throw new Exception("Subtract v from v is illegal");
        throw new IllegalArgumentException("Subtract v from v is illegal");
    }
    Vector answer = new Vector();
    answer.getHead().getX().setCoordinate(this.getHead().getX().getCoordinate()-other.getHead().getX().getCoordinate());
    answer.getHead().getY().setCoordinate(this.getHead().getY().getCoordinate()-other.getHead().getY().getCoordinate());
    answer.getHead().getZ().setCoordinate(this.getHead().getZ().getCoordinate()-other.getHead().getZ().getCoordinate());
    return answer;
}
public Vector scale(double scalar) { //method that multiply Vector with scalar
    if(scalar==0){
        throw new IllegalArgumentException("scalar canno't be 0");
    }
    Vector answer = new Vector();
    answer.getHead().getX().setCoordinate((this.getHead().getX().getCoordinate()) * scalar);
    answer.getHead().getY().setCoordinate((this.getHead().getY().getCoordinate()) * scalar);
    answer.getHead().getZ().setCoordinate((this.getHead().getZ().getCoordinate()) * scalar);
    return answer;
}
public Vector crossProduct(Vector other) { //Vector multiplication
	 if(this.getHead().getX().getCoordinate()/other.getHead().getX().getCoordinate()==
	            this.getHead().getY().getCoordinate()/other.getHead().getY().getCoordinate()&&
	            this.getHead().getY().getCoordinate()/other.getHead().getZ().getCoordinate()==
	            this.getHead().getZ().getCoordinate()/other.getHead().getZ().getCoordinate()
	        ){
	            //throw new Exception("Subtract v from v is illegal");
	            throw new IllegalArgumentException("cross producting parallel vectors is illegal");
	        }

     Vector answer= new Vector();
        double uVal1 = this.getHead().getX().getCoordinate();
        double uVal2 = this.getHead().getY().getCoordinate();
        double uVal3 = this.getHead().getZ().getCoordinate();
        double vVal1 = other.getHead().getX().getCoordinate();
        double vVal2 = other.getHead().getY().getCoordinate();
        double vVal3 = other.getHead().getZ().getCoordinate();
        answer.getHead().getX().setCoordinate((uVal2*vVal3)-(uVal3*vVal2));
        answer.getHead().getY().setCoordinate((uVal3*vVal1)-(uVal1*vVal3));
        answer.getHead().getZ().setCoordinate((uVal1*vVal2)-(uVal2*vVal1));
        return answer;
}
public double dotProduct(Vector other) { //scalar multiplication
	   double uValue1 = this.getHead().getX().getCoordinate();
       double uValue2 = this.getHead().getY().getCoordinate();
       double uValue3 = this.getHead().getZ().getCoordinate();
       double vValue1 = other.getHead().getX().getCoordinate();
       double vValue2 = other.getHead().getY().getCoordinate();
       double vValue3 = other.getHead().getZ().getCoordinate();
       return (uValue1*vValue1)+(uValue2*vValue2)+(uValue3*vValue3);
}
}
