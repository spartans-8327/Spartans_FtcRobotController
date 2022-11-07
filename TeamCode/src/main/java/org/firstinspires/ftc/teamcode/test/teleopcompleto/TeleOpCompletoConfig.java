package org.firstinspires.ftc.teamcode.test.teleopcompleto;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TeleOpCompletoConfig {


    public DcMotor enfrenteDer = null; //0 CH
    public DcMotor enfrenteIzq = null; //1 CH
    public DcMotor atrasDer = null; //2 CH
    public DcMotor atrasIzq = null; //3 CH

    public DcMotor hex_0 = null; //0 EH
    public DcMotor motor_1 = null; //1 EH

    public Servo servo = null;



    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public TeleOpCompletoConfig() {

    }

    public void init(HardwareMap ahwMap, Telemetry telemetry) {

        hwMap = ahwMap;

        enfrenteDer = hwMap.get(DcMotor.class, "enfrenteDer");
        enfrenteIzq = hwMap.get(DcMotor.class, "enfrenteIzq");
        atrasDer = hwMap.get(DcMotor.class, "atrasDer");
        atrasIzq = hwMap.get(DcMotor.class, "atrasIzq");

        hex_0 = hwMap.get(DcMotor.class, "hex_0");
        motor_1 = hwMap.get(DcMotor.class, "motor_1");

        servo = hwMap.get(Servo.class, "servo");



        telemetry.addLine("Motores inicializados...");


        reversa(atrasIzq , enfrenteIzq);
        derecho(atrasDer , enfrenteDer , hex_0);

        telemetry.addLine("Cambio de giro de motores hecho...");
        telemetry.update();

        enfrenteDer.setPower(0);
        enfrenteIzq.setPower(0);
        atrasDer.setPower(0);
        atrasIzq.setPower(0);
        hex_0.setPower(0);

        telemetry.addLine("Motores al 0%...");
        telemetry.update();

        usarUsingEncoder(enfrenteDer, enfrenteIzq, atrasDer, atrasIzq , hex_0);
        telemetry.addLine("Motores configurados...");
        telemetry.update();

        telemetry.addData("Hardware", "Inicializado");
        telemetry.update();

    }


    protected void reversa(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setDirection(DcMotor.Direction.REVERSE);
        }
    }

    protected void derecho(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setDirection(DcMotor.Direction.FORWARD);
        }
    }

    protected void usarWithoutEncoder(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    protected void usarUsingEncoder(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    protected void usarRunToPosition(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }

    public void moverseDistancia(DcMotor motor , double potencia , int distance){
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor.setTargetPosition(distance);

        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor.setPower(potencia);

        while(motor.isBusy()){

        }

        motor.setPower(0);

        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void moverseDistanciaMantener(DcMotor motor, double potencia , int distance){
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor.setTargetPosition(distance);

        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor.setPower(potencia);

        while(motor.isBusy()){

        }

    }


}

