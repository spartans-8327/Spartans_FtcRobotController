package org.firstinspires.ftc.teamcode.teleOpMaster;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.configuracion.RobotConfigMaster;
import org.firstinspires.ftc.teamcode.domain.Chasis;
import org.firstinspires.ftc.teamcode.domain.Elevador;

@TeleOp(name="TeleOpMasternoubvtdedcytgvu", group="Pushbot")

public class TeleOpMasternoubvtdedcytgvu extends LinearOpMode {

    RobotConfigMaster robot = new RobotConfigMaster();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap , telemetry);
        Chasis chasis = new Chasis(robot.motores , this, robot.imu);
        Elevador elevador = new Elevador(robot.motor, robot.motor_1, robot.servo, this, 180);
        chasis.init();
        telemetry.update();
        final double velocidad = 0.5;
        double incremento = 0;
        boolean cambio = true;
        int pulsosElevador = 0;

        waitForStart();

        while (opModeIsActive()){

            double stickIzquierdoY = -gamepad1.left_stick_y;
            double stickIzquierdoX = gamepad1.left_stick_x;

            double stickDerechoY = -gamepad1.right_stick_y;
            double stickDerechoX = gamepad1.right_stick_x;


            double stickIzquierdoY_2 = -gamepad2.left_stick_y;
            double stickIzquierdoX_2 = gamepad2.left_stick_x;

            double stickDerechoY_2 = -gamepad2.right_stick_y;
            double stickDerechoX_2 = gamepad2.right_stick_x;

            telemetry.addLine("CONTROL 2");
            telemetry.addData("Stic isquierdo 2 Y" , stickIzquierdoY_2);
            telemetry.addData("Stic isquierdo 2 X " , stickIzquierdoX_2);
            telemetry.addData("Stic derecho 2 Y " , stickDerechoY_2);
            telemetry.addData("Stic derecho 2 X " , stickDerechoX_2);

            telemetry.addData("Pos actual", elevador.pulsosGiroAct / 490);


            telemetry.update();

            //Control de giro (Automático)
            if (robot.motor_1.isBusy() == false && gamepad2.left_trigger < 0.9){
                if (gamepad2.dpad_down)
                    telemetry.addData("Pulsos necesarios", elevador.girar90Grados(0, 1));


            }


        }



    }

}


