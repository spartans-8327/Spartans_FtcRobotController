package org.firstinspires.ftc.teamcode.test.girosprecisos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.configuracion.RobotConfigMaster;
import org.firstinspires.ftc.teamcode.domain.Chasis;
import org.firstinspires.ftc.teamcode.templates.ConfiguracionHardwareTempl;

@Autonomous(name="GirosPrecisosAutonomo", group="Pushbot")
@Disabled
public class GirosPrecisos extends LinearOpMode {

    RobotConfigMaster robot = new RobotConfigMaster();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap , telemetry);
        Chasis chasis = new Chasis(robot.motores, this);
        sleep(1000);
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        //while (opModeIsActive()) {
            telemetry.addData("Angulo" , chasis.obtenerAngulo());
            telemetry.update();


            telemetry.addData("Angulo final" , chasis.obtenerAngulo());
            telemetry.update();


        }
    }
//}
