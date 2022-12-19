package org.firstinspires.ftc.teamcode.test.motor.encoder;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.domain.Chasis;
import org.firstinspires.ftc.teamcode.test.motor.sinencoder.PruebaMotorSinEncoderConfig;


@TeleOp(name="PruebaMotorEncoder", group="Pushbot")
@Disabled
public class PruebaMotorEncoder extends LinearOpMode {
    PruebaMotorSinEncoderConfig robot = new PruebaMotorSinEncoderConfig();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap , telemetry);
        sleep(1000);
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        double velocidad = 0;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

             velocidad = Chasis.controlesVelocidad(gamepad1 , velocidad);

            if(gamepad1.dpad_up)
                robot.motor.setPower(velocidad);
            else if(gamepad1.dpad_down)
                robot.motor.setPower(-velocidad);
            else{
                robot.motor.setPower(0);
            }

            telemetry.addData("Velocidad" , velocidad*100 + "%");
            telemetry.update();

        }
    }

}
