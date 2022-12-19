package org.firstinspires.ftc.teamcode.test.motor.sinencoder;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="PruebaMotorSinEncoderManual", group="Pushbot")
@Disabled
public class PruebaMotorSinEncoderManual extends LinearOpMode {
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

            if(gamepad1.dpad_up) {
                robot.motor.setPower(1);
                telemetry.addData("Pa arriba pai" , 20);
            }

            else if (gamepad1.dpad_down){
                robot.motor.setPower(-1);
                telemetry.addData("Pa abajo pai" , 20);
            }

            else{
                robot.motor.setPower(0);
            }

            telemetry.update();


        }
    }

}
