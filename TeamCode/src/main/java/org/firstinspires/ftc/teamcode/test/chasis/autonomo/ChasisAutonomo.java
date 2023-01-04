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
        Chasis chasis = new Chasis(robot.motores , this, robot.imu);
        ElevadorAuto elevador = new ElevadorAuto(robot.motor, robot.motor_1, robot.servo, this);
        chasis.init();
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            elevador.cerrarGarra();
            elevador.girar_1(0.7);
            elevador.irBajo(0.7);
            chasis.moverseY(0.5);
            elevador.abrirGarra();
            sleep(500);
            elevador.girar_0(0.7);
            elevador.irPiso(0.7);
            chasis.moverseY(1.5);
            chasis.girarC(-1);
            chasis.moverseY(0.8);
        }

    }

}
