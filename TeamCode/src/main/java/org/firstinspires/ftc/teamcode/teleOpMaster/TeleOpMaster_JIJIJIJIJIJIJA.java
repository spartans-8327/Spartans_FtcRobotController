package org.firstinspires.ftc.teamcode.teleOpMaster;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.configuracion.RobotConfigMaster;
import org.firstinspires.ftc.teamcode.configuracion.RobotConfigMaster_2;
import org.firstinspires.ftc.teamcode.domain.Chasis;
import org.firstinspires.ftc.teamcode.domain.Elevador;

@TeleOp(name="TeleOpMaster_JIJIJIJIJIJIJA", group="Pushbot")

public class TeleOpMaster_JIJIJIJIJIJIJA extends LinearOpMode {

    RobotConfigMaster_2 robot = new RobotConfigMaster_2();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap , telemetry);
        Elevador elevador = new Elevador(robot.motor, robot.motor_1, robot.servo, this, 2);
        telemetry.update();
        final double velocidad = 0.5;
        double incremento = 0;
        boolean cambio = true;
        int pulsosElevador = 0;

        waitForStart();

        while (opModeIsActive()){

            pulsosElevador = (pulsosElevador >= 0)? robot.motor.getCurrentPosition(): 0;

            incremento = (gamepad1.left_stick_button || gamepad1.right_stick_button) ? 0.7 : 0;

            telemetry.addData("Velocidad" , (velocidad + incremento)* 100 + "%");

            double stickIzquierdoY = -gamepad1.left_stick_y;
            double stickIzquierdoX = gamepad1.left_stick_x;

            double stickDerechoY = -gamepad1.right_stick_y;
            double stickDerechoX = gamepad1.right_stick_x;


            double stickIzquierdoY_2 = -gamepad2.left_stick_y;
            double stickIzquierdoX_2 = gamepad2.left_stick_x;

            double stickDerechoY_2 = -gamepad2.right_stick_y;
            double stickDerechoX_2 = gamepad2.right_stick_x;

            telemetry.addData("gay el que lo lea", 13);

            telemetry.addLine("CONTROL 1");
            telemetry.addData("Stic isquierdo Y" , stickIzquierdoY);
            telemetry.addData("Stic isquierdo X " , stickIzquierdoX);
            telemetry.addData("Stic derecho Y " , stickDerechoY);
            telemetry.addData("Stic derecho X " , stickDerechoX);
            telemetry.addLine("");
            telemetry.addLine("CONTROL 2");
            telemetry.addData("Stic isquierdo 2 Y" , stickIzquierdoY_2);
            telemetry.addData("Stic isquierdo 2 X " , stickIzquierdoX_2);
            telemetry.addData("Stic derecho 2 Y " , stickDerechoY_2);
            telemetry.addData("Stic derecho 2 X " , stickDerechoX_2);

            telemetry.addData("Pos actual", elevador.pulsosGiroAct / 490);


            telemetry.update();

            //Control de giro (Autom??tico)
            if (robot.motor_1.isBusy() == false && gamepad2.left_trigger < 0.9){
                if (gamepad2.dpad_down)
                    elevador.girar_0(0.5);
                else if (gamepad2.dpad_left)
                    elevador.girar_1(0.5);
                else if (gamepad2.dpad_up)
                    elevador.girar_2(0.5);
                else if (gamepad2.dpad_right)
                    elevador.girar_3(0.5);
            }

            //Control de giro (Manual)
            if (robot.motor_1.isBusy() == false && gamepad2.left_trigger >= 0.9){
                if (gamepad2.dpad_right){
                    elevador.girarManual(0.4, 200);
                } else if(gamepad2.dpad_left){
                    elevador.girarManual(0.4, -200);
                }
            }

            
            //Control de elevador (Manual)
            if (stickIzquierdoY_2 == 1) {
                robot.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motor.setPower(1);
                telemetry.addLine("Subiendo");
                cambio = true;
            }
            else if (stickIzquierdoY_2 == -1 && robot.motor.getCurrentPosition() > 0) {
                robot.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motor.setPower(-1);
                telemetry.addLine("Bajando");
                cambio = true;
            }
            else if (cambio){
                robot.motor.setTargetPosition(robot.motor.getCurrentPosition());
                robot.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                cambio = false;
            }
            else {
                robot.motor.setPower(1);
            }

            //Control de garra
            if (gamepad2.right_trigger > 0.7) {
                elevador.abrirGarra();
            } else {
                elevador.cerrarGarra();
            }

        }



    }

}


