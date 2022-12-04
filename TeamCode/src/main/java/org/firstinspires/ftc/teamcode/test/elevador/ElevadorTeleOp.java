package org.firstinspires.ftc.teamcode.test.elevador;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.domain.Elevador;

@TeleOp(name="ElevadorTeleOp", group="Pushbot")
//@Disabled
public class ElevadorTeleOp extends LinearOpMode {
    ElevadorTestConfig robot = new ElevadorTestConfig();


    @Override
    public void runOpMode() {

        robot.init(hardwareMap , telemetry);
        Elevador elevador = new Elevador(robot.motor, robot.motor_2,  robot.servo ,  this);

        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        while (opModeIsActive()){

            telemetry.update();
            double stickIzquierdo_y = -gamepad1.left_stick_y;
            double stickIzquierdo_x = gamepad1.left_stick_x;

            double stickDerecho_x = gamepad1.right_stick_x;
            double stickDerecho_y = -gamepad1.right_stick_y;

            if (gamepad2.b)
                elevador.irAlto(0.3);
            else if(gamepad2.y)
                elevador.irMedio(0.3);
            else if (gamepad2.x)
                elevador.irBajo(0.3);
            else if (gamepad2.a)
                elevador.irPiso(1);

            if (gamepad1.right_trigger > 0.7)
                elevador.abrirGarra();
            else
                elevador.cerrarGarra();



        }

        // run until the end of the match (driver presses STOP

    }


}
