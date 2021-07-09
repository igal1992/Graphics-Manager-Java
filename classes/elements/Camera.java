package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Camera {
private Point3D p0;
private Vector vup;
private Vector vright;
private Vector vto;

public Camera() {
	this.p0=new Point3D();
	this.vup=new Vector();
	this.vright=new Vector();
	this.vto=new Vector();
}
public Camera(Point3D p0,Vector vto,Vector vup) {
	this.p0=new Point3D(p0);
	this.vup=new Vector(vup);
	this.vto=new Vector(vto);
	this.vright=vto.crossProduct(vup);
}

/**
 * @return the p0
 */
public Point3D getP0() {
	return p0;
}
/**
 * @param p0 the p0 to set
 */
public void setP0(Point3D p0) {
	this.p0 = p0;
}
/**
 * @return the vup
 */
public Vector getVup() {
	return vup;
}
/**
 * @param vup the vup to set
 */
public void setVup(Vector vup) {
	this.vup = vup;
}
/**
 * @return the vright
 */
public Vector getVright() {
	return vright;
}
/**
 * @param vright the vright to set
 */
public void setVright(Vector vright) {
	this.vright = vright;
}
/**
 * @return the vto
 */
public Vector getVto() {
	return vto;
}
/**
 * @param vto the vto to set
 */
public void setVto(Vector vto) {
	this.vto = vto;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Camera other = (Camera) obj;
	if (p0 == null) {
		if (other.p0 != null)
			return false;
	} else if (!p0.equals(other.p0))
		return false;
	if (vright == null) {
		if (other.vright != null)
			return false;
	} else if (!vright.equals(other.vright))
		return false;
	if (vto == null) {
		if (other.vto != null)
			return false;
	} else if (!vto.equals(other.vto))
		return false;
	if (vup == null) {
		if (other.vup != null)
			return false;
	} else if (!vup.equals(other.vup))
		return false;
	return true;
}
@Override
public String toString() {
	return "Camera [p0=" + p0 + ", vup=" + vup + ", vright=" + vright + ", vto=" + vto + "]";
}
public Ray constructRayThroughPixel(int nX,int nY,int j,int i,double screenDistance,double screenWidth,double screenHeight) {
	Point3D Pc,Pixel;
	Vector rayVector,tempRight,tempUp,tempSub;
	Pc=new Point3D(p0.add(vto.scale(screenDistance))); //image center
	double Rx,Ry,x,y,tempY,tempX;
	Rx=screenWidth/nX; //Ratio width
	Ry=screenHeight/nY; //Ratio height
	tempY=nY-1;
	tempX=nX-1;
	y=(j-(tempY/2))*Ry;
	x=(i-(tempX/2))*Rx;
	tempRight=new Vector(vright.scale(y));
	tempUp=new Vector(vup.scale(x));
	tempSub=new Vector(tempRight.subtract(tempUp));
	Pixel=new Point3D(Pc.add(tempSub)); //Pixel[i,j]
	rayVector=new Vector(Pixel.subtract(p0)); // V[i,j]
	return new Ray(p0,rayVector.normalize());
	
}
}
