package org.firstinspires.ftc.teamcode.test.elevador;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.teamcode.configuracion.RobotConfigMaster;
import org.firstinspires.ftc.teamcode.domain.Elevador;

@TeleOp(name="ElevadorTeleOp", group="Pushbot")
//@Disabled
public class ElevadorTeleOp extends LinearOpMode {
    RobotConfigMaster robot = new RobotConfigMaster();


    @Override
    public void runOpMode() {

        robot.init(hardwareMap , telemetry);
        Elevador elevador = new Elevador(robot.motor, robot.motor_1,  robot.servo ,  this);

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

            if (stickIzquierdo_y != 0){
                robot.motor.setPower(stickIzquierdo_y);
            }

            if (gamepad2.right_trigger > 0.7)
                elevador.abrirGarra();
            else
                elevador.cerrarGarra();

        }

        // run until the end of the match (driver presses STOP

    }


}
