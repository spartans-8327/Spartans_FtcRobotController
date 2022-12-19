package org.firstinspires.ftc.teamcode.domain;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ElevadorSupremo {
    DcMotor elevador;
    DcMotor giroMotor;

    Servo servo;

    private LinearOpMode linearOpMode;

    private int pulsosElevActual = 0;
    private final int pulsosAlto = 1130; //90cm
    private final int pulsosMedio = 850; //59cm
    private final int pulsosBajo = 570;
    private final int pulsosMoverse = 160;
    private final int pulsosPiso = 0;

    private final int pulsos90 = 480;
 //Posicion de inicio (Puede cambiar)
    private int pulsosGiroActual;
    private double gradoAct = 0;


    public ElevadorSupremo(int gradoAct, DcMotor elevador , DcMotor giroMotor, Servo servo, LinearOpMode linearOpMode){
        this.elevador = elevador;
        this.giroMotor = giroMotor;
        this.servo = servo;
        this.linearOpMode = linearOpMode;
        this.gradoAct = gradoAct;
    }

    public void pruebaPulsos(double potencia, int pulsos){
        irMoverse(1);
        moverseDistanciaMantener_2(1, 480);
        linearOpMode.sleep(10000);
    }

    public void moverseGrados(double gradoMeta){

    }

    public void moverse90(double potencia){
        moverseDistanciaMantener_2(potencia, pulsos90);
    }

    public double encontrarErrorGrado(double posGradoMeta){
        return posGradoMeta - gradoAct;
    }

    public double encontrarErrorSeguro(double errorMinimo){
        double errorSeguro;
        //errorSeguro = (errorMinimo + gradoAct)? : ;
        return 9;
    }

    public double encontrarErrorMinimo(double error){
        double errorMinimo;
        errorMinimo = (error > 180)? error -= 360 :error;
        errorMinimo = (error < 180)? error += 360 :error;
        return errorMinimo;
    }

    public int pulsosAGrados(int pulsos){
        return pulsos*(90/480);
    }

    public int GradosAPulsos(int grados){
        return grados*(480/90);
    }

    public void irMoverse(double potencia){
        int pulsosNecesarios = pulsosMoverse - pulsosElevActual;
        if (pulsosNecesarios != 0){
            moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        }
        pulsosElevActual += pulsosNecesarios;
    }


    public void irPiso(double potencia){
        int pulsosNecesarios = pulsosPiso - pulsosElevActual;
        if (pulsosNecesarios != 0){
            moverseDistanciaMantener_1(potencia, pulsosNecesarios);
        }
        pulsosElevActual += pulsosNecesarios;
    }

    public void elevadorManual(double potencia,int pulsos,int tiempo){
        moverseDistanciaMantener_1(potencia, pulsos);
        linearOpMode.sleep(tiempo);
        pulsosElevActual += pulsos;
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