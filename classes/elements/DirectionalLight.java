package elements;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;
import java.util.Objects;

public class DirectionalLight extends Light {
    private Vector direction;

    public DirectionalLight(Color intensity, Vector direction) { //constructor
        super(intensity);
        this.direction=direction;
    }
    public DirectionalLight() { //default constructor
        super(Color.BLACK);
        this.direction=new Vector();
    }
    public DirectionalLight(DirectionalLight temp){//copy constructor
        super(temp.intensity);
        this.direction= temp.getDirection();
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
        DirectionalLight that = (DirectionalLight) o;
        return Objects.equals(direction, that.direction);
    }

    @Override
    public String toString() { //toString
        return "DirectionalLight{" +
                "direction=" + direction +
                '}';
    }

    @Override
    public Color getIntensity(Point3D point) {
        return this.intensity;
    }

    @Override
    public Vector getL(Point3D point) {
        return direction.normalize().scale(-1);
    }
}
