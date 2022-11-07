package org.firstinspires.ftc.teamcode.test.girosprecisos;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * HARDWARE TEMPLATE
 * Usa este template para:
 * -Inicializar tus motores y servos
 * -Sentido de giro de los motores
 * -Modo de uso de los motores
 * <p>
 * *
 */

public class GirosPrecisosConfig {
    /**
     * Declaracion de los motores/servo -- modificar
     */
    //Declarar objetos (motores y servos), es recomendable usar el mismo nombre
    //para el objeto y en la configracion en el robot

    public DcMotor enfrenteIzq = null;
    public DcMotor enfrenteDer = null;
    public DcMotor atrasIzq = null;
    public DcMotor atrasDer = null;

    BNO055IMU imu;

    /* local OpMode members. -- no modificar */
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    /* Constructor -- no modificar */
    public GirosPrecisosConfig() {

    }

    /**
     * Inicializar hardware --modificar
     */
    public void init(HardwareMap ahwMap, Telemetry telemetry) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Definir e inicializar hardware
        /*En las comillas (deviceName) debe de ir el nombre que hayas puesto en la configuracion
        del robot*/

        enfrenteDer = hwMap.get(DcMotor.class, "enfrenteDer");
        enfrenteIzq = hwMap.get(DcMotor.class, "enfrenteIzq");
        atrasDer = hwMap.get(DcMotor.class, "atrasDer");
        atrasIzq = hwMap.get(DcMotor.class, "atrasIzq");
        telemetry.addLine("Motores inicializados...");

        imu = hwMap.get(BNO055IMU.class , "imu");

        BNO055IMU.Parameters IMUParameters = new BNO055IMU.Parameters();

        IMUParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        IMUParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        IMUParameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        IMUParameters.calibrationDataFile = "BNO055IMU.json";

        imu.initialize(IMUParameters);

        //Invertir giro de motores
        reversa(enfrenteIzq, atrasIzq);
        derecho(enfrenteDer, atrasDer);
        telemetry.addLine("Cambio de giro de motores hecho...");
        telemetry.update();

        //Motores al 0%
        enfrenteDer.setPower(0);
        enfrenteIzq.setPower(0);
        atrasDer.setPower(0);
        atrasIzq.setPower(0);
        telemetry.addLine("Motores al 0%...");
        telemetry.update();

        //Configurar modo
        usarWithoutEncoder(enfrenteDer, enfrenteIzq, atrasDer, atrasIzq);
        telemetry.addLine("Motores configurados...");
        telemetry.update();

        //Mensaje
        telemetry.addData("Hardware", "Inicializado");
        telemetry.update();
        /** Fin de la configuracion*/

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

