package org.firstinspires.ftc.teamcode.domain;

public class Posicion {

    Chasis chasis;
    private double posicionActualCuadros[];
    private double posicionActualPulsos[];

    private int pulsos_X = 0;
    private int pulsos_Y = 0;

    public Posicion(Chasis chasis, double posicionX , double posicionY){
        this.chasis = chasis;
        this.posicionActualCuadros[0] = posicionX;
        this.posicionActualCuadros[1] = posicionY;
    }

    public void moverseNcuadrosX(int numeroCuadrosX, double potencia){
        chasis.moverseX(potencia, pulsos_X * numeroCuadrosX);
        this.posicionActualCuadros[0] += numeroCuadrosX;
    }

    public void moverseNcuadrosY(int numeroCuadrosY, double potencia){
        chasis.moverseY(potencia, pulsos_X * numeroCuadrosY);
        this.posicionActualCuadros[1] += numeroCuadrosY;
    }


    private void moverse1CuadroX(double potencia){
        chasis.moverseX(potencia, pulsos_X);
    }
    private void moverse1CuadroY(double potencia){
        chasis.moverseY(potencia, pulsos_Y);
    }

    private double calcularErrorX(double metaX){
        double ErrorX = posicionActualCuadros[0] - metaX;
        return ErrorX;
    }

    private double calcularErrorY(double metaY){
        double ErrorY = posicionActualCuadros[1] - metaY;
        return  ErrorY;
    }

    public void actualizarPosicion(double posicionX , double posicionY){
        this.posicionActualCuadros[0] = posicionX;
        this.posicionActualCuadros[1] = posicionY;
    }

    }

