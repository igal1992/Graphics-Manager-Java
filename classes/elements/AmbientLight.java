package elements;
import java.awt.Color;
import primitives.Point3D;
import primitives.Vector;

public class AmbientLight extends Light {
    private double kA;

    public AmbientLight(Color intensity,double kA) { //constructor
        super(intensity);
        this.kA=kA;
    }
    public AmbientLight(){ //default constructor
        super(new Color(0,0,0));
        this.kA=0.1;
    }
    public AmbientLight(AmbientLight temp){ //copy constructor
        super(temp.intensity);
        this.kA=temp.getkA();
    }

    public double getkA() { //getter
        return kA;
    }

    public void setkA(double kA) { //setter
        this.kA = kA;
    }

    @Override
    public Color getIntensity(Point3D point) { //getter
        return this.multColorByK(this.kA,this.intensity);
    }

    @Override
    public Vector getL(Point3D point) { //setter
        return new Vector();
    }
    @Override
    public boolean equals(Object o) { //equals
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AmbientLight that = (AmbientLight) o;
        return Double.compare(that.kA, kA) == 0;
    }

    @Override
    public String toString() { //toString
        return "AmbientLight{" +
                "kA=" + kA +
                '}';
    }
}
