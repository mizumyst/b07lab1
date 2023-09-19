public class Polynomial {
    double[] coeffs = {0};

    public Polynomial() {

    }

    public Polynomial(double[] c) {
        coeffs = c;
    }

    public Polynomial add(Polynomial p) {
        Polynomial sumPoly;
        if (p.coeffs.length > coeffs.length) {
            sumPoly = new Polynomial(p.coeffs);
            for (int i=0; i<coeffs.length; i++) {
                    sumPoly.coeffs[i] += coeffs[i];
            }   
        } else {
            sumPoly = new Polynomial(coeffs);
            for (int i=0; i<p.coeffs.length; i++) {
                    sumPoly.coeffs[i] += p.coeffs[i];
            }   
        }

        return sumPoly;
    }

    public double evaluate(double x) {
        double out = 0;
        for (int i=0; i<coeffs.length; i++) {
            out += coeffs[i] * Math.pow(x, i);
        }
        return out;
    }

    public boolean hasRoot(double x) {
        return Math.abs(evaluate(x)) < 0.0001;
    }
}