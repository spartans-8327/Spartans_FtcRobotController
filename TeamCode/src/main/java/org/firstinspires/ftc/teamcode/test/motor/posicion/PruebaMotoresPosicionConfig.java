package org.firstinspires.ftc.teamcode.test.motor.posicion;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
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

public class PruebaMotoresPosicionConfig {


    public static double controlesVelocidad (Gamepad gamepad, double velocidad){
        velocidad = (gamepad.a)? 0 : velocidad;
        velocidad = (gamepad.x)? 0.3 : velocidad;
        velocidad = (gamepad.y)? 0.6 : velocidad;
        velocidad = (gamepad.b)? 1 : velocidad;

        return velocidad;
    }
    /**
     * Declaracion de los motores/servo -- modificar
     */
    //Declarar objetos (motores y servos), es recomendable usar el mismo nombre
    //para el objeto y en la configracion en el robot

    public DcMotor motor = null;
    public DcMotor motor_2 = null;

    /* local OpMode members. -- no modificar */
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    /* Constructor -- no modificar */
    public PruebaMotoresPosicionConfig() {

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
        motor_2 = hwMap.get(DcMotor.class, "motor_2");
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

