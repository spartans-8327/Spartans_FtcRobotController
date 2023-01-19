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
            telemetry.addData("Selecciona la posicion a la que quieres ir" , "Posicion " + posicion );

            telemetry.addData("" , "");
            telemetry.addData("A" , "Posicion 0");
            telemetry.addData("B" , "Posicion 0.3");
            telemetry.addData("X" , "Posicion 0.6");
            telemetry.addData("Y" , "Posicion 1.0");
            telemetry.update();

            if (gamepad1.a)
                posicion = 0;
            else if (gamepad1.b)
                posicion = 0.3;
            else if (gamepad1.x)
                posicion = 0.6;
            else if (gamepad1.y)
                posicion = 1;

            robot.servo.setPosition(posicion);
        }
    }
}
