package org.firstinspires.ftc.teamcode.test.elevador;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.domain.Elevador;
import org.firstinspires.ftc.teamcode.test.motor.sinencoder.PruebaMotorSinEncoderConfig;

@Autonomous(name="Elevador Test", group="Pushbot")
//@Disabled
public class ElevadorTest extends LinearOpMode {
    ElevadorTestConfig robot = new ElevadorTestConfig();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap , telemetry);
        telemetry.update();
        Elevador elevador = new Elevador(robot.motor , robot.motor_2, robot.servo ,
                this);

        // Wait for the game to start (driver presses PLAY)

        waitForStart();
        elevador.irBajo(1);
        sleep(2000);

        elevador.irMoverse(1);
        sleep(2000);

        elevador.irMedio(1);
        sleep(2000);

        elevador.irAlto(1);
        sleep(2000);


        elevador.irMedio(1);
        sleep(2000);

        elevador.irPiso(1);
        sleep(2000);

        elevador.girar_0(1);
        sleep(2000);



        // run until the end of the match (driver presses STOP)
    }


}
