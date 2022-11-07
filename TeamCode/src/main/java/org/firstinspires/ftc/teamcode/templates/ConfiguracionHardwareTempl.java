package org.firstinspires.ftc.teamcode.templates;

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

public class ConfiguracionHardwareTempl {
    /**
     * Declaracion de los motores/servo -- modificar
     */
    //Declarar objetos (motores y servos), es recomendable usar el mismo nombre
    //para el objeto y en la configracion en el robot

    public DcMotor enfrenteIzq = null;
    public DcMotor enfrenteDer = null;
    public DcMotor atrasIzq = null;
    public DcMotor atrasDer = null;

    public Servo garraIzq = null;
    public Servo garraDer = null;

    //Valores para posiciones del servo
    public static final double MID_SERVO = 0.5;
    public static final double ARM_UP_POWER = 0.45;
    public static final double ARM_DOWN_POWER = -0.45;


    /* local OpMode members. -- no modificar */
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    /* Constructor -- no modificar */
    public ConfiguracionHardwareTempl() {

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

        //Inicializar servos y colocarlos a la posicion inicial (eso si se puede hacer en el init)
        garraDer = hwMap.get(Servo.class, "garraDer");
        garraIzq = hwMap.get(Servo.class, "garraIzq");
        garraDer.setPosition(MID_SERVO);
        garraIzq.setPosition(MID_SERVO);
        telemetry.addLine("Servos configurados...");
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

