package org.firstinspires.ftc.teamcode.test.chasis.autonomo;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ChasisSimpleConfig_1 {


    public DcMotor enfrenteDer = null; //0
    public DcMotor enfrenteIzq = null; //1
    public DcMotor atrasDer = null; //2
    public DcMotor atrasIzq = null; //3

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public ChasisSimpleConfig_1() {

    }

    public void init(HardwareMap ahwMap, Telemetry telemetry) {

        hwMap = ahwMap;

        enfrenteDer = hwMap.get(DcMotor.class, "enfrenteDer");
        enfrenteIzq = hwMap.get(DcMotor.class, "enfrenteIzq");
        atrasDer = hwMap.get(DcMotor.class, "atrasDer");
        atrasIzq = hwMap.get(DcMotor.class, "atrasIzq");
        telemetry.addLine("Motores inicializados...");


        reversa(atrasIzq , enfrenteIzq);
        derecho(atrasDer , enfrenteDer);

        telemetry.addLine("Cambio de giro de motores hecho...");
        telemetry.update();

        enfrenteDer.setPower(0);
        enfrenteIzq.setPower(0);
        atrasDer.setPower(0);
        atrasIzq.setPower(0);
        telemetry.addLine("Motores al 0%...");
        telemetry.update();

        usarUsingEncoder(enfrenteDer, enfrenteIzq, atrasDer, atrasIzq);
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

