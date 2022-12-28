package org.firstinspires.ftc.teamcode.test.chasis.autonomo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.configuracion.RobotConfigMaster;
import org.firstinspires.ftc.teamcode.domain.*;
import org.firstinspires.ftc.teamcode.test.chasis.teleop.ChasisSimpleConfig_2;
//@Disabled
@Autonomous(name="ChasisAutonomo", group="Pushbot")

public class ChasisAutonomo extends LinearOpMode {

    RobotConfigMaster robot = new RobotConfigMaster();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap , telemetry);
        Chasis chasis = new Chasis(robot.motores, this);
        chasis.init();
        telemetry.update();

        waitForStart();

        chasis.irEnfrente(800, 0.0008);

    }

}
