package org.firstinspires.ftc.teamcode.calibracion;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.configuracion.RobotConfigMaster;
import org.firstinspires.ftc.teamcode.domain.Chasis;
import org.firstinspires.ftc.teamcode.domain.Elevador;

//@Disabled
@TeleOp(name="Calibración Chasis", group="Pushbot")

public class CalibracionChasis extends LinearOpMode {

    RobotConfigMaster robot = new RobotConfigMaster();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap , telemetry);
        Chasis chasis = new Chasis(robot.enfrenteDer , robot.enfrenteIzq , robot.atrasDer , robot.atrasIzq);

        telemetry.addLine("Iniciando calibración de chasis");
        telemetry.update();
        int pulsos = 0;
        int sumadorPulsos = 10;
        int pulsosElegidos = 0;


        double potenciaIda = 0.4;
        double potenciaRegreso = 0.2;

        waitForStart();

        while (opModeIsActive()){

            double stickIzquierdoY = -gamepad1.left_stick_y;
            double stickIzquierdoX = gamepad1.left_stick_x;
            double stickDerechoY = -gamepad1.right_stick_y;
            double stickDerechoX = gamepad1.right_stick_x;

            telemetry.addLine("$$$ CALIBRACION CHASIS $$$");
            telemetry.addLine("Usa este programa para calibrar el chasis del robot");
            telemetry.addLine("");

            telemetry.addLine("*** CONTROLES ***");
            telemetry.addData("Sumar/Restar pulsos" , " Bumpers (G1)");
            telemetry.addData("Sumador de pulsos" , " Dpad Arriba (G1)");
            telemetry.addData("Seleccionar acción", "Stick derecho (G1)");
            telemetry.addData("Regresar a posicion inicial", " B");
            telemetry.addLine("");

            telemetry.addLine("### DATOS ###");
            telemetry.addData("Sumador Pulsos " , sumadorPulsos);
            telemetry.addData("Pulsos " , pulsos);
            telemetry.addLine("");

            telemetry.update();



            //SELECCION DE SUMADOR DE PULSOS
            if (sumadorPulsos >= 10 && sumadorPulsos <= 1000){
                if(gamepad1.dpad_up){
                    sumadorPulsos = sumadorPulsos*10;
                    sleep(300);
                }
                sumadorPulsos =(sumadorPulsos < 10 || sumadorPulsos > 1000)? 10 :sumadorPulsos;
            }

            //SUMA DE PUNTOS
            if (gamepad1.right_bumper){
                pulsos += sumadorPulsos;
                sleep(300);
            }
            else if(gamepad1.left_bumper){
                pulsos -= sumadorPulsos;
                sleep(300);
            }

            //SELECCIÓN DE ACCIÓN  Y ACCIÓN
            if (stickIzquierdoY == 1)
                chasis.moverseEnfrente(1, pulsos);
             else if (stickIzquierdoY == -1)
                chasis.moverseAtras(1, pulsos );
             else if (stickIzquierdoX == 1)
                 chasis.moverseDerecha(1, pulsos);
             else if (stickIzquierdoX == -1)
                 chasis.moverseIzquierda(1, pulsos);

             else if (stickDerechoX == 1)
                 chasis.girarDerecha(1, pulsos);
             else if (stickIzquierdoX == -1)
                 chasis.girarIzquierda(1, pulsos);

        }



    }

    public void pararMotor(){
        robot.motor.setPower(0);
    }

}