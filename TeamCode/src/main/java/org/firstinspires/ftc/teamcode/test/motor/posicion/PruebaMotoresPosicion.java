package org.firstinspires.ftc.teamcode.test.motor.posicion;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.test.motor.sinencoder.PruebaMotorSinEncoderConfig;

@Autonomous(name="PruebaMotoresPosicion", group="Pushbot")
//@Disabled
public class PruebaMotoresPosicion extends LinearOpMode {
    PruebaMotorSinEncoderConfig robot = new PruebaMotorSinEncoderConfig();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap , telemetry);
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        while(opModeIsActive()){
            double stickY = -gamepad2.left_stick_y;
            telemetry.update();

            if (stickY == 1){
                usingEncoder();
                robot.motor.setPower(1);
                telemetry.addLine("Arriba");
            } else if (stickY == -1) {
                usingEncoder();
                robot.motor.setPower(-1);
                telemetry.addLine("Abajo");
            } else {
                mantenerse();
                telemetry.addLine("Manteniendo");
            }


        }
    }

    public void usingEncoder(){
        robot.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void resetearEncoder(){
        robot.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setTarget(int distancia){
        robot.motor.setTargetPosition(distancia);
    }

    public void setRunToPosition(){
        robot.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void activarMotor(int potencia){
        robot.motor.setPower(potencia);
    }

    public void mantenerse (){

        robot.motor.setTargetPosition(robot.motor.getCurrentPosition());

        robot.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.motor.setPower(1);

    }

    public void moverseDistanciaMantener(double potencia , int distance){
        robot.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        telemetry.addLine("Reseteando Encoders");
        telemetry.update();

        robot.motor.setTargetPosition(distance);
        telemetry.addLine("Set target position");
        telemetry.update();

        robot.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        telemetry.addLine("Run to position");
        telemetry.update();

        moverseEnfrente(potencia);
        telemetry.addLine("Moverse Enfrente");
        telemetry.update();

        while(robot.motor.isBusy()){
            telemetry.addLine("Dentro del Busy");
            telemetry.update();
        }

    }

    public void moverseEnfrente(double potencia){
        robot.motor.setPower(potencia);
    }

    public void pararMotores(){
        robot.motor.setPower(0);
    }

}
