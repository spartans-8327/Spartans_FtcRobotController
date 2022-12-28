package org.firstinspires.ftc.teamcode.test.chasis.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.configuracion.RobotConfigMaster;
import org.firstinspires.ftc.teamcode.domain.Chasis;

@Disabled
@TeleOp(name="ChasisTeleop", group="Pushbot")

public class ChasisTeleop extends LinearOpMode {

    RobotConfigMaster robot = new RobotConfigMaster();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap , telemetry);
        Chasis chasis = new Chasis(robot.motores, this);
        telemetry.update();
        final double velocidad = 0.3;
        double incremento = 0.7;

        waitForStart();

        while (opModeIsActive()){

            incremento = (gamepad1.left_stick_button || gamepad1.right_stick_button) ? 0.7 : 0;
            telemetry.addData("Velocidad" , velocidad + incremento * 100 + "%");

            double stickIzquierdoY = -gamepad1.left_stick_y;
            double stickIzquierdoX = -gamepad1.left_stick_x;

            double stickDerechoY = -gamepad1.right_stick_y;
            double stickDerechoX = -gamepad1.right_stick_x;

            telemetry.addData("Stic isquierdo Y" , stickIzquierdoY);
            telemetry.addData("Stic isquierdo X " , stickIzquierdoX);
            telemetry.addData("Stic derecho Y " , stickDerechoY);
            telemetry.addData("Stic derecho X " , stickDerechoX);
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





        }

    }

}
