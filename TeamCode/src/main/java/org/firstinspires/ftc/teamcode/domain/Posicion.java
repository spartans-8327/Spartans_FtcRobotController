package org.firstinspires.ftc.teamcode.domain;

public class Posicion {

    Chasis chasis;
    private double posicionActual [];

    private int pulsos_X = 0;
    private int pulsos_Y = 0;

    public Posicion(Chasis chasis, double posicionX , double posicionY){
        this.chasis = chasis;
        this.posicionActual[0] = posicionX;
        this.posicionActual[1] = posicionY;
    }

    public void moverseCuadroPosicion(double metaX, double metaY, double potencia){
        
    }

    public void moverseNcuadrosX(int numeroCuadrosX, double potencia){
        chasis.moverseX(potencia, pulsos_X * numeroCuadrosX);
    }

    public void moverseNcuadrosY(int numeroCuadrosY, double potencia){
        chasis.moverseY(potencia, pulsos_X * numeroCuadrosY);
    }


    private void moverseCuadroX(double potencia){
        chasis.moverseX(potencia, pulsos_X);
    }
    private void moverseCuadroY(double potencia){
        chasis.moverseY(potencia, pulsos_Y);
    }

    private double calcularErrorX(double metaX){
        double ErrorX = posicionActual[0] - metaX;
        return ErrorX;
    }

    private double calcularErrorY(double metaY){
        double ErrorY = posicionActual[1] - metaY;
        return  ErrorY;
    }

    public void actualizarPosicion(double posicionX , double posicionY){
        this.posicionActual[0] = posicionX;
        this.posicionActual[1] = posicionY;
    }

    }

