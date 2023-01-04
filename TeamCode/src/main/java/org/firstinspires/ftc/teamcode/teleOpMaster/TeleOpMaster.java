package org.firstinspires.ftc.teamcode.teleOpMaster;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.configuracion.RobotConfigMaster;
import org.firstinspires.ftc.teamcode.domain.Chasis;
import org.firstinspires.ftc.teamcode.domain.Elevador;

@TeleOp(name="TeleOpManual", group="Pushbot")

public class TeleOpMaster extends LinearOpMode {

    RobotConfigMaster robot = new RobotConfigMaster();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap , telemetry);
        Chasis chasis = new Chasis(robot.motores , this, robot.imu);
        Elevador elevador = new Elevador(robot.motor, robot.motor_1, robot.servo, this);
        chasis.init();
        telemetry.update();
        final double velocidad = 0.3;
        double incremento = 0.5;
        waitForStart();

        while (opModeIsActive()){

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

            telemetry.addData("Pulsos actual", elevador.pulsosActual);
            telemetry.addData("pto el q lo lea",10);

            telemetry.update();

            //Control de chasis
            if (stickIzquierdoY > 0.9)
                chasis.moverseAtras(velocidad + incremento);
            else if (stickIzquierdoY < -0.9){
                chasis.moverseEnfrente(velocidad + incremento);
            }
            else if (stickIzquierdoX < -0.9){
                chasis.moverseIzquierda(velocidad + incremento);
            }
            else if (stickIzquierdoX > 0.9){
                chasis.moverseDerecha(velocidad + incremento);
            }
            else if (stickDerechoX < -0.9){
                chasis.girarIzquierda(velocidad + incremento);
            }
            else if(stickDerechoX > 0.9){
                chasis.girarDerecha(velocidad + incremento);
            } else {
                chasis.parar();
            }

            //Control de giro (Automático)
            if (robot.motor_1.isBusy() == false && gamepad2.left_trigger < 0.9){
                if (gamepad2.dpad_down)
                    elevador.girar_0(1);
                else if (gamepad2.dpad_left)
                    elevador.girar_1(1);
                else if (gamepad2.dpad_up)
                    elevador.girar_2(1);
                else if (gamepad2.dpad_right)
                    elevador.girar_3(1);
            }

            //Control de giro (Manual)
            if (robot.motor_1.isBusy() == false && gamepad2.left_trigger >= 0.9){
                if (gamepad2.dpad_right){
                    elevador.girarManual(0.4, 200);
                } else if(gamepad2.dpad_left){
                    elevador.girarManual(0.4, -200);
                }
            }

            //Control de elevador (Automático)
            if (robot.motor.isBusy() == false && gamepad2.left_bumper == false && gamepad2.left_trigger < 0.9){
                if (gamepad2.b)
                    elevador.irAlto(1);
                else if (gamepad2.y)
                    elevador.irMedio(1);
                else if (gamepad2.x)
                    elevador.irBajo(1);
                else if (gamepad2.a)
                    elevador.irPiso(0.6);
            }

            if (robot.motor.isBusy() == false && gamepad2.left_bumper && gamepad2.left_trigger < 0.9){
                if (gamepad2.b)
                    elevador.irCono5(1);
                else if (gamepad2.y)
                    elevador.irCono4(1);
            }

            //Control de elevador (Manual)
            if (robot.motor.isBusy() == false && gamepad2.left_trigger >= 0.9){
                if (gamepad2.y){
                    elevador.elevadorManual(0.4, 200);
                } else if(gamepad2.a){
                    elevador.elevadorManual(0.4, -200);
                }
            }

            //Control de garra
            if (gamepad2.right_trigger > 0.7) {
                elevador.abrirGarra();
            }
            else {
                elevador.cerrarGarra();
            }

        }

    }

}


