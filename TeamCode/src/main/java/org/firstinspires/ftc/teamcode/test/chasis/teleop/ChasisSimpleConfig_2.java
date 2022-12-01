package org.firstinspires.ftc.teamcode.test.chasis.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.domain.Chasis;

public class ChasisSimpleConfig_2 {


    public DcMotor enfrenteDer = null; //0
    public DcMotor enfrenteIzq = null; //1
    public DcMotor atrasDer = null; //2
    public DcMotor atrasIzq = null; //3

    BNO055IMU imu;//VALDI PUTO


    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public ChasisSimpleConfig_2() {

    }

    public void init(HardwareMap ahwMap, Telemetry telemetry) {

        hwMap = ahwMap;

        enfrenteDer = hwMap.get(DcMotor.class, "enfrenteDer");
        enfrenteIzq = hwMap.get(DcMotor.class, "enfrenteIzq");
        atrasDer = hwMap.get(DcMotor.class, "atrasDer");
        atrasIzq = hwMap.get(DcMotor.class, "atrasIzq");
        telemetry.addLine("Motores inicializados...");

        imu = hwMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters IMUParameters = new BNO055IMU.Parameters();

        IMUParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        IMUParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        IMUParameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        IMUParameters.calibrationDataFile = "BNO055IMUCalibration.json";

        imu.initialize(IMUParameters);




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

    public void girarAngulo(double poder, double angulo , Chasis chasis){

        double error = obtenerError(angulo);

        if (error > 0){ //Derecha
            chasis.girarDerecha(poder);

            while (error>0)
                error = obtenerError(angulo);

            chasis.parar();

        } else {  //Izquierda
            chasis.girarIzquierda(poder);

            while(error < 0)
                error = obtenerError(angulo);

            chasis.parar();
        }
    }

    public double obtenerError(double angulo){
        double error = angulo - obtenerAngulo();
        error = (error>180) ? error-360 : error+360;
        return error;
    }

    public double obtenerAngulo(){
        Orientation angulos;
        angulos = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double primerAngulo = angulos.firstAngle;
        primerAngulo += 180;
        return primerAngulo;
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

