package org.firstinspires.ftc.teamcode.teleOpMaster;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.configuracion.RobotConfigMaster;
import org.firstinspires.ftc.teamcode.domain.Chasis;
import org.firstinspires.ftc.teamcode.domain.Elevador;

@TeleOp(name="TeleOpManual", group="Pushbot")

public class TeleOpMaster extends LinearOpMode {

    @Override
    public void runOpMode() {
        RobotConfigMaster robot = new RobotConfigMaster();
        robot.init(hardwareMap , telemetry);
        Chasis chasis = new Chasis(robot.motores , this, robot.imu);
        Elevador elevador = new Elevador(robot.motor, robot.motor_1, robot.servo, robot.servo_2, this, 2);
        chasis.init();
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
            telemetry.addData("Pos elevador" , elevador.elevador.getCurrentPosition());


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
                if (gamepad2.a)
                    elevador.girar_0(0.5);
                else if (gamepad2.x)
                    elevador.girar_1(0.5);
                else if (gamepad2.y)
                    elevador.girar_2(0.5);
                else if (gamepad2.b)
                    elevador.girar_3(0.5);
            }

            //Control de giro (Manual)
            int contadorManual = 0;
            if (robot.motor_1.isBusy() == false && gamepad2.left_trigger >= 0.9){
                if (gamepad2.dpad_right){
                    elevador.girarManual(0.4, 100);
                    ++contadorManual;
                } else if(gamepad2.dpad_left){
                    elevador.girarManual(0.4, -100);
                    --contadorManual;
                }
            }

            int pulsosTopeSuperior = 3600;
            int pulsosTopeInferior = 0;

            //Control de elevador (Manual)
            if (stickIzquierdoY_2 == 1 && elevador.elevador.getCurrentPosition() < pulsosTopeSuperior) {
                robot.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motor.setPower(1);
                telemetry.addLine("Subiendo");
                cambio = true;
            }


            else if (stickIzquierdoY_2 == -1 && elevador.elevador.getCurrentPosition() > pulsosTopeInferior) {
                robot.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motor.setPower(-0.7);
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


