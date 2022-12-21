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

    private final int PULSOSALTO = 1170;
    private final int PULSOSMEDIO = 870;
    private final int PULSOSBAJO = 550;
    private final int PULSOSMOVERSECONO = 500;
    private final int PULSOSMOVERSE = 400;
    private final int PULSOSPISO = 0;

    private final int pulsos20pulgadas = 0;

    private final int PULSOS90 = 490;
 //Posicion de inicio (Puede cambiar)
    private int pulsosGiroAct;

    private boolean garraCerrada = false;


    public Elevador(DcMotor elevador , DcMotor giroMotor, Servo servo, LinearOpMode linearOpMode, int posGiroAct){
        this.elevador = elevador;
        this.giroMotor = giroMotor;
        this.servo = servo;
        this.linearOpMode = linearOpMode;
        this.pulsosGiroAct = posGiroAct*PULSOS90;
    }

    public Elevador(DcMotor elevador , DcMotor giroMotor, Servo servo, LinearOpMode linearOpMode){
        this.elevador = elevador;
        this.giroMotor = giroMotor;
        this.servo = servo;
        this.linearOpMode = linearOpMode;
    }

    public void pruebaPulsos(int potencia, int pulsos){
        irMoverse(1);
        moverseDistanciaMantener_2(1, 480);
        linearOpMode.sleep(10000);
    }

    public void girarManual(double potencia,int pulsos, int delay){
        moverseDistanciaMantener_2(potencia, pulsos);
        pulsosGiroAct += pulsos;
        linearOpMode.sleep(delay);
        linearOpMode.telemetry.addLine("Ejecutando Girar Manual");
    }

    public void girar_0(double potencia){
        boolean estabaEnPsocioninicial = (pulsosActual == PULSOSPISO)? true: false;
        int pulsosNecesarios = PULSOS90*0 - pulsosGiroAct;
        if (pulsosNecesarios != 0) {
            if (estabaEnPsocioninicial)
                irMoverseCono(0.8);
            moverseDistanciaMantener_2(potencia, pulsosNecesarios);
            pulsosGiroAct += pulsosNecesarios;
        }
    }

    public void girar_1(double potencia){
        boolean estabaEnPsocioninicial = (pulsosActual == PULSOSPISO)? true: false;
        int pulsosNecesarios = PULSOS90*1 - pulsosGiroAct;
        if (pulsosNecesarios != 0) {
            if (estabaEnPsocioninicial)
                irMoverseCono(0.8);
            moverseDistanciaMantener_2(potencia, pulsosNecesarios);
            pulsosGiroAct += pulsosNecesarios;
        }
    }

    public void girar_2(double potencia){
        boolean estabaEnPsocioninicial = (pulsosActual == PULSOSPISO)? true: false;
        int pulsosNecesarios = PULSOS90*2 - pulsosGiroAct;
        if (pulsosNecesarios != 0) {
            if (estabaEnPsocioninicial)
                irMoverseCono(0.8);
            moverseDistanciaMantener_2(potencia, pulsosNecesarios);
            pulsosGiroAct += pulsosNecesarios;
        }
    }

    public void girar_3(double potencia){
        boolean estabaEnPsocioninicial = (pulsosActual == PULSOSPISO)? true: false;
        int pulsosNecesarios = PULSOS90*3 - pulsosGiroAct;
        if (pulsosNecesarios != 0) {
            if (estabaEnPsocioninicial)
                irMoverseCono(0.8);
            moverseDistanciaMantener_2(potencia, pulsosNecesarios);
            pulsosGiroAct += pulsosNecesarios;
        }
    }

    public void elevarManual(double potencia,int pulsos, int delay){
        moverseDistanciaMantener_1(potencia, pulsos);
        pulsosActual += pulsos;
        linearOpMode.sleep(delay);
        linearOpMode.telemetry.addLine("Ejecutando elevar Manual");
    }

    public void irAlto(double potencia){
        int pulsosNecesarios = PULSOSALTO - pulsosActual;
        if (pulsosNecesarios != 0){
            moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        }
        pulsosActual += pulsosNecesarios;
    }

    public void irMedio(double potencia){
        int pulsosNecesarios = PULSOSMEDIO - pulsosActual;
        if (pulsosNecesarios != 0){
            moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        }
        pulsosActual += pulsosNecesarios;
    }


    public void irBajo(double potencia){
        int pulsosNecesarios = PULSOSBAJO - pulsosActual;
        if (pulsosNecesarios != 0){
            moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        }
        pulsosActual += pulsosNecesarios;
    }


    public void irMoverse(double potencia){
        int pulsosNecesarios = PULSOSMOVERSE - pulsosActual;
        if (pulsosNecesarios != 0){
            moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        }
        pulsosActual += pulsosNecesarios;
    }

    public void irMoverseCono(double potencia){
        int pulsosNecesarios = PULSOSMOVERSECONO - pulsosActual;
        if (pulsosNecesarios != 0){
            moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        }
        pulsosActual += pulsosNecesarios;
    }


    public void irPiso(double potencia){
        int pulsosNecesarios = PULSOSPISO - pulsosActual;
        if (pulsosNecesarios != 0){
            moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        }
        pulsosActual += pulsosNecesarios;
    }

    public void elevadorManual(double potencia,int pulsos,int tiempo){
        moverseDistanciaMantener_1(potencia, pulsos);
        linearOpMode.sleep(tiempo);
        pulsosActual += pulsos;
    }



    public void abrirGarra(){
        servo.setPosition(0);
        garraCerrada = false;

    }

    public void cerrarGarra(){
        servo.setPosition(1);
        garraCerrada = true;
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