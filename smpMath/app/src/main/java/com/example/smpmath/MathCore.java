package com.example.smpmath;

import java.util.regex.Pattern;

public class MathCore {

    static public Boolean isInt(String value){
        Pattern pattern = Pattern.compile("\\d+");
        if (value == null) {
            return false;
        }
        return pattern.matcher(value).matches();
    }

    //below is parse code from creative commons. :)

    /***
     * The person who associated a work with this deed has dedicated the work to the public domain by waiving all of his or her rights to the work worldwide under copyright law, including all related and neighboring rights, to the extent allowed by law.
     *
     * You can copy, modify, distribute and perform the work, even for commercial purposes, all without asking permission.
     *
     * @param str
     * @return
     */

    public static ComplexNumber eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            ComplexNumber parse() {
                nextChar();
                ComplexNumber x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            ComplexNumber parseExpression() {
                ComplexNumber x = parseTerm();
                for (;;) {
                    if      (eat('+')) x=x.add(parseTerm()); // addition
                    else if (eat('-')) x=x.add(parseTerm().mult(-1)); // subtraction
                    else return x;
                }
            }

            ComplexNumber parseTerm() {
                ComplexNumber x = parseFactor();
                for (;;) {
                    if      (eat('*')) x =x.mult(parseFactor()); // multiplication
                    else if (eat('/')) x=x.mult(parseFactor().pow(-1));// division
                    else if (eat('%')) x=x.mod(parseFactor());
                    else return x;
                }
            }

            ComplexNumber parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return parseFactor().mult(-1); // unary minus

                ComplexNumber x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = new ComplexNumber(Double.parseDouble(str.substring(startPos, this.pos)));
                } else if (eat('i')) x = new ComplexNumber(0,1);
                    /*

                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = new ComplexNumber(parseFactor());
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);

                     */
                else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = x.pow(parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

}
