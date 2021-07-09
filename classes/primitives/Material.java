package primitives;

public class Material {

    private double _kd = 0d;
    private double _ks = 0d;
    private double _nShininess = 1;

    public  Material(Material material) {
        _kd =material.getKd() ;
        _ks = material.getKs();
        _nShininess = material.getShininess();
    }
    public  Material() {
        _kd = 0d;
        _ks = 0d;
        _nShininess = 1;
    }
    public  Material(double kd,double ks, double nS){
        _kd = kd;
        _ks = ks;
        _nShininess = nS;
    }
    /**
     * Diffusion attenuation factor getter/setter
     */
    public double getKd() { return _kd; }
    public void setKd(double kd) { _kd = kd; }

    /**
     * Specular attenuation factor getter/setter
     */
    public double getKs() { return _ks; }
    public void setKs(double ks) { _ks = ks; }

    /**
     * Shininess level getter/setter
     */
    public double getShininess() { return _nShininess; }
    public void setShininess(int nShininess) { _nShininess = nShininess; }


}