package appcompgraficaprojeto2.model;

public class ET {
    private double ymax;
    private double xmin;
    private double incr;

    public ET(double ymax, double xmin, double incr) {
        this.ymax = ymax;
        this.xmin = xmin;
        this.incr = incr;
    }

    public double getYmax() {
        return ymax;
    }

    public void setYmax(double ymax) {
        this.ymax = ymax;
    }

    public double getXmin() {
        return xmin;
    }

    public void setXmin(double xmin) {
        this.xmin = xmin;
    }

    public double getIncr() {
        return incr;
    }

    public void setIncr(double incr) {
        this.incr = incr;
    }
}
