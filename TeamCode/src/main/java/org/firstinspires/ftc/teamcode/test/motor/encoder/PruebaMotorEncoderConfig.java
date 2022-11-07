package org.firstinspires.ftc.teamcode.test.motor.encoder;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
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

public class PruebaMotorEncoderConfig {
    /**
     * Declaracion de los motores/servo -- modificar
     */
    //Declarar objetos (motores y servos), es recomendable usar el mismo nombre
    //para el objeto y en la configracion en el robot

    public DcMotor motor = null;

    /* local OpMode members. -- no modificar */
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    /* Constructor -- no modificar */
    public PruebaMotorEncoderConfig() {

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

        motor = hwMap.get(DcMotor.class, "motor");
        telemetry.addLine("Motores inicializados...");


        //Invertir giro de motores
        derecho(motor);
        telemetry.addLine("Cambio de giro de motores hecho...");

        //Motores al 0%
        motor.setPower(0);
        telemetry.addLine("Motores al 0%...");

        //Configurar modo
        usarUsingEncoder(motor);
        telemetry.addLine("Motores configurados...");

        //Mensaje
        telemetry.addLine("Robot inicilaizado con exito");
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

