package org.firstinspires.ftc.teamcode.domain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Elevador {
    DcMotor elevador;

    Servo servo;

    private int pulsosActual = 0;
    private final int pulsosAlto = 1800; //90cm
    private final int pulsosMedio = 1200; //59cm
    private final int pulsosBajo = 660;
    private final int pulsosPiso = 0;

    private int posActual; //Posicion de inicio (Puede cambiar)
    private final int pulsos90 = 1;


    public Elevador(DcMotor elevador , Servo servo , int posActual){
        this.elevador = elevador;
        this.servo = servo;
        this.posActual = posActual;
    }

    public void subir(double potencia){
        elevador.setPower(potencia);
    }

    public void bajar(double potencia){
        elevador.setPower(-potencia);
    }

    public void tomarCono(double potencia){
        abrirGarra();
        irPiso(potencia);
        cerrarGarra();
    }

    public void irAlto(double potencia){
        int pulsosNecesarios = pulsosAlto - pulsosActual;
        moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        pulsosActual = pulsosActual + pulsosNecesarios;
    }

    public void irMedio(double potencia){
        int pulsosNecesarios = pulsosMedio - pulsosActual;
        moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        pulsosActual = pulsosActual + pulsosNecesarios;
    }

    public void irBajo(double potencia){
        int pulsosNecesarios = pulsosBajo - pulsosActual;
        moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        pulsosActual = pulsosActual + pulsosNecesarios;
    }


    public void irPiso(double potencia){
        int pulsosNecesarios = pulsosPiso - pulsosActual;
        moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        abrirGarra();
        pulsosActual = pulsosActual + pulsosNecesarios;
    }

    public void soltarAlto(double potencia){
        int pulsosNecesarios = pulsosAlto - pulsosActual;
        moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        pulsosActual = pulsosActual + pulsosNecesarios;
    }

    public void soltarMedio(double potencia){
        int pulsosNecesarios = pulsosMedio - pulsosActual;
        moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        abrirGarra();
        pulsosActual = pulsosActual + pulsosNecesarios;
    }

    public void soltarBajo(double potencia){
        int pulsosNecesarios = pulsosBajo - pulsosActual;
        moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        abrirGarra();
        pulsosActual = pulsosActual + pulsosNecesarios;
    }

    public void soltarPiso(double potencia){
        irPiso(potencia);
        abrirGarra();
    }



    public void abrirGarra(){
        servo.setPosition(0);
    }

    public void cerrarGarra(){
        servo.setPosition(1);
    }

    private void moverseDistancia_1(double potencia , int distance){
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        elevador.setTargetPosition(distance);

        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador.setPower(potencia);

        while(elevador.isBusy()){

        }

        elevador.setPower(0);

        elevador.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    private void moverseDistanciaMantener_1(double potencia , int distance){
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        elevador.setTargetPosition(distance);

        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador.setPower(potencia);

        while(elevador.isBusy()){

        }

    }

    private void moverseDistancia_2(double potencia , int distance){
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        elevador.setTargetPosition(distance);

        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador.setPower(potencia);

        while(elevador.isBusy()){

        }

        elevador.setPower(0);

        elevador.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    private void moverseDistanciaMantener_2(double potencia , int distance){
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        elevador.setTargetPosition(distance);

        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador.setPower(potencia);

        while(elevador.isBusy()){

        }

    }

    public void girarGrados(int gradoMeta){
        int errorNormal = encontrarError(gradoMeta);
        int errorMinimo = errorMinimo(errorNormal);
        int giroCalculado = posActual + errorMinimo;
        int errorReal;

        if (giroCalculado > 360 || giroCalculado < -360){
         errorReal = encontrarError(gradoMeta);
        }

        errorReal = (giroCalculado > 360 || giroCalculado < -360)? errorMinimo: errorNormal;
    }

    private int encontrarError(int gradoMeta){
        return gradoMeta - posActual;
    }

    public int errorMinimo(int error){
        int errorMinimo = 0;

        errorMinimo = (error > 180)? error -= errorMinimo: error;
        errorMinimo = (error < 180)? error += errorMinimo: error;

        return  errorMinimo;
    }

}