package org.firstinspires.ftc.teamcode.configuracion;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.domain.Chasis;

public class RobotConfigMaster {

    //CONTROL HUB
    public DcMotor motor = null;// 0 ELEVADOR
    public DcMotor motor_1 = null; //1 GIRO

    //EXPANSION HUB
    public DcMotor enfrenteDer = null; //0
    public DcMotor enfrenteIzq = null; //1
    public DcMotor atrasDer = null; //2
    public DcMotor atrasIzq = null; //3

    public DcMotor[] motores= {enfrenteDer,enfrenteIzq, atrasDer, atrasIzq};

    public Servo servo = null;

    public BNO055IMU imu;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public RobotConfigMaster() {

    }

    public void init(HardwareMap ahwMap, Telemetry telemetry) {

        hwMap = ahwMap;

        motores[0] = hwMap.get(DcMotor.class, "enfrenteDer");
        motores[1] = hwMap.get(DcMotor.class, "enfrenteIzq");
        motores[2] = hwMap.get(DcMotor.class, "atrasDer");
        motores[3]= hwMap.get(DcMotor.class, "atrasIzq");

        enfrenteDer = motores[0];
        enfrenteIzq = motores[1];
        atrasDer = motores[2];
        atrasIzq = motores[3];

        motor = hwMap.get(DcMotor.class, "motor");
        motor_1 = hwMap.get(DcMotor.class, "motor_1");

        telemetry.addLine("Motores inicializados...");
        telemetry.update();

        servo = hwMap.get(Servo.class, "servo");

        imu = hwMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters IMUParameters = new BNO055IMU.Parameters();

        IMUParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        IMUParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        IMUParameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        IMUParameters.calibrationDataFile = "BNO055IMUCalibration.json";
        imu.initialize(IMUParameters);


        reversa(atrasIzq , enfrenteIzq, motor);
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
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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

    public void moverseDistanciaMantener_1(double potencia , int distance){
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor.setTargetPosition(distance);

        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor.setPower(potencia);

    }

}

