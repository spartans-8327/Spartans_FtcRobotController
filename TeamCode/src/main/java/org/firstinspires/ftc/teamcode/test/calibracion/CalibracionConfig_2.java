package org.firstinspires.ftc.teamcode.test.calibracion;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CalibracionConfig_2 {


    public DcMotor motor = null;
    public DcMotor motor_2 = null;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public CalibracionConfig_2() {

    }

    public void init(HardwareMap ahwMap, Telemetry telemetry) {

        hwMap = ahwMap;

        motor = hwMap.get(DcMotor.class, "motor");
        motor_2 = hwMap.get(DcMotor.class, "motor_2");

        telemetry.addLine("Motores inicializados...");

        derecho(motor);

        telemetry.addLine("Cambio de giro de motores hecho...");
        telemetry.update();

        motor.setPower(0);
        telemetry.addLine("Motores al 0%...");
        telemetry.update();

        usarUsingEncoder(motor);
        telemetry.addLine("Motores configurados...");
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

