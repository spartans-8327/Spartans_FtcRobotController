package org.firstinspires.ftc.teamcode.test.elevador;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.domain.Elevador;

@TeleOp(name="Elevador TeleOp", group="Pushbot")
//@Disabled
public class ElevadorTeleOp extends LinearOpMode {
    ElevadorTestConfig robot = new ElevadorTestConfig();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap , telemetry);
        telemetry.update();
        Elevador elevador = new Elevador(robot.motor , robot.servo);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        while (opModeIsActive()){
            double stickIzquierdo = -gamepad1.left_stick_y;

            elevador.subir(stickIzquierdo);


        }

        // run until the end of the match (driver presses STOP

    }


}
