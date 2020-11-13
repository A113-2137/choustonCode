
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains the TeleOp code for FTC 11230 ElektraKatz for Velocity Vortex competition
 * The robot uses the TileRunner BD base with team built accessories attached to it.
 * The drive system consists of four AndyMark NeveRest 60 motors (2 left, 2 right)
 * The lift is driven by 1 AndyMark NeveRest 40 motor
 *
 * The motors are defined as follows:
 * leftMotor1   = "left motor1"
 * leftMotor2   = "left motor2"
 * rightMotor1  = "right motor1"
 * rightMotor2  = "right motor2"
 * upMotor      = "up motor"
 * <p>
 * This OpMode started with the FIRST example "TemplateOpMode_Linear"
 * Team member Conlan Houston modified the Template code to create this TeleOp OpMode
 *
 */

//Identifies the code as TeleOp and gives it a name and group *will only be visible in drive station*
@TeleOp(name = "Conmanbot: Old OpMode", group = "ElektraKatz")
public class chouston2 extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor leftMotor1 = null; //Creates all motors \/
    DcMotor relicMotor = null;
    DcMotor clawMotor = null;
    DcMotor rightMotor1 = null;
    DcMotor leftMotor2 = null;
    DcMotor rightMotor2 = null;
    DcMotor upMotor = null;
    DcMotor slideMotor = null;
    public Servo leftClaw = null; //creates all Servos \/
    public Servo relicClaw22 = null;
    public Servo rightClaw = null;
    public Servo relicClaw = null;
    public Servo jewelArm = null;
    //DigitalChannel liftLower;  // Hardware Device Object


    //    public Servo    frontClaw   = null;
    static double LEFT_CLAW_OPEN = .6; //Creates positions for the servos \/
    static double LEFT_CLAW_PARK = 1;
    static double RIGHT_CLAW_OPEN = .6;
    static double LEFT_CLAW_CLOSE = .8;
    static double RIGHT_CLAW_CLOSE = .3;
    static double RIGHT_CLAW_PARK = 0;
    static double JEWEL_ARM_UP = .3;
    static double JEWEL_ARM_DOWN = .7;
   // static double FULL = 1;

    static double RELIC_CLAW_CLOSE = .75;
    static double RELIC_CLAW_OPEN = .5;


    // static double CLAW_MID = 0.2;
   // boolean oneShot = false;
   double dblLeftX = 0; //Only for strafing. if not srafing, use tank drive \/
    double dblLeftY = 0;
    double dblRightX = 0;
    double dblRawLeftMotor1;
    double dblRawLeftMotor2;
    double dblRawRightMotor1;
    double dblRawRightMotor2;
    double dblLeftMotor1Power;
    double dblLeftMotor2Power;
    double dblRightMotor1Power;
    double dblRightMotor2Power;
    boolean oneShotb = false;
    //    boolean         oneShotc     = false;
    boolean oneShotd = false;
    boolean oneShot = false;
    boolean oneShotY = false;
    boolean         oneShotx     = false;
//    boolean         oneShotd3     = false;
//    boolean         oneShotd4     = false;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * st   ep (using the FTC Robot Controller app on the phone).
         */
        leftMotor1 = hardwareMap.dcMotor.get("left motor1");
        clawMotor = hardwareMap.dcMotor.get("claw motor");
        leftMotor2 = hardwareMap.dcMotor.get("left motor2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor2");
        upMotor = hardwareMap.dcMotor.get("up motor");
        relicMotor = hardwareMap.dcMotor.get("relic motor");
        //slideMotor = hardwareMap.dcMotor.get("slide motor");
        leftClaw = hardwareMap.servo.get("left claw");
        rightClaw = hardwareMap.servo.get("right claw");
        relicClaw = hardwareMap.servo.get("relic claw");
        jewelArm     = hardwareMap.servo.get("jewel arm");

        // eg: Set the drive motor directions:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        leftMotor1.setDirection(DcMotor.Direction.FORWARD);
        leftMotor2.setDirection(DcMotor.Direction.FORWARD);// Set to REVERSE if using AndyMark motors
        upMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor1.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        relicMotor.setDirection(DcMotor.Direction.FORWARD);
        leftClaw.setPosition(LEFT_CLAW_PARK);//Initiate left claw to 0 position
        rightClaw.setPosition(RIGHT_CLAW_PARK);
        relicClaw.setPosition(RELIC_CLAW_CLOSE);
        jewelArm.setPosition(JEWEL_ARM_UP);
        // set the digital channel to input.
       // liftLower.setMode(DigitalChannel.Mode.INPUT);

//        upMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            //sets variables to the values of control stick axis'
            dblLeftY = gamepad1.left_stick_y;
            dblLeftX = gamepad1.left_stick_x;
            dblRightX = gamepad1.right_stick_x;

            //sets up drive with mechanum wheels
            dblRawLeftMotor1 = -dblLeftY + dblLeftX + dblRightX;
            dblRawLeftMotor2 = -dblLeftY - dblLeftX + dblRightX;
            dblRawRightMotor1 = -dblLeftY - dblLeftX - dblRightX;
            dblRawRightMotor2 = -dblLeftY + dblLeftX - dblRightX;

            dblLeftMotor1Power = Range.clip(dblRawLeftMotor1, -.70, .70);
            dblLeftMotor2Power = Range.clip(dblRawLeftMotor2, -.875, .875);
            dblRightMotor1Power = Range.clip(dblRawRightMotor1, -1.0, 1.0);
            dblRightMotor2Power = Range.clip(dblRawRightMotor2, -.70, .70);


            //prints status of various variables
            telemetry.addData("Mode", "running");
            telemetry.addData("stick", " LY=" + dblLeftY + " LX=" + dblLeftX + " RX=" + dblRightX);
            telemetry.addData("raw", " L1=" + dblRawLeftMotor1 + " L2=" + dblRawLeftMotor2 + " R1=" + dblRawRightMotor1 + " R2=" + dblRawRightMotor2);
            telemetry.addData("power", " L1=" + dblLeftMotor1Power + " L2=" + dblLeftMotor2Power + " R1=" + dblRightMotor1Power + " R2=" + dblRawRightMotor2);
            telemetry.update();

            //sets the motors to follow the values of certain variables
            leftMotor1.setPower(dblLeftMotor1Power);
            leftMotor2.setPower(dblLeftMotor2Power);
            rightMotor1.setPower(dblRightMotor1Power);
            rightMotor2.setPower(dblRightMotor2Power);

            relicMotor.setPower(-gamepad2.right_stick_y);
            upMotor.setPower(-gamepad2.left_stick_y);


            //lets the d-pad on the second controller controll the claw motor
            if(gamepad2.dpad_up){
                clawMotor.setPower(.5);
            }else if (gamepad2.dpad_down){
                clawMotor.setPower(-.5);
            }else {
                clawMotor.setPower(0);
            }


            //allows the 'a' button on controller 2 to toggle the relic claw servos' positions
            if (!gamepad2.a) {
                oneShot = false;
            }


            if (gamepad2.a && !oneShot) {

                oneShot = true;
//                wait(10);
                telemetry.addData("Status:", "'gamepad2.right' pressed");
                telemetry.update();

                if (relicClaw.getPosition() == RELIC_CLAW_OPEN) {
                    telemetry.addData("Status:", "l claw close");
                    relicClaw.setPosition(RELIC_CLAW_CLOSE);

                } else if ((relicClaw.getPosition() == RELIC_CLAW_CLOSE)) {
                    telemetry.addData("Status:", "l claw open");
                    relicClaw.setPosition(RELIC_CLAW_OPEN);

                }
            }
              
            //allows the 'y' button on controller 2 to toggle the jewel arm motor positions
            if (!gamepad2.y) {
                oneShotY = false;
            }

            if (gamepad2.y && !oneShotY) {

                oneShotY = true;
//                wait(10);
                telemetry.addData("Status:", "'gamepad2.Y' pressed");
                telemetry.update();

                if (relicClaw.getPosition() == JEWEL_ARM_DOWN) {
                    telemetry.addData("Status:", "jewelArm up");
                    jewelArm.setPosition(JEWEL_ARM_UP);

                } else if ((relicClaw.getPosition() == JEWEL_ARM_UP)) {
                    telemetry.addData("Status:", "jewArm up");
                    jewelArm.setPosition(JEWEL_ARM_DOWN);

                }

                sleep(10);

            }
            telemetry.update();

            //allows the left bumper on controller 2 to toggle the left and right claw servos' positions
            if (!gamepad2.left_bumper) {
                oneShotd = false;
            }


            if (gamepad2.left_bumper && !oneShotd) {

                oneShotd = true;
//                wait(10);
                telemetry.addData("Status:", "'gamepad2.right' pressed");
                telemetry.update();

                if (leftClaw.getPosition() == LEFT_CLAW_OPEN ) {
                    telemetry.addData("Status:", "l & r claw close");
                    leftClaw.setPosition(LEFT_CLAW_CLOSE);
                    rightClaw.setPosition(RIGHT_CLAW_CLOSE);

                } else if ((leftClaw.getPosition() == LEFT_CLAW_CLOSE || leftClaw.getPosition() == LEFT_CLAW_PARK)) {
                    telemetry.addData("Status:", "l claw open");
                    leftClaw.setPosition(LEFT_CLAW_OPEN);
                    rightClaw.setPosition(RIGHT_CLAW_OPEN);

                }

               sleep(10);

            }
            telemetry.update();
        }
    }
}