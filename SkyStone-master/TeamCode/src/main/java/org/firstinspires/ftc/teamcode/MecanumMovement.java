package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp


public class MecanumMovement extends LinearOpMode {


    //Elevator Declarations
    private DcMotorSimple elevatorMotor = null;
    private Servo ClawRightServo;
    private Servo ClawLeftServo;

    //Drive Shaft Motor variable decleraitons
    private DcMotor mbackl;
    private DcMotor mbackr;
    private DcMotor mforwardl;
    private DcMotor mforwardr;

    public void ServoClawInput(){
        if(gamepad1.left_bumper){
            //ClawRightServo.setPosition(TODO)
            //ClawLeftServo.setPosition(TODO)
        }
        if(gamepad1.right_bumper){
            //ClawRightServo.setPosition(TODO)
            //ClawLeftServo.setPosition(TODO)
        }
        else{
            //Do nothing: Leave in previous state
        }
    }

    public void DriveShaftInput(){
        float x = -gamepad1.right_stick_x;
        float y = -gamepad1.right_stick_y;
        float left = -gamepad1.left_stick_x;

        mbackr.setPower((y-x)+left);
        mbackl.setPower((y+x)-left);
        mforwardr.setPower((y+x)+left);
        mforwardl.setPower((y-x)-left);
    }


    public void ElevatorInput(){
        if(gamepad1.left_trigger > 0.2){
            elevatorMotor.setPower(-gamepad1.left_trigger);
        }
        if(gamepad1.right_trigger > 0.2){
            elevatorMotor.setPower(gamepad1.right_trigger);
        }
        if(gamepad1.right_trigger > 0.2 && gamepad1.left_trigger > 0.2){
            elevatorMotor.setPower(0);
        }
        else{
            elevatorMotor.setPower(0);
        }
    }

    @Override

    public void runOpMode() {

        //Drive Shaft Motor Assignment//
        mbackl = hardwareMap.dcMotor.get("mbackleft");
        mbackr = hardwareMap.dcMotor.get("mbackright");
        mforwardl = hardwareMap.dcMotor.get("mforwardleft");
        mforwardr = hardwareMap.dcMotor.get("mforwardright");

        //Elevator Assignment//
        elevatorMotor = hardwareMap.get(DcMotorSimple.class, "elevatorcontroller");
        //ClawRightServo = hardwareMap.servo.get("TODO");
        //ClawLeftServo = hardwareMap.servo.get("TODO");

        //Setup
        mforwardl.setDirection(DcMotor.Direction.REVERSE);//Reverse it I don't know how it works it just does


        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {

                DriveShaftInput();
                ElevatorInput();
                telemetry.update();
            }
        }


    }
}

