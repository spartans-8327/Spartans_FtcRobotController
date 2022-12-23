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
        Chasis chasis = new Chasis(robot.enfrenteDer , robot.enfrenteIzq , robot.atrasDer , robot.atrasIzq);
        telemetry.update();

        waitForStart();

        chasis.moverseEnfrente(1, 1000);

    }

}
