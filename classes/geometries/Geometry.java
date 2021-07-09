package geometries;

import java.awt.Color;
import java.util.List;
import java.util.Objects;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public abstract class Geometry {
	private Color emission;
	private Material material;

	public Geometry() {
		this.emission = Color.BLACK;
		this.material = new Material();
	}

	public Color getEmission() {
		return emission;
	}

	public void setEmission(Color emission) {
		this.emission = emission;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Geometry geometry = (Geometry) o;
		return Objects.equals(emission, geometry.emission) &&
				Objects.equals(material, geometry.material);
	}

	@Override
	public String toString() {
		return "Geometry{" +
				"emission=" + emission +
				", material=" + material +
				'}';
	}

	public abstract List<GeoPoint> findIntersections(Ray ray) ;

	public abstract Vector getNormal(Point3D point);
}
