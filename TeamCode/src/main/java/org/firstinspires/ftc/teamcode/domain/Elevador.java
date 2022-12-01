package org.firstinspires.ftc.teamcode.domain;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Elevador {
    DcMotor elevador;
    DcMotor giroMotor;

    Servo servo;

    private LinearOpMode linearOpMode;

    private int pulsosActual = 0;
    private final int pulsosAlto = 1800; //90cm
    private final int pulsosMedio = 1200; //59cm
    private final int pulsosBajo = 660;
    private final int pulsosMoverse = 160;
    private final int pulsosPiso = 0;

    private final int pulsos90 = 480;

    private int posActual; //Posicion de inicio (Puede cambiar)
    private int pulsosGiroAct;


    public Elevador(DcMotor elevador , DcMotor giroMotor, Servo servo , int posActual, int posGiroAct, LinearOpMode linearOpMode){
        this.elevador = elevador;
        this.giroMotor = giroMotor;
        this.servo = servo;
        this.posActual = posActual;
        this.linearOpMode = linearOpMode;
    }

    public void pruebaPulsos(int potencia, int pulsos){
        irMoverse(1);
        moverseDistanciaMantener_2(1, 480);
        linearOpMode.sleep(10000);
    }

    public void girar_0(double potencia){
        irMoverse(1);
        int pulsosNecesarios = pulsos90*0 - pulsosGiroAct;
        moverseDistanciaMantener_2(potencia, pulsosNecesarios);
        pulsosActual = pulsosActual + pulsosNecesarios;
        irPiso(1);
    }

    public void girar_1(double potencia){
        irMoverse(1);
        int pulsosNecesarios = pulsos90*1 - pulsosGiroAct;
        moverseDistanciaMantener_2(potencia, pulsosNecesarios);
        pulsosActual = pulsosActual + pulsosNecesarios;
        irPiso(1);
    }

    public void girar_2(double potencia){
        irMoverse(1);
        int pulsosNecesarios = pulsos90*2 - pulsosGiroAct;
        moverseDistanciaMantener_2(potencia, pulsosNecesarios);
        pulsosActual = pulsosActual + pulsosNecesarios;
        irPiso(1);
    }

    public void girar_3(double potencia){
        irMoverse(1);
        int pulsosNecesarios = pulsos90*3 - pulsosGiroAct;
        moverseDistanciaMantener_2(potencia, pulsosNecesarios);
        pulsosActual = pulsosActual + pulsosNecesarios;
        irPiso(1);
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

    public void irMoverse(double potencia){
        int pulsosNecesarios = pulsosMoverse - pulsosActual;
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
        giroMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        giroMotor.setTargetPosition(distance);

        giroMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        giroMotor.setPower(potencia);

        while(giroMotor.isBusy()){

        }

        giroMotor.setPower(0);

        giroMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    private void moverseDistanciaMantener_2(double potencia , int distance){
        giroMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        giroMotor.setTargetPosition(distance);

        giroMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        giroMotor.setPower(potencia);

        while(giroMotor.isBusy()){

        }

    }





}