package org.firstinspires.ftc.teamcode.domain;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ElevadorAutonomo {
    public DcMotor elevador;
    public DcMotor giroMotor;

    Servo servo;

    private LinearOpMode linearOpMode;

    public int pulsosActual = 0;

    private final int PULSOSALTO = 4150;
    private final int PULSOSMEDIO = 2600;
    private final int PULSOSBAJO = 1800;
    private final int PULSOSMOVERSECONO = 700;
    private final int PULSOSMOVERSE = 400;
    private final int PULSOSPISO = 0;

    private final int PULSOSCONO5 = 500;
    private final int PULSOSCONO4 = 400;

    private final int PULSOS90 = 490;

 //Posicion de inicio (Puede cambiar)
    public int pulsosGiroAct;

    private boolean garraCerrada = false;


    public ElevadorAutonomo(DcMotor elevador , DcMotor giroMotor, Servo servo, LinearOpMode linearOpMode, int posGiroAct){
        this.elevador = elevador;
        this.giroMotor = giroMotor;
        this.servo = servo;
        this.linearOpMode = linearOpMode;
        this.pulsosGiroAct = posGiroAct * PULSOS90;
    }

    public ElevadorAutonomo(DcMotor elevador , DcMotor giroMotor, Servo servo, LinearOpMode linearOpMode){
        this.elevador = elevador;
        this.giroMotor = giroMotor;
        this.servo = servo;
        this.linearOpMode = linearOpMode;
    }

    public void girar_0(double potencia){
        girar90Grados(0, potencia);
    }

    public void girar_1(double potencia){
        girar90Grados(1, potencia);
    }

    public void girar_2(double potencia){
        girar90Grados(2, potencia);
    }

    public void girar_3(double potencia){
        girar90Grados(3, potencia);
    }

    public void irAlto(double potencia){
        int pulsosNecesarios = PULSOSALTO - pulsosActual;
        if (pulsosNecesarios != 0 && pulsosNecesarios > 0){
            moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        } else if (pulsosNecesarios < 0){
            moverseDistanciaMantener_1(0.3, pulsosNecesarios);
        }
        pulsosActual += pulsosNecesarios;

        this.elevador.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elevador.setPower(0.6);
        linearOpMode.sleep(500);
        moverseDistanciaMantener_1(1, 0);
    }

    public void mantener(){
        elevador.setTargetPosition(elevador.getCurrentPosition());
        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevador.setPower(0.5);
    }

    public void irMedio(double potencia){
        elevarPulsos(PULSOSMEDIO, potencia);
        mantener();
    }


    public void irBajo(double potencia){
        elevarPulsos(PULSOSBAJO, potencia);
        mantener();
    }

    public void irMoverseCono(double potencia){
        elevarPulsos(PULSOSMOVERSECONO, potencia);
        mantener();
    }

    public void irCono5(double potencia){
        elevarPulsos(PULSOSCONO5, potencia);
        mantener();
    }

    public void irCono4(double potencia){
        elevarPulsos(PULSOSCONO4, potencia);
        mantener();
    }

    public void irPiso(double potencia){
        elevarPulsos(PULSOSPISO, potencia);
        mantener();
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



    public void cerrarGarra(){
        servo.setPosition(0);
        garraCerrada = false;
        linearOpMode.sleep(1000);
    }

    public void abrirGarra(){
        servo.setPosition(1);
        garraCerrada = true;
        linearOpMode.sleep(1000);
    }

    public void elevarPulsos(final int PULSOS, double potencia){
        int pulsosNecesarios = PULSOS - pulsosActual;
        if (pulsosNecesarios != 0 && pulsosNecesarios > 0){
            moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        } else if (pulsosNecesarios < 0){
            moverseDistanciaMantener_1(0.3, pulsosNecesarios);
        }
        pulsosActual += pulsosNecesarios;
    }

    public int girar90Grados(int veces, double potencia){
        boolean estabaEnPsocioninicial = (pulsosActual == PULSOSPISO)? true: false;
        int pulsosNecesarios = PULSOS90*veces - pulsosGiroAct;
        if (pulsosNecesarios != 0) {
            if (estabaEnPsocioninicial)
                irMoverseCono(0.8);
            moverseDistanciaMantener_2(potencia, pulsosNecesarios);
            pulsosGiroAct += pulsosNecesarios;
        }
        return pulsosNecesarios;
    }


    private void moverseDistancia_1(double potencia , int distance){
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        elevador.setTargetPosition(distance);

        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador.setPower(potencia);

        elevador.setPower(0);

        elevador.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



    }

    public void moverseDistanciaMantener_1(double potencia , int distance){

        elevador.setTargetPosition(distance);

        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador.setPower(potencia);

        while(elevador.isBusy()){

        }

        elevador.setTargetPosition(elevador.getCurrentPosition());

        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador.setPower(1);

        while(elevador.isBusy()){

        }

    }

    private void moverseDistanciaMantener_2(double potencia , int distance){
        giroMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        giroMotor.setTargetPosition(distance);

        giroMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        giroMotor.setPower(potencia);

        while(giroMotor.isBusy()){

        }

        elevador.setTargetPosition(giroMotor.getCurrentPosition());

        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador.setPower(1);

        while(giroMotor.isBusy()){

        }
    }





}