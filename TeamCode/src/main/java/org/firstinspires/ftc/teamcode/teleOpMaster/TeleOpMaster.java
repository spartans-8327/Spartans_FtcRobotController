package org.firstinspires.ftc.teamcode.teleOpMaster;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.configuracion.RobotConfigMaster;
import org.firstinspires.ftc.teamcode.domain.Chasis;
import org.firstinspires.ftc.teamcode.domain.Elevador;

@TeleOp(name="TeleOpMaster", group="Pushbot")

public class TeleOpMaster extends LinearOpMode {

    RobotConfigMaster robot = new RobotConfigMaster();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap , telemetry);
        Chasis chasis = new Chasis(robot.enfrenteDer , robot.enfrenteIzq , robot.atrasDer , robot.atrasIzq);
        Elevador elevador = new Elevador(robot.motor, robot.motor_1, robot.servo, this);
        telemetry.update();
        final double velocidad = 0.3;
        double incremento = 0.5;
        int incrementoGiro = 200;
        int incrementoElevador = 400;

        int posGiroNum = 0;
        int posEleNum = 0;

        waitForStart();

        while (opModeIsActive()){


            incremento = (gamepad1.left_stick_button || gamepad1.right_stick_button) ? 0.7 : 0;
            incrementoGiro = (gamepad2.left_stick_button || gamepad2.right_stick_button)? 200 : 0;

            telemetry.addData("Velocidad" , velocidad + incremento * 100 + "%");

            double stickIzquierdoY = -gamepad1.left_stick_y;
            double stickIzquierdoX = gamepad1.left_stick_x;

            double stickDerechoY = -gamepad1.right_stick_y;
            double stickDerechoX = gamepad1.right_stick_x;


            double stickIzquierdoY_2 = -gamepad2.left_stick_y;
            double stickIzquierdoX_2 = gamepad2.left_stick_x;

            double stickDerechoY_2 = -gamepad2.right_stick_y;
            double stickDerechoX_2 = gamepad2.right_stick_x;

            telemetry.addData("Stic isquierdo Y" , stickIzquierdoY);
            telemetry.addData("Stic isquierdo X " , stickIzquierdoX);
            telemetry.addData("Stic derecho Y " , stickDerechoY);
            telemetry.addData("Stic derecho X " , stickDerechoX);

            telemetry.addLine("CONTROL 2");

            telemetry.addData("Stic isquierdo 2 Y" , stickIzquierdoY_2);
            telemetry.addData("Stic isquierdo 2 X " , stickIzquierdoX_2);
            telemetry.addData("Stic derecho 2 Y " , stickDerechoY_2);
            telemetry.addData("Stic derecho 2 X " , stickDerechoX_2);

            telemetry.update();


            if (stickIzquierdoY > 0)
                chasis.moverseEnfrente(velocidad + incremento);
            else if (stickIzquierdoY < 0){
                chasis.moverseAtras(velocidad + incremento);
            }
            else if (stickIzquierdoX < 0){
                chasis.moverseIzquierda(velocidad + incremento);
            }
            else if (stickIzquierdoX > 0){
                chasis.moverseDerecha(velocidad + incremento);
            }
            else if (stickDerechoX < 0){
                chasis.girarIzquierda(velocidad + incremento);
            }
            else if(stickDerechoX > 0){
                chasis.girarDerecha(velocidad + incremento);
            } else {
                chasis.parar();
            }


            if (gamepad2.dpad_down)
                elevador.girar_0(1);
            else if (gamepad2.dpad_left)
                elevador.girar_1(1);
            else if (gamepad2.dpad_up)
                elevador.girar_2(1);
            else if (gamepad2.dpad_right)
                elevador.girar_3(1);

            if (gamepad2.b)
                elevador.irAlto(1);
            else if (gamepad2.y)
                elevador.irMedio(1);
            else if (gamepad2.x)
                elevador.irBajo(1);
            else if (gamepad2.a)
                elevador.irPiso(0.5);

            if (stickDerechoX_2 > 0 && stickDerechoX_2 < 0.5)
                elevador.elevadorManual(1, 200 , 100);
            else if (stickDerechoX_2 > 0.5 || stickDerechoX_2 == 1)
                elevador.girarManual(1, 400, 100);

            if (gamepad2.right_trigger > 0.7) {
                elevador.abrirGarra();
            }
            else {
                elevador.cerrarGarra();
            }

        }

    }

}
