package org.firstinspires.ftc.teamcode.calibracion;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.teamcode.configuracion.RobotConfigMaster;
import org.firstinspires.ftc.teamcode.domain.Elevador;
import org.firstinspires.ftc.teamcode.test.calibracion.CalibracionConfig;

//@Disabled
@TeleOp(name="Calibracion Elevacion", group="Pushbot")

public class CalibracionElevacion extends LinearOpMode {

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

            telemetry.addLine("$$$ CALIBRACION ELEVACION $$$");
            telemetry.addLine("Usa este programa para calibrar la elevacion del robot");
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



            //SELECCION DE SUMADOR DE PULSOS
            if (sumadorPulsos >= 10 && sumadorPulsos <= 1000){
                if(gamepad1.dpad_up){
                    sumadorPulsos = sumadorPulsos*10;
                    sleep(300);
                }
                sumadorPulsos =(sumadorPulsos < 10 || sumadorPulsos > 1000)? 10 :sumadorPulsos;
            }

            //SUMA DE PUNTOS
            if (gamepad1.right_bumper){
                pulsos += sumadorPulsos;
                sleep(300);
            }
            else if(gamepad1.left_bumper){
                pulsos -= sumadorPulsos;
                sleep(300);
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
        robot.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motor.setTargetPosition(distance);

        robot.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.motor.setPower(potencia);

        while(robot.motor.isBusy()){

        }

        pararMotor();

        robot.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void moverseDistanciaMantener(double potencia , int distance){
        robot.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motor.setTargetPosition(distance);

        robot.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.motor.setPower(potencia);

        while(robot.motor.isBusy()){

        }

    }

    public void pararMotor(){
        robot.motor.setPower(0);
    }

}