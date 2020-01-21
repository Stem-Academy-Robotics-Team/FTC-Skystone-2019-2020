package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous


public class AutoModeRedAlliance extends LinearOpMode {

    //Variables
    boolean ClawEngaged = false;

    //Constants
    final double ServoSpeed = 1.0d;
    final long ButtonPause = 500; //Time in ms until button input is read again to avoid button jitter
    //Auto
    ColorSensor colorSensor = null;
    //Elevator Declarations
    private DcMotorSimple elevatorMotor = null;
    private CRServo ClawRightServo = null;
    private CRServo ClawLeftServo = null;

    //Drive Shaft declarations
    private DcMotor mbackl = null;
    private DcMotor mbackr = null;
    private DcMotor mforwardl = null;
    private DcMotor mforwardr = null;



    //Autonomous Commands
    public void MoveForwardAUTO(long timeMS){
        mbackr.setPower(1);
        mbackl.setPower(1);
        mforwardr.setPower(1);
        mforwardl.setPower(1);
        sleep(timeMS);
    }
    public void MoveBackAUTO(long timeMS){
        mbackr.setPower(-1);
        mbackl.setPower(-1);
        mforwardr.setPower(-1);
        mforwardl.setPower(-1);
        sleep(timeMS);
    }
    public void StrafeRightAUTIO(long timeMS){
        mbackr.setPower(1);
        mbackl.setPower(-1);
        mforwardr.setPower(-1);
        mforwardl.setPower(1);
        sleep(timeMS);
    }
    public void StrafeLeftAuto(long timeMS){
        mbackr.setPower(-1);
        mbackl.setPower(1);
        mforwardr.setPower(1);
        mforwardl.setPower(-1);
        sleep(timeMS);
    }
    public void CheckForRed(){
        int x = colorSensor.red();
        if(x > 000 && x < 000){
            //DoSomething
        }
    }
    public void Assignments(){

        //Drive Shaft Motor Assignment//
        mbackl = hardwareMap.dcMotor.get("mbackleft");
        mbackr = hardwareMap.dcMotor.get("mbackright");
        mforwardl = hardwareMap.dcMotor.get("mforwardleft");
        mforwardr = hardwareMap.dcMotor.get("mforwardright");

        //Sensors
        colorSensor = hardwareMap.get(ColorSensor.class, "colorsensor");

        //Elevator Assignment//
        elevatorMotor = hardwareMap.get(DcMotorSimple.class, "elevatorcontroller");
        ClawRightServo = hardwareMap.get(CRServo.class, "clawrightservo");
        ClawLeftServo = hardwareMap.get(CRServo.class, "clawleftservo");
    }

    @Override
    public void runOpMode(){
        Assignments();

        //Setup
        mforwardl.setDirection(DcMotor.Direction.REVERSE);//Reverse it I don't know how it works it just does
        mbackl.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive()){
            MoveForwardAUTO(1000);
            sleep(1000);
            MoveBackAUTO(1000);
            sleep(1000);
            StrafeLeftAuto(1000);
            sleep(1000);
            StrafeRightAUTIO(1000);
            sleep(1000);
            telemetry.update();
        }

    }

}
