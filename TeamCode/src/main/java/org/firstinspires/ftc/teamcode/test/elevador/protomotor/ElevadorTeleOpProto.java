package org.firstinspires.ftc.teamcode.test.elevador.protomotor;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name="ElevadorProtoTest", group="Pushbot")
//@Disabled
public class ElevadorTeleOpProto extends LinearOpMode {
    ElevadorTestProtoConfig robot = new ElevadorTestProtoConfig();


    @Override
    public void runOpMode() {

        robot.init(hardwareMap , telemetry);
        ElevadorProto elevador = new ElevadorProto(robot.motor, 0);

        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        while (opModeIsActive()){

            telemetry.update();

            telemetry.addData("Grado Meta" , "270");
            telemetry.addData("Error" , elevador.encontrarError(270));

            telemetry.addData("Error Mínimo" , elevador.errorMinimo(elevador.encontrarError(270)));
            telemetry.addData("Error Definitivo" , elevador.arreglarError(elevador.errorMinimo(elevador.encontrarError(270))));



        }

        // run until the end of the match (driver presses STOP

    }


}
