package org.firstinspires.ftc.teamcode.autonomos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.configuracion.RobotConfigMaster;
import org.firstinspires.ftc.teamcode.domain.Chasis;
import org.firstinspires.ftc.teamcode.domain.Elevador;
import org.firstinspires.ftc.teamcode.domain.ElevadorAutonomo;

//@Disabled
@Autonomous(name="ChasisAutonomo2", group="Pushbot")

public class ChasisAutonomo2 extends LinearOpMode {

    RobotConfigMaster robot = new RobotConfigMaster();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap , telemetry);
        Chasis chasis = new Chasis(robot.motores , this, robot.imu);
        ElevadorAutonomo elevador = new ElevadorAutonomo(robot.motor, robot.motor_1, robot.servo, this, 1);
        chasis.init();
        telemetry.update();

        waitForStart();

        elevador.cerrarGarra();
        chasis.moverseDerecha(1, Chasis.cuadroX);
        elevador.abrirGarra();

        }

}
