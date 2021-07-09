package elements;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;
import java.util.Objects;

public class PointalLight extends Light {
    private Point3D position;
    private double kC,kL,kQ;

    public PointalLight(Color intensity,Point3D point,double num1,double num2,double num3) { //constructor
        super(intensity);
        this.position=point;
        this.kC=num1;
        this.kL=num2;
        this.kQ=num3;
    }
    public PointalLight() { //default constructor
        super(Color.BLACK);
        this.position=new Point3D();
        this.kC=0;
        this.kL=0;
        this.kQ=0;
    }
    public PointalLight(PointalLight temp) { //copy constructor
        super(temp.intensity);
        this.position=temp.getPosition();
        this.kC=temp.getkC();
        this.kL=temp.getkL();
        this.kQ=temp.getkQ();
    }

    public Point3D getPosition() { //getters and setters
        return position;
    }

    public void setPosition(Point3D position) {
        this.position = position;
    }

    public double getkC() {
        return kC;
    }

    public void setkC(double kC) {
        this.kC = kC;
    }

    public double getkL() {
        return kL;
    }

    public void setkL(double kL) {
        this.kL = kL;
    }

    public double getkQ() {
        return kQ;
    }

    public void setkQ(double kQ) {
        this.kQ = kQ;
    }

    @Override
    public boolean equals(Object o) { //equals
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PointalLight that = (PointalLight) o;
        return Double.compare(that.kC, kC) == 0 &&
                Double.compare(that.kL, kL) == 0 &&
                Double.compare(that.kQ, kQ) == 0 &&
                Objects.equals(position, that.position);
    }

    @Override
    public String toString() { //toString
        return "PointalLight{" +
                "position=" + position +
                ", kC=" + kC +
                ", kL=" + kL +
                ", kQ=" + kQ +
                '}';
    }

    @Override
    public Color getIntensity(Point3D point) {
        double d = this.position.distance(point);
        double denominator = this.kC + (this.kL*d) + (this.kQ*d*d);
        double red = this.intensity.getRed();
        double green = this.intensity.getGreen();
        double blue = this.intensity.getBlue();
        return new Color(Math.min(255,(int)(red/denominator)),Math.min(255,(int)(green/denominator)),Math.min(255,(int)(blue/denominator)));
    }

    @Override
    public Vector getL(Point3D point) {
        return this.position.subtract(point).normalize();
    }
}
