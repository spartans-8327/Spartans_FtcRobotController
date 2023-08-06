package org.firstinspires.ftc.teamcode.odometria;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class OdometriaCahsisAutonomo2 {

    public DcMotor enfrenteDer, enfrenteIzq, atrasDer, atrasIzq;

    public DcMotor[] motores = {enfrenteDer, enfrenteIzq, atrasDer, atrasIzq};

    HardwareMap hwMap = null;

    public double coutnsPerRevolution = 1440;
    public double wheelDiameter = 10.16;
    public double robotWidht = 24.10;
    public double countersPerCm;

    public double robotX = 0;
    public double robotY = 0;
    public double robotHeading = 0;

    int frontLeftTicks = enfrenteDer.getCurrentPosition();
    int frontRightTicks = enfrenteIzq.getCurrentPosition();
    int backRightTicks = atrasDer.getCurrentPosition();
    int backLeftTicks = atrasIzq.getCurrentPosition();

    private ElapsedTime runtime = new ElapsedTime();

    public void init(HardwareMap ahwMap, Telemetry telemetry) {
        hwMap = ahwMap;

        enfrenteDer = hwMap.get(DcMotor.class, "enfrenteDer");
        enfrenteIzq = hwMap.get(DcMotor.class, "enfrenteIzq");
        atrasDer = hwMap.get(DcMotor.class, "atrasDer");
        atrasIzq = hwMap.get(DcMotor.class, "atrasIzq");

    }

    public void calcularPosicion(){
        double deltaX = (frontLeftTicks + frontRightTicks + backLeftTicks + backRightTicks) / 4.0;

        double deltaY = (frontLeftTicks - frontRightTicks - backLeftTicks + backRightTicks) / 4.0;

        double deltaHeading = (frontLeftTicks + frontRightTicks - backRightTicks - backLeftTicks) / 24;

        robotHeading += deltaHeading;
        double angleRad = Math.toRadians(robotHeading);
        double cosAngle = Math.cos(angleRad);
        double sinAngle = Math.sin(angleRad);

        robotX += deltaX * cosAngle - deltaY * sinAngle;
        robotY += deltaX * sinAngle + deltaY * cosAngle;

    }


    public void usarEncoder() {
        enfrenteDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasDer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        enfrenteDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        enfrenteIzq.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        atrasDer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        atrasIzq.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        countersPerCm = countersPerCm / (Math.PI * wheelDiameter);

    }
    
}


