package org.firstinspires.ftc.teamcode.test.elevador.protomotor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


public class ElevadorProto {
    DcMotor elevador;

    private int pulsosActual = 0;
    private final int pulsosAlto = 1800; //90cm
    private final int pulsosMedio = 1200; //59cm
    private final int pulsosBajo = 660;
    private final int pulsosPiso = 0;

    private int posActual; //Posicion de inicio (Puede cambiar)
    private final int pulsos90 = 110;


    public ElevadorProto(DcMotor elevador  , int posActual){
        this.elevador = elevador;
        this.posActual = posActual;
    }

    public void subir(double potencia){
        elevador.setPower(potencia);
    }

    public void bajar(double potencia){
        elevador.setPower(-potencia);
    }

    private void moverseDistancia_elevador(double potencia , int distance){
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        elevador.setTargetPosition(distance);

        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador.setPower(potencia);

        while(elevador.isBusy()){

        }

        elevador.setPower(0);

        elevador.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    private void moverseDistanciaMantener_elevador(double potencia , int distance){
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        elevador.setTargetPosition(distance);

        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador.setPower(potencia);

        while(elevador.isBusy()){

        }

    }

    private void moverseDistancia_giro(double potencia , int distance){
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        elevador.setTargetPosition(distance);

        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador.setPower(potencia);

        while(elevador.isBusy()){

        }

        elevador.setPower(0);

        elevador.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    private void moverseDistanciaMantener_giro(double potencia , int distance){
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        elevador.setTargetPosition(distance);

        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador.setPower(potencia);

        while(elevador.isBusy()){

        }

    }

    public void girarGrados(int gradoMeta){
        int errorDefinitivo = 0;
        int error = encontrarError(gradoMeta);
        int errorMinimo = errorMinimo(encontrarError(gradoMeta));

        if (posActual + errorMinimo > 360 || posActual + errorMinimo < -360){
            errorDefinitivo = encontrarError(gradoMeta);
        }




        posActual += errorDefinitivo;
    }


    public int encontrarError(int gradoMeta){
        return gradoMeta - posActual;
    }

    public int errorMinimo(int error){
        int errorMinimo = error;

        errorMinimo = (error > 180)? error -= 360: error;
        errorMinimo = (error < -180)? error += 360: error;

        return  errorMinimo;
    }

    //comentario para commit XDDD

    public int arreglarError(int error) {
        int errorDefinitivo = 0;

        errorDefinitivo = (error + posActual > 360)? error -= 360: error;
        errorDefinitivo = (error + posActual < -360)? error += 360: error;

        return errorDefinitivo;
    }

    /*private int encontrarPulsosNecesarios(int errorDefinitivo){

    }*/

    public int getPosActual() {
        return posActual;
    }
}