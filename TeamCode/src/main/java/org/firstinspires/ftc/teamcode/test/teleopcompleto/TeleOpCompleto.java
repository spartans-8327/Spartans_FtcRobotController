package org.firstinspires.ftc.teamcode.test.teleopcompleto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.domain.Chasis;

//UN SOLO MANDO

@TeleOp(name = "TeleOpCompleto", group = "Pushbot")

public class TeleOpCompleto extends LinearOpMode {

    TeleOpCompletoConfig robot = new TeleOpCompletoConfig();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap, telemetry);
        Chasis chasis = new Chasis(robot.enfrenteDer, robot.enfrenteIzq, robot.atrasDer, robot.atrasIzq);
        telemetry.update();
        final double velocidad = 0.3;
        double aumento = 0;
        boolean posicionMotor_1 = false;
        int posicionMotorHex = 0;
        final int pulsosposicion = 177;
        final double SERVOTOMARCUBOS = 0;
        final double SERVOTIRARCUBOS = 0.8;

        waitForStart();

        while (opModeIsActive()) {

            double x1 = gamepad1.left_stick_x;
            double y1 = -gamepad1.left_stick_y;

            double x2 = gamepad1.right_stick_x;
            double y2 = -gamepad1.right_stick_y;

            telemetry.update();

            aumento = (gamepad1.left_stick_button || gamepad1.right_stick_button) ? 0.7 : 0;
            double velocidadFinal = velocidad + aumento;
            telemetry.addData("Velocidad Final:", velocidadFinal);

            if (y1 > 0.7)
                chasis.moverseEnfrente(velocidadFinal);
            else if (y1 < -0.7)
                chasis.moverseAtras(velocidadFinal);
            else if (x1 < -0.7)
                chasis.moverseIzquierda(velocidadFinal);
            else if (x1 > 0.7)
                chasis.moverseDerecha(velocidadFinal);
            else if (x2 < -0.7)
                chasis.girarIzquierda(velocidadFinal);
            else if (x2 > 0.7)
                chasis.girarDerecha(velocidadFinal);
            else{
                chasis.parar();
                telemetry.addData("Chasis" , "Parao");
            }


            if (gamepad1.right_trigger == 1) {
                if (posicionMotor_1 == false)
                    robot.moverseDistancia(robot.motor_1, 1, 1400);
                robot.servo.setPosition(SERVOTIRARCUBOS);
                posicionMotor_1 = true;
            } else {
                if (posicionMotor_1 == true)
                    robot.moverseDistancia(robot.motor_1, 1, -1450);
                robot.servo.setPosition(SERVOTOMARCUBOS);
                posicionMotor_1 = false;
            }

            //posicion 0
            if (gamepad1.dpad_left){
                if (posicionMotorHex == 1)
                    robot.moverseDistancia(robot.hex_0, 1 , -pulsosposicion);
                else if (posicionMotorHex == 2)
                    robot.moverseDistancia(robot.hex_0, 1 , -pulsosposicion*2);
                posicionMotorHex = 0;
            }
            //posicion 1
            else if (gamepad1.dpad_up){
                if (posicionMotorHex == 0)
                    robot.moverseDistanciaMantener(robot.hex_0, 1 , pulsosposicion);
                else if(posicionMotorHex == 2)
                    robot.moverseDistanciaMantener(robot.hex_0, 1 , -pulsosposicion - 5);
                posicionMotorHex = 1;
            }
            //posicion 2
            else if (gamepad1.dpad_right){
                if (posicionMotorHex == 0)
                    robot.moverseDistancia(robot.hex_0, 0.5 , pulsosposicion*2);
                else if (posicionMotorHex == 1)
                    robot.moverseDistancia(robot.hex_0 ,0.5 ,pulsosposicion);
                posicionMotorHex = 2;
            }

        }


    }


}
