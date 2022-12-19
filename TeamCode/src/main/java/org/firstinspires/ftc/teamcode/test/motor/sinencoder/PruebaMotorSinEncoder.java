package org.firstinspires.ftc.teamcode.test.motor.sinencoder;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="PruebaMotorSinEncoder", group="Pushbot")
@Disabled
public class PruebaMotorSinEncoder extends LinearOpMode {
    PruebaMotorSinEncoderConfig robot = new PruebaMotorSinEncoderConfig();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap , telemetry);
        sleep(1000);
        telemetry.update();



        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            robot.motor.setPower(1);
            telemetry.addData("Motor" , "100%");
            telemetry.update();

        }
    }

}
