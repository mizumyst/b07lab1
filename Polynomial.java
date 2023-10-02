//lab 2

public class Polynomial {
    double[] coeffs = {0};
    int[] expos = {0};

    public Polynomial() {

    }

    public Polynomial(double[] c, int[] e) {
        coeffs = c;
        expos = e;
    }

    public Polynomial trim(Polynomial p) {
        int n = 0;
        for (int i=0; i<1000; i++) {
            if (p.expos[i]==-1) break;
            n++;
        }
        double[] new_coeffs = new double[n];
        int[] new_expos = new int[n];
        for (int i=0; i<n; i++) {
            new_coeffs[i] = p.coeffs[i];
            new_expos[i] = p.expos[i];
        }

        return new Polynomial(new_coeffs, new_expos);
    }

    public Polynomial add(Polynomial p) {
        Polynomial sumPoly = new Polynomial(new double[1000], new int[1000]);
        for (int i=0; i<1000; i++) {
            sumPoly.coeffs[i] = -1;
            sumPoly.expos[i] = -1;
        }
        for (int i=0; i<expos.length; i++) {
            sumPoly.coeffs[i] = coeffs[i];
            sumPoly.expos[i] = expos[i];
        }
        int i_offset = expos.length;
        boolean flag;
        int e;
        int e_i;
        for (int i=0; i<p.expos.length; i++) {
            e = p.expos[i];
            flag = false;
            e_i = 0;
            for (int j=0; j<1000; j++) {
                if (sumPoly.expos[i] == e) {
                    flag = true;
                    e_i = i;
                }
            }
            if (flag) {
                sumPoly.coeffs[e_i] += p.coeffs[i];
            } else {
                sumPoly.coeffs[i_offset] = p.coeffs[i];
                sumPoly.expos[i_offset++] = p.expos[i];
            }
        }
        return trim(sumPoly);
    }

    public Polynomial multiply(Polynomial p) {
        Polynomial productPoly = new Polynomial(new double[1000], new int[1000]);
        int counter = 0;
        double new_c;
        int new_e;
        boolean flag;
        int e_i;
        for (int i=0; i<expos.length; i++) {
            for (int j=0; j<p.expos.length; j++) {
                new_c = coeffs[i]*p.coeffs[j];
                new_e = expos[i]+p.expos[j];
                flag = false;
                e_i = 0;
                for (int k=0; k<1000; k++) {
                    if (productPoly.expos[i] == new_e) {
                        flag = true;
                        e_i = i;
                    }
                }
                if (flag) {
                    productPoly.coeffs[e_i] += new_c;
                } else {
                    productPoly.coeffs[counter] += new_c;
                    productPoly.expos[counter++] += new_e;
                }
                counter++;
            }
        }
        
        return trim(productPoly);
    }

    public double evaluate(double x) {
        double out = 0;
        for (int i=0; i<expos.length; i++) {
            out += coeffs[i] * Math.pow(x, expos[i]);
        }
        return out;
    }

    public boolean hasRoot(double x) {
        return Math.abs(evaluate(x)) < 0.0001;
    }
}