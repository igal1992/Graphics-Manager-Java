package renderer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import elements.Light;
import geometries.GeoPoint;
import geometries.Geometry;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

public class Renderer {
private Scene scene;
private ImageWriter imageWriter;
public Renderer(Scene scene, ImageWriter imageWriter) { //constructor
	super();
	this.scene = scene;
	this.imageWriter = imageWriter;
}
/**
 * @return the scene
 */
public Scene getScene() {
	return scene;
}  //getters and setters
/**
 * @param scene the scene to set
 */
public void setScene(Scene scene) {
	this.scene = scene;
}
/**
 * @return the imageWriter
 */
public ImageWriter getImageWriter() {
	return imageWriter;
}
/**
 * @param imageWriter the imageWriter to set
 */
public void setImageWriter(ImageWriter imageWriter) {
	this.imageWriter = imageWriter;
}
@Override
public boolean equals(Object obj) { //equals method
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Renderer other = (Renderer) obj;
	if (imageWriter == null) {
		if (other.imageWriter != null)
			return false;
	} else if (!imageWriter.equals(other.imageWriter))
		return false;
	if (scene == null) {
		if (other.scene != null)
			return false;
	} else if (!scene.equals(other.scene))
		return false;
	return true;
}
@Override
public String toString() { //toString method
	return "Renderer [scene=" + scene + ", imageWriter=" + imageWriter + "]";
}

public void renderImage() {
	int i=0,j=0,counter = 1;
	for(i=0;i<imageWriter.getHeight();i++) {
		for (j = 0; j < imageWriter.getWidth(); j++) {
			Ray ray = scene.getCamera().constructRayThroughPixel(imageWriter.getNx(), imageWriter.getNy(),
					j,i,scene.getScreenDistance(), imageWriter.getWidth(), imageWriter.getHeight());
			List<GeoPoint> intersectionPoints = getSceneRayIntersections(ray);
			if (intersectionPoints==null) {
				imageWriter.writePixel(j, i, scene.getBackground());
			} else {
				GeoPoint closestPoint = getClosestPoint(intersectionPoints);
				imageWriter.writePixel(j, i, calcColor(closestPoint));
			}
		}
		imageWriter.writeToImage();
	}
}
private ArrayList<GeoPoint> getSceneRayIntersections(Ray ray) {
	ArrayList<GeoPoint> intersectionPoints = new ArrayList<>();
	ArrayList<GeoPoint> tempIntersections = null;
	for(Geometry geometry:scene.getGeometries()) {
		tempIntersections = (ArrayList<GeoPoint>) geometry.findIntersections(ray);//check if the ray intersect with geometry
		if(tempIntersections != null){
			for(GeoPoint point:tempIntersections){
				intersectionPoints.add(point);
			}
		}
	}
	if(intersectionPoints.size() == 0){ //if there are no intersections return null
		return null;
	}
	return intersectionPoints;//return the intersection points

}
private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) { //method that returns the first geometry point where the ray hits
	double distance = Double.MAX_VALUE;
	Point3D P0 = scene.getCamera().getP0();
	GeoPoint minDistancePoint = null;
	for (GeoPoint point: intersectionPoints) {  //for each point of the intersection return the closest one to the camera
		if (P0.distance(point.getPoint()) < distance) {
			minDistancePoint = point;
			distance = P0.distance(point.getPoint());
		}
	}
	return minDistancePoint;
}
private Color calcColor(GeoPoint gp) { //method  that calculates the color in the point
	Color ambientLight = this.scene.getAmbientLight().getIntensity(gp.getPoint());
	Color emissionLight = gp.getGeometry().getEmission();
	Color diffuseLight = new Color(0, 0, 0);
	Color specularLight = new Color(0, 0, 0);
	for (Light light: this.scene.getLights()){
		if(!shaded(light,light.getL(gp.getPoint()),gp.getPoint(),gp.getGeometry().getNormal(gp.getPoint()))){
			diffuseLight = sumColor(diffuseLight,calcDiffusiveComp(gp.getGeometry().getMaterial().getKd(),
					gp.getGeometry().getNormal(gp.getPoint()),
					light.getL(gp.getPoint()),
					light.getIntensity(gp.getPoint())));
			specularLight = sumColor(specularLight,calcSpecularComp(gp.getGeometry().getMaterial().getKs(),
				new Vector(gp.getPoint(),this.scene.getCamera().getP0()),
					gp.getGeometry().getNormal(gp.getPoint()),
					light.getL(gp.getPoint()),
					gp.getGeometry().getMaterial().getShininess(),
					light.getIntensity(gp.getPoint())));
		}
	}
	return (sumColor(sumColor(ambientLight , emissionLight) ,sumColor( diffuseLight , specularLight)));
}
	private static final double EPS = 0.1;
	private boolean shaded(Light light,Vector l, Point3D point,Vector n){
		Vector lightDirection = l;
		Vector epsVector = n.scale(EPS);
		Point3D newPoint = point.add(epsVector);
		Ray shadowRay = new Ray(newPoint,lightDirection);
		List<GeoPoint> intersectionPoints = getSceneRayIntersections(shadowRay);
		if(intersectionPoints == null){
			return false;
		}
		return true;
	}
	private Color calcDiffusiveComp(double kd,Vector normal,Vector l,Color intensity){
		double x = normal.dotProduct(l);//n*l
		x = kd * x;//n*l*kd
		Color temp = Light.multColorByK(x,intensity);//x*int
		return temp;
	}
	private Color calcSpecularComp(double ks, Vector v, Vector normal,Vector l,double shininess , Color intensity) {
		Vector r = l.subtract(normal.scale(l.dotProduct(normal)*2.0));//l-normal*(l*normal)*2
		double  x = Math.pow(v.dotProduct(r),shininess) * ks;//v*r^k * ks
		Color temp = Light.multColorByK(x,intensity);//x *intensity
		return temp;
	}
	private Color sumColor(Color first,Color second){
	int newRed = first.getRed()+second.getRed();
	int newGreen = first.getGreen()+second.getGreen();
	int newBlue = first.getBlue()+second.getBlue();
	return Light.normalizeColor(newRed,newGreen,newBlue);//normalize colors
	}
}
