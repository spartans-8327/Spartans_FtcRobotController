package org.firstinspires.ftc.teamcode.test.servo;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class PruebaServoConfig {



    public Servo servo = null;

    public static final double MIN_SERVO = 0;
    public static final double MID_SERVO = 0.5;
    public static final double MAX_SERVO = 1;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public PruebaServoConfig() {

    }

    public void init(HardwareMap ahwMap, Telemetry telemetry) {

        hwMap = ahwMap;

        servo = hwMap.get(Servo.class, "servo");
        servo.setPosition(MID_SERVO);
        telemetry.addLine("Servos configurados...");
        telemetry.update();

        telemetry.addData("Hardware", "Inicializado");
        telemetry.update();
    }


    public void reversa(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setDirection(DcMotor.Direction.REVERSE);
        }
    }

    public void derecho(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setDirection(DcMotor.Direction.FORWARD);
        }
    }

    public void usarWithoutEncoder(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void usarUsingEncoder(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void usarRunToPosition(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }


}

