package org.firstinspires.ftc.teamcode.domain;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Chasis {

    public DcMotor enfrenteDer;
    private DcMotor enfrenteIzq;
    private DcMotor atrasDer;
    private DcMotor atrasIzq;
    private LinearOpMode linearOpMode;
    private BNO055IMU imu = null;
    private static final int cuadroY = 980;
    private static final int cuadroX = 1200;
    private static final int cuadroG = 930;

    private DcMotor motores[];

    public Chasis(DcMotor[] motores, LinearOpMode linearOpMode, BNO055IMU imu){
        this.linearOpMode = linearOpMode;
        this.motores = motores;
        this.imu = imu;
    }

    public Chasis(DcMotor[] motores, BNO055IMU imu, LinearOpMode linearOpMode){
        this.enfrenteDer = enfrenteDer;
        this.enfrenteIzq = enfrenteIzq;
        this.atrasDer = atrasDer;
        this.atrasIzq = atrasIzq;
    }



    public void init(){
        this.enfrenteDer = motores[0];
        this.enfrenteIzq = motores[1];
        this.atrasDer = motores[2];
        this.atrasIzq = motores[3];
    }

    public void reversa(DcMotor[] motores) {
        for (int i = 0; i < motores.length; i++){
            motores[i].setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

    public void derecho(DcMotor[] motores) {
        for (int i = 0; i < motores.length; i++){
            motores[i].setDirection(DcMotorSimple.Direction.FORWARD);
        }
    }

    public void usarWithoutEncoder(DcMotor[] motores) {
        for (int i = 0; i < motores.length; i++){
            motores[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void usarUsingEncoder(DcMotor[] motores) {
        for (int i = 0; i < motores.length; i++){
            motores[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void usarRunToPosition(DcMotor[] motores) {
        for (int i = 0; i < 3; i++){
            motores[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }

    public void usarStopAndResetEncoders(DcMotor[] motores) {
        for (int i = 0; i < 3; i++){
            motores[i].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

    public void setTargetPositionV(DcMotor[] motores, int target) {
        for (int i = 0; i < motores.length; i++){
            motores[i].setTargetPosition(target);
        }
    }



    public static double controlesVelocidad (Gamepad gamepad, double velocidad){
        velocidad = (gamepad.a)? 0 : velocidad;
        velocidad = (gamepad.x)? 0.3 : velocidad;
        velocidad = (gamepad.y)? 0.6 : velocidad;
        velocidad = (gamepad.b)? 1 : velocidad;

        return velocidad;
    }


    public void irEnfrente(int sp, double kp){
        usarStopAndResetEncoders(motores);
        usarWithoutEncoder(motores);
        int pv = 0;
        int i = 0;

        while(sp != pv && linearOpMode.opModeIsActive()){
            pv = motores[i].getCurrentPosition();
            int error = sp - pv;
            double power = kp * error;
            moverseEnfrente(power);
            i = (i < 4)? i++ : 0;
        }
            usarRunToPosition(motores);
            usarStopAndResetEncoders(motores);
            setTargetPositionV(motores, 0);
            moverseEnfrente(0.1);
    }

    public void irAtras(int sp, double kp){
        usarStopAndResetEncoders(motores);
        usarWithoutEncoder(motores);
        sp = -sp;
        int pv = 0;
        int i = 0;

        while(sp != pv && linearOpMode.opModeIsActive()){
            pv = motores[i].getCurrentPosition();
            int error = sp - pv;
            double power = kp * error;
            moverseEnfrente(power);
            i = (i < 4)? i++ : 0;
        }
        usarRunToPosition(motores);
        usarStopAndResetEncoders(motores);
        setTargetPositionV(motores, 0);
        moverseAtras(0.1);
    }

    public void irIzquierda(int sp, double kp){
        usarStopAndResetEncoders(motores);
        usarWithoutEncoder(motores);
        int pv = 0;
        int i = 0;

        while(sp != pv && linearOpMode.opModeIsActive()){
            pv = motores[0].getCurrentPosition();
            int error = sp - pv;
            double power = kp * error;
            moverseDerecha(power);
        }
        usarRunToPosition(motores);
        usarStopAndResetEncoders(motores);
        setTargetPositionV(motores, 0);
        moverseEnfrente(0.1);
    }

    public void irDerecha(int sp, double kp){
        usarStopAndResetEncoders(motores);
        usarWithoutEncoder(motores);
        int pv = 0;

        while(sp != pv && linearOpMode.opModeIsActive()){
            pv = motores[1].getCurrentPosition();
            int error = sp - pv;
            double power = kp * error;
            moverseIzquierda(power);
        }
        usarRunToPosition(motores);
        usarStopAndResetEncoders(motores);
        setTargetPositionV(motores, 0);
        moverseEnfrente(0.1);
    }

    public void moverseEnfrente(double potencia , int pulsos){
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

        while(enfrenteDer.isBusy() && enfrenteIzq.isBusy() && atrasDer.isBusy() && atrasIzq.isBusy()){

        }

        parar();

        enfrenteDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void girarC(double potencia, double veces){
        int error = (int)(veces * cuadroG);
        girarDerecha(potencia, error);
    }
    public void girarC(double veces){
        int error = (int)(veces * cuadroG);
        girarDerecha(0.8, error);
    }

    public void moverseY(double potencia, double veces){
        int error = (int)(veces * cuadroY);
        moverseEnfrente(potencia, error);
    }

    public void moverseX(double potencia, double veces){
        int error = (int)(veces * cuadroX);
        moverseDerecha(potencia, error);
    }

    public void moverseY(double veces){
        int error = (int)(veces * cuadroY);
        moverseEnfrente(0.3, error);
    }

    public void moverseX( double veces){
        int error = (int)(veces * cuadroX);
        moverseDerecha(1, error);
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


    public void moverseDerecha(double potencia){
        enfrenteDer.setPower(-potencia);
        enfrenteIzq.setPower(potencia);
        atrasDer.setPower(potencia);
        atrasIzq.setPower(-potencia);
    }

    public void moverseIzquierda(double potencia){
        moverseDerecha(-potencia);
    }

    public void moverseIzquierda(double potencia , int pulsos){
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


    public void moverseDerecha(double potencia , int pulsos){
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


    public void parar(){
        moverseEnfrente(0);
    }

    public double obtenerAngulo(){
        Orientation angulos;
        angulos = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double primerAngulo = angulos.firstAngle;
        primerAngulo += 180;
        return primerAngulo;
    }

} 
