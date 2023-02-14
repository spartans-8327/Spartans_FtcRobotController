package org.firstinspires.ftc.teamcode.autonomos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.configuracion.RobotConfigMaster;
import org.firstinspires.ftc.teamcode.domain.Chasis;
import org.firstinspires.ftc.teamcode.domain.Elevador;
import org.firstinspires.ftc.teamcode.domain.SensorDistancia;

//@Disabled
@Autonomous(name="PruebaSensor", group="Pushbot")

public class PruebaSensor extends LinearOpMode {

    RobotConfigMaster robot = new RobotConfigMaster();

    @Override
    public void runOpMode() {
        RobotConfigMaster robot = new RobotConfigMaster();
        robot.init(hardwareMap, telemetry);
        Chasis chasis = new Chasis(robot.motores, this, robot.imu);
        Elevador elevador = new Elevador(robot.motor, robot.motor_1, robot.servo, this, 2);
        SensorDistancia sensorDistancia = new SensorDistancia(robot.sensor_distancia);
        chasis.init();
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Distancia", sensorDistancia.obtenerCm());
            telemetry.addData("Junction detectado", sensorDistancia.junctionDetectado());
            telemetry.update();
        }

    }
}
