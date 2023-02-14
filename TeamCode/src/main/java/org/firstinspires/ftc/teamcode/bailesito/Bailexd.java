package org.firstinspires.ftc.teamcode.bailesito;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.configuracion.RobotConfigMaster;
import org.firstinspires.ftc.teamcode.domain.Chasis;

//@Disabled
@Autonomous(name="Baile de la victoria", group="Pushbot")

public class Bailexd extends LinearOpMode {

    RobotConfigMaster robot = new RobotConfigMaster();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap , telemetry);
        Chasis chasis = new Chasis(robot.motores , this, robot.imu);

        chasis.girarDerecha(1);
        

        chasis.init();
        telemetry.update();

        waitForStart();

        }

}
