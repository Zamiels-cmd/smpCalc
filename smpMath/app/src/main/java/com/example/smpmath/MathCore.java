package com.example.smpmath;

public class MathCore {
    public class multNode{
        Object left;
        Object right;

    }

    public class addNode{
        Object left;
        Object right;
    }

    public class powNode{
        Object left;
        Object right;
    }

    public class groupNode{
        Object inner;
    }

    public class varNod{
        varNod(){
        }
    }

    public class number{
        double real;
        double imag;

        public number(double real, double imag) {
            this.real = real;
            this.imag = imag;
        }

        public double getImag() {
            return imag;
        }

        public double getReal() {
            return real;
        }

        public void setReal(double real) {
            this.real = real;
        }

        public void setImag(double imag) {
            this.imag = imag;
        }

        @Override
        public String toString() {
            return String.valueOf(real)+" + I"+String.valueOf(imag);
        }
    }
}
