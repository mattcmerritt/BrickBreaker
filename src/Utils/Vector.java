package Utils;

import java.lang.Math;

public class Vector {
    private double xComponent, yComponent;
    
    public Vector(double xComponent, double yComponent) {
        this.xComponent = xComponent;
        this.yComponent = yComponent;
    }

    public double getXComponent() {
        return xComponent;
    }

    public double getYComponent() {
        return yComponent;
    }

    public void setXComponent(double xComponent) {
        this.xComponent = xComponent;
    }

    public void setYComponent(double yComponent) {
        this.yComponent = yComponent;
    }

    public void normalize() {
        double magnitude = Math.sqrt(xComponent * xComponent + yComponent * yComponent);
        xComponent /= magnitude;
        yComponent /= magnitude;
    }

    public int getXComponentRounded() {
        return (int) Math.round(xComponent);
    }

    public int getYComponentRounded() {
        return (int) Math.round(yComponent);
    }
}
