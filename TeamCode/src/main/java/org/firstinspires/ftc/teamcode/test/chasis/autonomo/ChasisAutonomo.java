package org.firstinspires.ftc.teamcode.test.chasis.autonomo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.domain.*;
import org.firstinspires.ftc.teamcode.test.chasis.teleop.ChasisSimpleConfig_2;

@Autonomous(name="ChasisAutonomo", group="Pushbot")
//@Disabled2
public class ChasisAutonomo extends LinearOpMode {

    ChasisSimpleConfig_2 robot = new ChasisSimpleConfig_2();
    final int PULSOSPOSICION = 177;


    @Override
    public void runOpMode() {
        robot.init(hardwareMap , telemetry);
        Chasis chasis = new Chasis(robot.enfrenteDer , robot.enfrenteIzq , robot.atrasDer , robot.atrasIzq);
        telemetry.update();

        waitForStart();

        chasis.moverseEnfrente(0.3);
        sleep(1000);

        chasis.moverseAtras(0.3);
        sleep(1000);

        chasis.moverseIzquierda(0.3);
        sleep(1000);

        chasis.moverseDerecha(0.3);
        sleep(1000);

        chasis.girarIzquierda(0.3);
        sleep(1000);

        chasis.moverseEnfDer(0.3);
        sleep(1000);

        chasis.moverseAtrIzq(0.3);
        sleep(1000);

    }

}
