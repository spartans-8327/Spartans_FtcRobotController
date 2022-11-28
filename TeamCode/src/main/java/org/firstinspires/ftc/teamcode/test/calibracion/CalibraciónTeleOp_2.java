package org.firstinspires.ftc.teamcode.test.calibracion;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Calibración Motor TeleOp_2", group="Pushbot")

public class CalibraciónTeleOp_2 extends LinearOpMode {

    CalibracionConfig_2 robot = new CalibracionConfig_2();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap , telemetry);

        telemetry.update();
        int pulsos = 0;
        int sumadorPulsos = 10;


        double velocidadReal = 0;
        int velocidadSeleccion = 0;
        final double velocidadMinima = 0.1;
        final double velocidadMediana = 0.5;
        final double velocidadMaxima = 1;

        int accionSeleccion = 0;

        boolean direccionDerecho = true;


        waitForStart();

        while (opModeIsActive()){

            moverseDistanciaMantener(0.3 , 200);

            telemetry.addLine("$$$ CALIBRACIÓN $$$");
            telemetry.addLine("Este programa sirve para que encuentres los pulsos necesarios para cierta acciòn en un programa");
            telemetry.addLine("*** CONTROLES ***");
            telemetry.addData("Aumentar velocidad" , " Dpad Derecha (G1)");
            telemetry.addData("Pulsos" , " Dpad Arriba (G1)");
            telemetry.addData("Sumador de pulsos" , " Bumpers (G1)");
            telemetry.addData("Cambiar de acción" , "B");
            telemetry.addLine("");

            telemetry.addLine("+++ SUMADOR DE PULSOS +++ ()");
            telemetry.addLine("+10");
            telemetry.addLine("+100");
            telemetry.addLine("+1000");
            telemetry.addLine("");

            telemetry.addLine("DATOS");
            telemetry.addData("Velocidad Real " , velocidadReal);
            telemetry.addData("Sumador Pulsos " , sumadorPulsos);
            telemetry.addData("Pulsos " , pulsos);
            telemetry.addData("Direccion Derecho " , direccionDerecho);

            telemetry.update();

            //SELECCION DE VELOCIDAD
            if (velocidadSeleccion >= 0 && velocidadSeleccion <= 2){
                if(gamepad1.dpad_right){
                    velocidadSeleccion += 1;
                    sleep(400);
                }
                velocidadSeleccion =(velocidadSeleccion < 0 || velocidadSeleccion > 2)? 0 :velocidadSeleccion;
            }

            switch(velocidadSeleccion){
                case 0:
                    velocidadReal = velocidadMinima;
                    break;
                case 1:
                    velocidadReal = velocidadMediana;
                    break;
                case 2:
                    velocidadReal = velocidadMaxima;
                    break;
            }

            //SELECCION DE SUMADOR DE PULSOS
            if (sumadorPulsos >= 10 && sumadorPulsos <= 1000){
                if(gamepad1.dpad_up){
                    sumadorPulsos = sumadorPulsos*10;
                    sleep(400);
                }
                sumadorPulsos =(sumadorPulsos < 10 || sumadorPulsos > 1000)? 10 :sumadorPulsos;
            }

            //SUMA DE PUNTOS
            if (gamepad1.right_bumper){
                pulsos += sumadorPulsos;
                sleep(400);
            }
            else if(gamepad1.left_bumper){
                pulsos -= sumadorPulsos;
                sleep(400);
            }

            //SELECCION DE ACCION
            if (accionSeleccion >= 0 && accionSeleccion <= 6){
                if(gamepad1.b){
                    accionSeleccion += 1;
                    sleep(400);
                }
                accionSeleccion =(accionSeleccion < 0 || accionSeleccion > 6)? 0 :accionSeleccion;
            }

            //SELECCION DE DIRECCION
                if(gamepad1.b) {
                    if (direccionDerecho) {
                        direccionDerecho = false;
                        sleep(400);
                    } else if (direccionDerecho == false) {
                        direccionDerecho = true;
                        sleep(400);
                    }

                    if (direccionDerecho)
                        robot.motor.setDirection(DcMotorSimple.Direction.FORWARD);
                    else {
                        robot.motor.setDirection(DcMotorSimple.Direction.REVERSE);
                    }
                }

            //ATSION
            if (gamepad1.x && gamepad1.y){
                moverseDistanciaMantener(velocidadReal , pulsos);
                sleep(3000);
            }



        }



    }
    public void moverseDistancia(double potencia , int distance){
        robot.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motor.setTargetPosition(distance);

        robot.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.motor.setPower(potencia);

        while(robot.motor.isBusy()){

        }

        pararMotores();

        robot.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void moverseDistanciaMantener(double potencia , int distance){
        robot.motor_2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motor_2.setTargetPosition(distance);

        robot.motor_2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.motor_2.setPower(potencia);

        while(robot.motor_2.isBusy()){

        }

    }

    public void moverseEnfrente(double potencia){
        robot.motor.setPower(potencia);
    }


    public void pararMotores(){
        robot.motor.setPower(0);
    }



}