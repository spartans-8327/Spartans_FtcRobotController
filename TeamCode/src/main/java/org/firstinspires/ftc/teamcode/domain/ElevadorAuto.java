package org.firstinspires.ftc.teamcode.domain;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ElevadorAuto {
    DcMotor elevador;
    DcMotor giroMotor;

    Servo servo;

    private LinearOpMode linearOpMode;

    private int pulsosActual = 0;

    private final int PULSOSALTO = 1230;
    private final int PULSOSMEDIO = 900;
    private final int PULSOSBAJO = 620;
    private final int PULSOSMOVERSECONO = 500;
    private final int PULSOSMOVERSE = 400;
    private final int PULSOSPISO = 0;

    private final int PULSOSCONO5 = 270;
    private final int PULSOSCONO4 = 230;
    private final int PULSOSCONO3 = 190;

    private final int DELAY = 200;


    private final int pulsos20pulgadas = 0;

    private final int PULSOS90 = 490;
 //Posicion de inicio (Puede cambiar)
    private int pulsosGiroAct;

    private boolean garraCerrada = false;


    public ElevadorAuto(DcMotor elevador , DcMotor giroMotor, Servo servo, LinearOpMode linearOpMode, int posGiroAct){
        this.elevador = elevador;
        this.giroMotor = giroMotor;
        this.servo = servo;
        this.linearOpMode = linearOpMode;
        this.pulsosGiroAct = posGiroAct*PULSOS90;
    }

    public ElevadorAuto(DcMotor elevador , DcMotor giroMotor, Servo servo, LinearOpMode linearOpMode){
        this.elevador = elevador;
        this.giroMotor = giroMotor;
        this.servo = servo;
        this.linearOpMode = linearOpMode;
    }

    public void girar_0(double potencia){
        girar90Grados(0, potencia);
        linearOpMode.sleep(DELAY);
    }

    public void girar_1(double potencia){
        girar90Grados(1, potencia);
        linearOpMode.sleep(DELAY);
    }

    public void girar_2(double potencia){
        girar90Grados(2, potencia);
        linearOpMode.sleep(DELAY);
    }

    public void girar_3(double potencia){
        girar90Grados(3, potencia);
        linearOpMode.sleep(DELAY);
    }

    public void irAlto(double potencia){
        elevarPulsos(PULSOSALTO, potencia);
        linearOpMode.sleep(DELAY);
    }

    public void irMedio(double potencia){
        elevarPulsos(PULSOSMEDIO, potencia);
        linearOpMode.sleep(DELAY);
    }


    public void irBajo(double potencia){
        elevarPulsos(PULSOSBAJO, potencia);
        linearOpMode.sleep(DELAY);
    }

    public void irMoverse(double potencia){
        elevarPulsos(PULSOSMOVERSE, potencia);
        linearOpMode.sleep(DELAY);
    }

    public void irMoverseCono(double potencia){
        elevarPulsos(PULSOSMOVERSECONO, potencia);
        linearOpMode.sleep(DELAY);
    }

    public void irCono5(double potencia){
        elevarPulsos(PULSOSCONO5, potencia);
        linearOpMode.sleep(DELAY);
    }

    public void irCono4(double potencia){
        elevarPulsos(PULSOSCONO4, potencia);
        linearOpMode.sleep(DELAY);
    }

    public void irCono3(double potencia){
        elevarPulsos(PULSOSCONO3, potencia);
        linearOpMode.sleep(DELAY);
    }

    public void irPiso(double potencia){
        elevarPulsos(PULSOSPISO, potencia);
        linearOpMode.sleep(DELAY);
    }

    public void elevadorManual(double potencia, int pulsos){
        moverseDistanciaMantener_1(potencia, pulsos);
        linearOpMode.sleep(500);
        pulsosActual += pulsos;
    }

    public void girarManual(double potencia, int pulsos){
        moverseDistanciaMantener_2(potencia, pulsos);
        linearOpMode.sleep(500);
        pulsosGiroAct += pulsos;
    }



    public void abrirGarra(){
        servo.setPosition(0);
        garraCerrada = false;
        linearOpMode.sleep(DELAY);

    }

    public void cerrarGarra(){
        servo.setPosition(1);
        garraCerrada = true;
        linearOpMode.sleep(DELAY);
    }

    public void elevarPulsos(final int PULSOS, double potencia){
        int pulsosNecesarios = PULSOS - pulsosActual;
        if (pulsosNecesarios != 0){
            moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        }
        pulsosActual += pulsosNecesarios;
    }

    public void girar90Grados(int veces, double potencia){
        boolean estabaEnPsocioninicial = (pulsosActual == PULSOSPISO)? true: false;
        int pulsosNecesarios = PULSOS90*veces - pulsosGiroAct;
        if (pulsosNecesarios != 0) {
            if (estabaEnPsocioninicial)
                irMoverseCono(0.8);
            moverseDistanciaMantener_2(potencia, pulsosNecesarios);
            pulsosGiroAct += pulsosNecesarios;
    }
    }


    private void moverseDistancia_1(double potencia , int distance){
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        elevador.setTargetPosition(distance);

        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador.setPower(potencia);

        elevador.setPower(0);

        elevador.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    private void moverseDistanciaMantener_1(double potencia , int distance){
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        elevador.setTargetPosition(distance);

        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador.setPower(potencia);

    }

    private void moverseDistancia_2(double potencia , int distance){
        giroMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        giroMotor.setTargetPosition(distance);

        giroMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        giroMotor.setPower(potencia);

        giroMotor.setPower(0);

        giroMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    private void moverseDistanciaMantener_2(double potencia , int distance){
        giroMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        giroMotor.setTargetPosition(distance);

        giroMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        giroMotor.setPower(potencia);

    }





}