package elements;
import java.awt.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.Objects;

public abstract class Light {
    protected Color intensity;

    public Light(Color intensity) { //constructor
        this.intensity = intensity;
    }

    @Override
    public boolean equals(Object o) { //equals
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Light light = (Light) o;
        return Objects.equals(intensity, light.intensity);
    }

    @Override
    public String toString() { //toString
        return "Light{" +
                "intensity=" + intensity +
                '}';
    }
    public static Color multColorByK(double k,Color color){//multiply Color by Sent K
        double red = k*color.getRed();
        double blue = k*color.getBlue();
        double green = k*color.getGreen();
        return normalizeColor(Math.abs(red),Math.abs(green),Math.abs(blue));//Normalize Values
    }
    public void setIntensity(Color intensity) { //setter
        this.intensity = intensity;
    }
    public abstract Color getIntensity(Point3D point); //getter
    public abstract Vector getL(Point3D point);
    public static Color normalizeColor(double red,double green,double blue){
        return new Color(Math.min(Math.max((int)red,0),255),Math.min(Math.max((int)green,0),255),Math.min(Math.max((int)blue,0),255));//min(max(0,color),255)
    }
}