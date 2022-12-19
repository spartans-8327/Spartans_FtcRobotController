package org.firstinspires.ftc.teamcode.calibracion;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.configuracion.RobotConfigMaster;
import org.firstinspires.ftc.teamcode.domain.Elevador;

//@Disabled
@TeleOp(name="Calibracion Giro", group="Pushbot")

public class CalibracionGiro extends LinearOpMode {

    RobotConfigMaster robot = new RobotConfigMaster();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap , telemetry);
        Elevador elevador = new Elevador(robot.motor, robot.motor_1, robot.servo, this);

        telemetry.update();
        int pulsos = 0;
        int sumadorPulsos = 10;
        int pulsosElegidos = 0;


        double potenciaIda = 0.4;
        double potenciaRegreso = 0.2;

        waitForStart();

        while (opModeIsActive()){

            telemetry.addLine("$$$ CALIBRACION GIRO $$$");
            telemetry.addLine("Usa este programa para calibrar el giro del robot");
            telemetry.addLine("");

            telemetry.addLine("*** CONTROLES ***");
            telemetry.addData("Sumar/Restar pulsos" , " Bumpers (G1)");
            telemetry.addData("Sumador de pulsos" , " Dpad Arriba (G1)");
            telemetry.addData("Probar pulsos", " A");
            telemetry.addData("Regresar a posicion inicial", " B");
            telemetry.addLine("");

            telemetry.addLine("### DATOS ###");
            telemetry.addData("Sumador Pulsos " , sumadorPulsos);
            telemetry.addData("Pulsos " , pulsos);
            telemetry.addLine("");

            telemetry.addLine("/// REGISTRO ///");
            telemetry.addData("Ultima prueba ", pulsosElegidos);
            telemetry.update();

            elevador.irMoverseCono(0.5);

            //SELECCION DE SUMADOR DE PULSOS
            if (sumadorPulsos >= 10 && sumadorPulsos <= 1000){
                if(gamepad1.dpad_up){
                    sumadorPulsos = sumadorPulsos*10;
                    sleep(200);
                }
                sumadorPulsos =(sumadorPulsos < 10 || sumadorPulsos > 1000)? 10 :sumadorPulsos;
            }

            //SUMA DE PUNTOS
            if (gamepad1.right_bumper){
                pulsos += sumadorPulsos;
                sleep(200);
            }
            else if(gamepad1.left_bumper){
                pulsos -= sumadorPulsos;
                sleep(200);
            }

            //ATSION
            if (gamepad1.a){
                moverseDistanciaMantener(potenciaIda , pulsos);
                pulsosElegidos = pulsos;
                sleep(3000);
            } else if (gamepad1.b){
                moverseDistanciaMantener(potenciaRegreso, -pulsos);
            }






        }



    }
    public void moverseDistancia(double potencia , int distance){
        robot.motor_1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motor_1.setTargetPosition(distance);

        robot.motor_1.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.motor_1.setPower(potencia);

        while(robot.motor_1.isBusy()){

        }

        pararMotor();

        robot.motor_1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void moverseDistanciaMantener(double potencia , int distance){
        robot.motor_1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motor_1.setTargetPosition(distance);

        robot.motor_1.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.motor_1.setPower(potencia);

        while(robot.motor_1.isBusy()){

        }

    }

    public void pararMotor(){
        robot.motor_1.setPower(0);
    }

}