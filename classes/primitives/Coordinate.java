package primitives;

public class Coordinate {

private double coordinate;

public Coordinate(double coordinate) { //constructor with parameter
	this.coordinate = coordinate;
}
public Coordinate() { //default constructor
	this.coordinate = 0.0;
}
public Coordinate(Coordinate other) { //copy constructor
	this.coordinate = other.getCoordinate();
}

public double getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(double coordinate) { 
		this.coordinate = coordinate;
	}

	@Override
	 public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Coordinate)) return false;
     //   return _coord - ((Coordinate)obj)._coord==0;
        return Util.isZero(coordinate - ((Coordinate)obj).coordinate);
    }


	@Override
	public String toString() {
		return "Coordinate [coordinate=" + coordinate + "]";
	}
}