package org.firstinspires.ftc.teamcode.test.servo;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="PruebaServo", group="Pushbot")
// @Disabled
public class PruebaServo extends LinearOpMode {

    PruebaServoConfig robot = new PruebaServoConfig();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap , telemetry);
        sleep(1000);
        telemetry.update();

        waitForStart();

        double posicion = 0;

        while (opModeIsActive()) {
            telemetry.addLine("Cambia la posicion del servo con los bumpers");

            telemetry.addData("" , "");
            telemetry.addData("Posicion del servo" , posicion);
            telemetry.addLine("Ever es gay");
            telemetry.update();

            if (gamepad1.left_bumper) {
                posicion -= 0.1;
                sleep(300);
            }
            else if (gamepad1.right_bumper) {
                posicion += 0.1;
                sleep(300);
            }

            if (posicion < 0){
                posicion = 0;
            } else if (posicion > 1)
                posicion = 1;

            robot.servo.setPosition(posicion);
        }
    }
}
