package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
@TeleOp


public class MecanumMovement extends LinearOpMode {
    //Variables
    boolean ClawEngaged = false;

    //Constants
    final double ServoSpeed = 1.0d;
    final long ButtonPause = 500; //Time in ms until button input is read again to avoid button jitter

    //Elevator Declarations
    private DcMotorSimple elevatorMotor = null;
    private CRServo ClawRightServo = null;
    private CRServo ClawLeftServo = null;

    //Drive Shaft declarations
    private DcMotor mbackl = null;
    private DcMotor mbackr = null;
    private DcMotor mforwardl = null;
    private DcMotor mforwardr = null;

    public void ServoClawInput(){

        if(gamepad1.a){
            ClawEngaged = !ClawEngaged;
            sleep(ButtonPause); //Avoid jittery button input
        }

        if(ClawEngaged){
            ClawRightServo.setPower(-ServoSpeed);
            ClawLeftServo.setPower(ServoSpeed);
        }
        if(!ClawEngaged){
            ClawRightServo.setPower(0);
            ClawLeftServo.setPower(0);
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

    public void Assignments(){
        //Drive Shaft Motor Assignment//
        mbackl = hardwareMap.dcMotor.get("mbackleft");
        mbackr = hardwareMap.dcMotor.get("mbackright");
        mforwardl = hardwareMap.dcMotor.get("mforwardleft");
        mforwardr = hardwareMap.dcMotor.get("mforwardright");

        //Elevator Assignment//
        elevatorMotor = hardwareMap.get(DcMotorSimple.class, "elevatorcontroller");
        ClawRightServo = hardwareMap.get(CRServo.class, "clawrightservo");
        ClawLeftServo = hardwareMap.get(CRServo.class, "clawleftservo");
    }
    @Override

    public void runOpMode() {

        Assignments();

        //Setup
        mforwardl.setDirection(DcMotor.Direction.REVERSE);//Reverse it I don't know how it works it just does
        mbackl.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {

                DriveShaftInput();
                ElevatorInput();
                ServoClawInput();

                //Telemetry//
                telemetry.addData("Claw Enabled?", ClawEngaged);
                telemetry.addData("Elevator Speed:", elevatorMotor.getPower());

                telemetry.update();
            }
        }


    }
}

