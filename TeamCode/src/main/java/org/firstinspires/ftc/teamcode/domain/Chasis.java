package org.firstinspires.ftc.teamcode.domain;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Chasis {

    private  DcMotor enfrenteDer;
    private DcMotor enfrenteIzq;
    private DcMotor atrasDer;
    private DcMotor atrasIzq;
    private BNO055IMU imu = null;



    public Chasis(DcMotor enfrenteDer , DcMotor enfrenteIzq , DcMotor atrasDer , DcMotor atrasIzq){
        this.enfrenteDer = enfrenteDer;
        this.enfrenteIzq = enfrenteIzq;
        this.atrasDer = atrasDer;
        this.atrasIzq = atrasIzq;
    }

    public Chasis(DcMotor enfrenteDer , DcMotor enfrenteIzq , DcMotor atrasDer , DcMotor atrasIzq , BNO055IMU imu){
        this.enfrenteDer = enfrenteDer;
        this.enfrenteIzq = enfrenteIzq;
        this.atrasDer = atrasDer;
        this.atrasIzq = atrasIzq;

        this.imu = imu;
    }

    public Chasis(DcMotor enfrenteDer , DcMotor enfrenteIzq , DcMotor atrasDer , DcMotor atrasIzq , double posicionX
                  ,double posicionY){
        this.enfrenteDer = enfrenteDer;
        this.enfrenteIzq = enfrenteIzq;
        this.atrasDer = atrasDer;
        this.atrasIzq = atrasIzq;

    }

    public double obtenerAngulo(){
        Orientation angulos;
        angulos = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double anguloChido = angulos.firstAngle + 180;
        return anguloChido;
    }



    public static double controlesVelocidad (Gamepad gamepad, double velocidad){
        velocidad = (gamepad.a)? 0 : velocidad;
        velocidad = (gamepad.x)? 0.3 : velocidad;
        velocidad = (gamepad.y)? 0.6 : velocidad;
        velocidad = (gamepad.b)? 1 : velocidad;

        return velocidad;
    }

    public void girarAngulo(int anguloObjetivo  , double potencia , Telemetry telemetry){
        double error = anguloObjetivo - this.obtenerAngulo();
        double tolerancia;

        switch ((int) (potencia*10)){
            case 1:
                tolerancia = 1;
                break;

            case 2:
                tolerancia = 4;
                break;

            case 3:
                tolerancia = 7;
                break;

            case 4:
                tolerancia = 4;
                break;

            case 5:
                tolerancia = 5;
                break;

            case 6:
                tolerancia = 6;
                break;

            case 7:
                tolerancia = 7;
                break;

            case 8:
                tolerancia = 8;
                break;

            case 9:
                tolerancia = 9;
                break;

            case 10:
                tolerancia = 10;
                break;


            default:
                throw new IllegalStateException("Unexpected value: " + (int) (potencia * 10));
        }

        if (error > 0){
            telemetry.update();

            while (this.obtenerAngulo() < anguloObjetivo -tolerancia || this.obtenerAngulo() > anguloObjetivo + tolerancia){
                girarIzquierda(potencia);
            }
        } else {
            telemetry.update();
            while (this.obtenerAngulo() < anguloObjetivo - tolerancia || this.obtenerAngulo() > anguloObjetivo + tolerancia){
                girarDerecha(potencia);
            }
        }
    }

    public void moverseEnfrente(double potencia , int pulsos){
        enfrenteDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        enfrenteDer.setTargetPosition(pulsos);
        enfrenteIzq.setTargetPosition(pulsos);
        atrasDer.setTargetPosition(pulsos);
        atrasIzq.setTargetPosition(pulsos);

        enfrenteDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        enfrenteIzq.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasIzq.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        moverseEnfrente(potencia);

        while(enfrenteDer.isBusy() && enfrenteIzq.isBusy() && atrasDer.isBusy() && atrasIzq.isBusy()){

        }

        parar();

        enfrenteDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void moverseY(double potencia, int pulsos){
        moverseEnfrente(potencia, pulsos);
    }

    public void moverseX(double potencia, int pulsos){
        moverseDerecha(potencia, pulsos);
    }

    public void moverseEnfrente(double potencia){
        enfrenteDer.setPower(potencia);
        enfrenteIzq.setPower(potencia);
        atrasDer.setPower(potencia);
        atrasIzq.setPower(potencia);
    }


    public void moverseAtras(double potencia){
        moverseEnfrente(-potencia);
    }

    public void moverseAtras(double potencia, int pulsos){
        enfrenteDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        enfrenteDer.setTargetPosition(-pulsos);
        enfrenteIzq.setTargetPosition(-pulsos);
        atrasDer.setTargetPosition(-pulsos);
        atrasIzq.setTargetPosition(-pulsos);

        enfrenteDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        enfrenteIzq.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasIzq.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        moverseEnfrente(potencia);

        while(enfrenteDer.isBusy() && enfrenteIzq.isBusy() && atrasDer.isBusy() &&
                atrasIzq.isBusy()){

        }

        parar();

        enfrenteDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }



    public void girarDerecha(double potencia){
        enfrenteDer.setPower(-potencia);
        enfrenteIzq.setPower(potencia);
        atrasDer.setPower(-potencia);
        atrasIzq.setPower(potencia);
    }

    public void girarDerecha(double potencia, int pulsos){
        enfrenteDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        enfrenteDer.setTargetPosition(-pulsos);
        enfrenteIzq.setTargetPosition(pulsos);
        atrasDer.setTargetPosition(-pulsos);
        atrasIzq.setTargetPosition(pulsos);

        enfrenteDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        enfrenteIzq.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasIzq.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        moverseEnfrente(potencia);

        while(enfrenteDer.isBusy() && enfrenteIzq.isBusy() && atrasDer.isBusy() &&
                atrasIzq.isBusy()){

        }

        parar();

        enfrenteDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }



    public void girarIzquierda(double potencia){
        girarDerecha(-potencia);
    }

    public void girarIzquierda(double potencia , int pulsos){
        enfrenteDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        enfrenteDer.setTargetPosition(pulsos);
        enfrenteIzq.setTargetPosition(-pulsos);
        atrasDer.setTargetPosition(pulsos);
        atrasIzq.setTargetPosition(-pulsos);

        enfrenteDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        enfrenteIzq.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasIzq.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        moverseDerecha(potencia);

        while(enfrenteDer.isBusy() && enfrenteIzq.isBusy() && atrasDer.isBusy() &&
                atrasIzq.isBusy()){

        }

        parar();

        enfrenteDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }



    public void moverseEnfIzq(double potencia){
        enfrenteDer.setPower(potencia);
        atrasIzq.setPower(potencia);
    }

    public void moverseEnfIzq(double potencia, int pulsos){
        enfrenteDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        enfrenteDer.setTargetPosition(pulsos);
        atrasDer.setTargetPosition(pulsos);

        enfrenteDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);;

        moverseEnfIzq(potencia);

        while(enfrenteDer.isBusy()  && atrasIzq.isBusy()){

        }

        parar();

        enfrenteDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void moverseEnfDer(double potencia){
        moverseEnfIzq(-potencia);
    }

    public void moverseEnfDer(double potencia, int pulsos){
        enfrenteIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        enfrenteIzq.setTargetPosition(pulsos);
        atrasDer.setTargetPosition(pulsos);

        enfrenteIzq.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);;

        moverseEnfIzq(potencia);

        while(enfrenteIzq.isBusy()  && atrasDer.isBusy()){

        }

        parar();

        enfrenteIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void moverseAtrIzq(double potencia){
        moverseEnfDer(-potencia);
    }

    public void moverseAtrIzq(double potencia, int pulsos){
        enfrenteIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        enfrenteIzq.setTargetPosition(-pulsos);
        atrasDer.setTargetPosition(-pulsos);

        enfrenteIzq.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);;

        moverseEnfIzq(potencia);

        while(enfrenteIzq.isBusy()  && atrasDer.isBusy()){

        }

        parar();

        enfrenteIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void moverseAtrDer(double potencia){
        moverseEnfIzq(potencia);
    }

    public void moverseAtrDer(double potencia , int pulsos){
        enfrenteDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        enfrenteDer.setTargetPosition(-pulsos);
        atrasDer.setTargetPosition(-pulsos);

        enfrenteDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);;

        moverseEnfIzq(potencia);

        while(enfrenteDer.isBusy()  && atrasIzq.isBusy()){

        }

        parar();

        enfrenteDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void moverseDerecha(double potencia){
        enfrenteDer.setPower(potencia);
        enfrenteIzq.setPower(-potencia);
        atrasDer.setPower(-potencia);
        atrasIzq.setPower(potencia);
    }

    public void moverseIzquierda(double potencia){
        moverseDerecha(-potencia);
    }

    public void moverseIzquierda(double potencia , int pulsos){
        enfrenteDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        enfrenteDer.setTargetPosition(pulsos);
        enfrenteIzq.setTargetPosition(-pulsos);
        atrasDer.setTargetPosition(-pulsos);
        atrasIzq.setTargetPosition(pulsos);

        enfrenteDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        enfrenteIzq.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasIzq.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        moverseEnfrente(potencia);

        while(enfrenteDer.isBusy() && enfrenteIzq.isBusy() && atrasDer.isBusy() &&
                atrasIzq.isBusy()){

        }

        parar();

        enfrenteDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void moverseDerecha(double potencia , int pulsos){
        enfrenteDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        enfrenteDer.setTargetPosition(-pulsos);
        enfrenteIzq.setTargetPosition(pulsos);
        atrasDer.setTargetPosition(pulsos);
        atrasIzq.setTargetPosition(-pulsos);

        enfrenteDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        enfrenteIzq.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasDer.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasIzq.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        moverseEnfrente(potencia);

        while(enfrenteDer.isBusy() && enfrenteIzq.isBusy() && atrasDer.isBusy() &&
                atrasIzq.isBusy()){

        }

        parar();

        enfrenteDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void parar(){
        moverseEnfrente(0);
    }

} 
