package primitives;

/**
 * The class used to describe bodies' material in a scene
 * by its shininess, opacity and reflection
 */
public class Material {
    public double Kd=0d;
    public double Ks=0d;
    public int nShininess=0;
    /**
     *  Promote transparency
     */
    public double Kt=0.0;
    /**
     *Promote reflection
     */
    public double Kr=0.0;

    public Material setKd(double kd) {
        Kd = kd;
        return this;
    }

    public Material setKs(double ks) {
        Ks = ks;
        return this;
    }

    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * Kt setter
     * @param kt
     * @return the material- chaining method
     */
    public Material setKt(double kt) {
        Kt = kt;
        return this;
    }

    /**
     * Kr setter
     * @param kr
     * @return the material- chaining method
     */
    public Material setKr(double kr) {
        Kr = kr;
        return this;
    }

}
