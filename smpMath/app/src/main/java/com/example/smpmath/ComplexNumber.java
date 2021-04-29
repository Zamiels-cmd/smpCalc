package com.example.smpmath;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ComplexNumber {
    double re;
    double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;

    }

    public ComplexNumber(double num) {
        this.re = num;
        this.im = 0;

    }

    public ComplexNumber(ComplexNumber num) {
        this.re = num.re;
        this.im = num.im;

    }

    public ComplexNumber round(int num){
        return new ComplexNumber(Math.round(this.re*num)/num,Math.round(this.im*num)/num);
    }

    public boolean equals(@Nullable ComplexNumber obj) {
        return (this.im==obj.im)&&(this.re==obj.re);
    }

    public boolean equals(@Nullable Double obj) {
        return (this.im==0)&&(this.re==obj);
    }

    public String toString() {
        super.toString();
        int DIGITS=1000000;//10^digits wanted
        return "("+this.re+" + i * "+this.im+")";
    }

    public ComplexNumber pow(double num){
        return new ComplexNumber(Math.cos(this.arg()*num),Math.sin(this.arg()*num)).mult(Math.pow(this.abs(),num));
    }

    private ComplexNumber ceil(){
        return new ComplexNumber(Math.ceil(re),Math.ceil(im));
    }

    public ComplexNumber mod(ComplexNumber num){
        return this.add(num.mult(this.mult(num.pow(-1).mult(-1)).ceil()));
    }

    public ComplexNumber pow(ComplexNumber num){
        ComplexNumber p=(new ComplexNumber(Math.log(this.abs()),this.arg())).mult(num);
        Double ex=Math.exp(p.re);
        ComplexNumber sign = new ComplexNumber(Math.cos(p.im),Math.sin(p.im));
        //Log.i("pow", ex+" "+sign +" "+sign.mult(ex));
        return sign.mult(ex);
    }

    public ComplexNumber mult(ComplexNumber num){
        return new ComplexNumber(this.re*num.re-this.im*num.im,this.re*num.im+this.im*num.re);
    }

    public ComplexNumber mult(double num){
        //Log.i("mult", this.re+" "+num+" "+new ComplexNumber(this.re*num,this.im*num));
        return new ComplexNumber(this.re*num,this.im*num);
    }

    public ComplexNumber add(ComplexNumber num){
        return new ComplexNumber(this.re+num.re,this.im+num.im);
    }

    public ComplexNumber add(double num){
        return new ComplexNumber(this.re+num,this.im);
    }

    public double abs(){
        return Math.sqrt(re*re+im*im);
    }

    public double arg(){
        //Log.i("arg", im+" "+re+" "+Math.atan2(im,re));
        return Math.atan2(im,re);
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }
}
