package org.firstinspires.ftc.teamcode.templates;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutonomoTempl", group="Pushbot")
@Disabled
public class AutonomoTempl extends LinearOpMode {

    ConfiguracionHardwareTempl robot = new ConfiguracionHardwareTempl();


    @Override
    public void runOpMode() {
        robot.init(hardwareMap , telemetry);
        sleep(1000);
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

        }
    }
}
