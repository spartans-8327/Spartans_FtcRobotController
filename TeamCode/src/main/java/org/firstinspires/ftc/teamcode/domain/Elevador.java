package org.firstinspires.ftc.teamcode.domain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Elevador {
    DcMotor elevador;

    Servo servo;

    private int pulsosActual = 0;
    private final int pulsosAlto = -950; //89cm
    private final int pulsosMedio = -650; //63cm
    private final int pulsosBajo = -350;//38cmn
    private final int pulsosPiso = 0;


    public Elevador(DcMotor elevador , Servo servo){
        this.elevador = elevador;
        this.servo = servo;
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

    public void soltarAlto(double potencia){
        int pulsosNecesarios = pulsosAlto - pulsosActual;
        moverseDistanciaMantener(potencia, pulsosNecesarios);
        abrirGarra();

        pulsosActual = pulsosActual + pulsosNecesarios;
    }

    public void soltarMedio(double potencia){
        int pulsosNecesarios = pulsosMedio - pulsosActual;
        moverseDistanciaMantener(potencia, pulsosNecesarios);
        abrirGarra();
        pulsosActual = pulsosActual + pulsosNecesarios;
    }

    public void soltarBajo(double potencia){
        int pulsosNecesarios = pulsosBajo - pulsosActual;
        moverseDistanciaMantener(potencia, pulsosNecesarios);
        abrirGarra();
        pulsosActual = pulsosActual + pulsosNecesarios;
    }

    public void soltarPiso(double potencia){
        irPiso(potencia);
        abrirGarra();
    }


    private void irPiso(double potencia){
        int pulsosNecesarios = pulsosPiso - pulsosActual;
        moverseDistanciaMantener(potencia, pulsosNecesarios);
        abrirGarra();
        pulsosActual = pulsosActual + pulsosNecesarios;
    }



    private void abrirGarra(){
        servo.setPosition(0);
    }

    private void cerrarGarra(){
        servo.setPosition(1);
    }

        private void moverseDistancia(double potencia , int distance){
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        elevador.setTargetPosition(distance);

        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador.setPower(potencia);

        while(elevador.isBusy()){

        }

        elevador.setPower(0);

        elevador.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

        private void moverseDistanciaMantener(double potencia , int distance){
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        elevador.setTargetPosition(distance);

        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador.setPower(potencia);

        while(elevador.isBusy()){

        }

    }

}
