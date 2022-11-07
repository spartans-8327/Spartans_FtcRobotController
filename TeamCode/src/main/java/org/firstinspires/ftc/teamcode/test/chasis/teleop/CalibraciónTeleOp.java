package org.firstinspires.ftc.teamcode.test.chasis.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.domain.Chasis;

@TeleOp(name="Calibración TeleOp", group="Pushbot")

public class CalibraciónTeleOp extends LinearOpMode {

    ChasisSimpleConfig_2 robot = new ChasisSimpleConfig_2();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap , telemetry);
        Chasis chasis = new Chasis(robot.enfrenteDer , robot.enfrenteIzq , robot.atrasDer , robot.atrasIzq);
        telemetry.update();;
        int pulsos = 0;

        waitForStart();

        while (opModeIsActive()){

            telemetry.addLine("$$$ CALIBRACIÓN $$$");
            telemetry.addLine("Este programa sirve para que encuentres los pulsos necesarios para ");
            telemetry.addLine("*** CONTROLES ***");
            telemetry.addData("Aumentar velocidad" , " Dpad Derecha & Izquierda (G1)");
            telemetry.addData("Pulsos" , " Dpad Arriba & Abajo (G1)");
            telemetry.addData("Sumador de pulsos" , " Bumpers (G1)");
            telemetry.addData("Cambiar de acción" , "X & B");
            telemetry.addLine("");

            telemetry.addLine("+++ SUMADOR DE PULSOS +++ ()");
            telemetry.addLine("+10");
            telemetry.addLine("+100");
            telemetry.addLine("+1000");
            telemetry.addLine("");

            telemetry.addLine("!!! ACCIÓN !!!");
            telemetry.addLine("Moverse enfrente");
            telemetry.addLine("Moverse atrás");
            telemetry.addLine("Moverse izquierda");
            telemetry.addLine("Moverse derecha");
            telemetry.addLine("Girar derecha");
            telemetry.addLine("Girar izquierda");


            telemetry.update();



        }

    }

}
