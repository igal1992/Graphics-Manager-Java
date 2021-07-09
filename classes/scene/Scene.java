package scene;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.awt.Color;
import elements.AmbientLight;
import elements.Camera;
import elements.Light;
import geometries.Geometry;

public class Scene {
private String name;
private List <Geometry> geometries;
private Camera camera;
private double screenDistance;
private Color background;
private AmbientLight ambientLight;
private List<Light> lights;

public Scene(String name, List<Geometry> geometries,AmbientLight ambientLight,List<Light> lights) { //constructor with parameters
	this.name = name;
	this.geometries = geometries;
	this.ambientLight=ambientLight;
	this.lights=lights;
}
public Scene() { //default constructor
	this.name ="";
	this.geometries = new ArrayList<Geometry>();
	this.ambientLight= new AmbientLight();
	this.lights=new ArrayList<Light>();
}
public Scene(Scene temp) { //copy constructor
	this.name = temp.getName();
	this.geometries = temp.getGeometries();
	this.ambientLight=temp.getAmbientLight();
	this.lights=temp.getLights();
}
public Scene(String name) {
	this.name=name;
	this.geometries = new ArrayList<Geometry>();
	this.ambientLight= new AmbientLight();
	this.lights=new ArrayList<Light>();
}

	public AmbientLight getAmbientLight() { //getters and setters
		return ambientLight;
	}

	public void setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
	}

	public List<Light> getLights() {
		return lights;
	}

	public void setLights(List<Light> lights) {
		this.lights = lights;
	}

	public String getName() {
	return name;
}
/**
 * @return the camera
 */
public Camera getCamera() {
	return camera;
}
/**
 * @param camera the camera to set
 */
public void setCamera(Camera camera) {
	this.camera = camera;
}
/**
 * @return the screenDistance
 */
public double getScreenDistance() {
	return screenDistance;
}
/**
 * @param screenDistance the screenDistance to set
 */
public void setScreenDistance(double screenDistance) {
	this.screenDistance = screenDistance;
}
/**
 * @return the backgroundColor
 */
public Color getBackground() {
	return background;
}
/**
 * @param background the backgroundColor to set
 */
public void setBackground(Color background) {
	this.background = background;
}
public void setName(String name) {
	this.name = name;
}
public List<Geometry> getGeometries() {
	return geometries;
}
public void setGeometries(List<Geometry> geometries) {
	this.geometries = geometries;
}

	@Override
	public boolean equals(Object o) { //equals
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Scene scene = (Scene) o;
		return Double.compare(scene.screenDistance, screenDistance) == 0 &&
				Objects.equals(name, scene.name) &&
				Objects.equals(geometries, scene.geometries) &&
				Objects.equals(camera, scene.camera) &&
				Objects.equals(background, scene.background) &&
				Objects.equals(ambientLight, scene.ambientLight) &&
				Objects.equals(lights, scene.lights);
	}

	@Override
	public String toString() { //toString
		return "Scene{" +
				"name='" + name + '\'' +
				", geometries=" + geometries +
				", camera=" + camera +
				", screenDistance=" + screenDistance +
				", background=" + background +
				", ambientLight=" + ambientLight +
				", lights=" + lights +
				'}';
	}

	public void addGeometry(Geometry geometry) {
	// TODO Auto-generated method stub
	   geometries.add(geometry);
}
public void addLight(Light light){
	lights.add(light);
}
}
