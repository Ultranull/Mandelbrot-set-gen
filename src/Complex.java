public class Complex {
    public double x,y;
    public Complex(double r,double i){
        x=r;y=i;
    }
    public Complex(){
        x=0;
        y=0;
    }
    public Complex abs(){
        return new Complex(Math.abs(x),Math.abs(y));
    }
    public Complex swap(){
        return new Complex(y,x);
    }

    public double mag(){
        return x*x+y*y;
    }

    public Complex add(Complex c) {
        return new Complex(this.x + c.x, this.y + c.y);
    }


    public Complex subtract(Complex c) {
        return new Complex(this.x - c.x, this.y - c.y);
    }

    public Complex multiply(double scalar) {
        return new Complex(x * scalar, y * scalar);
    }

    public Complex multiply(Complex c) {
        return new Complex(x * c.x - y * c.y, x * c.y + y * c.x);
    }

    public Complex divide(double scalar) {
        return multiply(1.0 / scalar);
    }

    public Complex divide(Complex c) {
        return multiply(c.getConjugate()).multiply(1.0 / (c.x * c.x + c.y * c.y));
    }

    public Complex getConjugate() {
        return new Complex(x, y * -1);
    }

    public Complex pow(int exp) {
        Complex c = new Complex(x, y);
        for (int k = 1; k < exp; k++) {
            c = multiply(c);
        }
        return c;
    }


    public double getReal() {
        return x;
    }

    public double getImaginary() {
        return y;
    }

    @Override
    public String toString() {
        return "" + x + (y >= 0 ? "+" : "") + y + "i";
    }
}
