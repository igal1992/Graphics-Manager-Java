package elements;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;
import java.util.Objects;

public class spotLight extends PointalLight {
    Vector direction;

    public spotLight(Color intensity, Point3D point, double num1, double num2, double num3,Vector vec) { //constructor
        super(intensity, point, num1, num2, num3);
        this.direction=vec;
    }
    public spotLight() { //default constructor
        super();
        this.direction=new Vector();
    }
    public spotLight(spotLight temp) { //copy constructor
        super(temp.intensity,temp.getPosition(),temp.getkC(),temp.getkL(),temp.getkQ());
        this.direction=temp.getDirection();
    }

    public Vector getDirection() { //getter
        return direction;
    }

    public void setDirection(Vector direction) { //setter
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) { //equals
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        spotLight spotLight = (spotLight) o;
        return Objects.equals(direction, spotLight.direction);
    }

    @Override
    public String toString() { //toString
        return "spotLight{" +
                "direction=" + direction +
                '}';
    }
    @Override
    public Color getIntensity(Point3D point) {
        double distance = this.getPosition().distance(point);//p->point
        double bottom = this.getkC() + (this.getkL() * distance) + (this.getkQ() * Math.pow(distance,2));//kc+kl*d+kq*d^2
        Color top = multColorByK(this.direction.normalize().dotProduct(this.getL(point)), this.intensity);//direction*l*point*intensity
        return (multColorByK(1/bottom, top));//top/bottom
    }
    @Override
    public Vector getL(Point3D point) {
        return this.getPosition().subtract(point).normalize();
    }//pl-p

}
