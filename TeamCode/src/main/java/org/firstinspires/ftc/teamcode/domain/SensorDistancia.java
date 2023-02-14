package org.firstinspires.ftc.teamcode.domain;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class SensorDistancia {
    private DistanceSensor sensor_distancia;

    public SensorDistancia(DistanceSensor sensor_distancia){
        this.sensor_distancia = sensor_distancia;
    }

    public double obtenerCm(){
        return sensor_distancia.getDistance(DistanceUnit.CM);
    }

    public boolean junctionDetectado(){
        if (obtenerCm() > 0 && obtenerCm() < 35)
            return true;
        else
            return  false;
    }
}
