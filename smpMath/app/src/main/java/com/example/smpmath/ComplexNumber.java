package com.example.smpmath;

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
        ComplexNumber r=this.round(10000);
        return String.valueOf(r.re)+" + i * "+String.valueOf(r.im);
    }

    public ComplexNumber pow(double num){
        return new ComplexNumber(Math.cos(this.arg()*num),Math.sin(this.arg()*num)).mult(Math.pow(this.abs(),num));
    }

    /*
    private ComplexNumber modPi(){
        return new ComplexNumber(this.re,(new ComplexNumber(Math.sin(this.im),Math.cos(this.im)).arg()));
    }

     */

    private ComplexNumber ceil(){
        return new ComplexNumber(Math.ceil(re),Math.ceil(im));
    }

    public ComplexNumber mod(ComplexNumber num){
        return this.add(num.mult((this.mult(num.pow(-1).mult(-1))).ceil()));
    }

    public ComplexNumber pow(ComplexNumber num){
        ComplexNumber p=new ComplexNumber(Math.log(this.abs()),this.arg()).mult(num);
        return new ComplexNumber(Math.cos(p.im),Math.sin(p.im)).mult(Math.exp(p.re));
    }

    public ComplexNumber mult(ComplexNumber num){
        return new ComplexNumber(re*num.re-im*num.im,re*num.im+im*num.re);
    }

    public ComplexNumber mult(double num){
        return new ComplexNumber(re*num,im*num);
    }

    public ComplexNumber add(ComplexNumber num){
        return new ComplexNumber(re+num.re,im+num.im);
    }

    public ComplexNumber add(double num){
        return new ComplexNumber(re+num,im);
    }

    public double abs(){
        return Math.sqrt(re*re+im*im);
    }

    public double arg(){
        return Math.atan2(im,re);
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }
}
