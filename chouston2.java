/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
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
//@Disabled
@TeleOp(name = "Conmanbot: Old OpMode", group = "ElektraKatz")
public class chouston2 extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor leftMotor1 = null;
    DcMotor relicMotor = null;
    DcMotor clawMotor = null;
    DcMotor rightMotor1 = null;
    DcMotor leftMotor2 = null;
    DcMotor rightMotor2 = null;
    DcMotor upMotor = null;
    DcMotor slideMotor = null;
    public Servo leftClaw = null;
    public Servo relicClaw22 = null;
    public Servo rightClaw = null;
    public Servo relicClaw = null;
    public Servo jewelArm = null;
    //DigitalChannel liftLower;  // Hardware Device Object


    //    public Servo    frontClaw   = null;
    static double LEFT_CLAW_OPEN = .6;
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
   double dblLeftX = 0;
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
        // get a reference to our digitalTouch object.
       // liftLower = hardwareMap.get(DigitalChannel.class, "lift lower");


//        beaconHit   = hardwareMap.servo.get("actuator2");
//        frontClaw   = hardwareMap.servo.get("actuator3");

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
//            upMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
            // rightMotor1.setPower(-gamepad1.left_stick_y);
            // rightMotor2.setPower(-gamepad1.left_stick_y);
            // leftMotor1.setPower(-gamepad1.right_stick_y);
            //leftMotor2.setPower(-gamepad1.right_stick_y);
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//            if (gamepad2.left_stick_y < 0 && upMotor.getCurrentPosition() < 10000) {
//                upMotor.setPower(gamepad2.left_stick_y);
//            } else if (gamepad2.left_stick_y>=0) {
//                upMotor.setPower(gamepad2.left_stick_y);
//            }

//            upMotor.setPower(0);
//            upMotor.setPower(gamepad2.left_stick_y);
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            dblLeftY = gamepad1.left_stick_y;
            dblLeftX = gamepad1.left_stick_x;
            dblRightX = gamepad1.right_stick_x;

            dblRawLeftMotor1 = -dblLeftY + dblLeftX + dblRightX;
            dblRawLeftMotor2 = -dblLeftY - dblLeftX + dblRightX;
            dblRawRightMotor1 = -dblLeftY - dblLeftX - dblRightX;
            dblRawRightMotor2 = -dblLeftY + dblLeftX - dblRightX;

            dblLeftMotor1Power = Range.clip(dblRawLeftMotor1, -.70, .70);
            dblLeftMotor2Power = Range.clip(dblRawLeftMotor2, -.875, .875);
            dblRightMotor1Power = Range.clip(dblRawRightMotor1, -1.0, 1.0);
            dblRightMotor2Power = Range.clip(dblRawRightMotor2, -.70, .70);



            telemetry.addData("Mode", "running");
            telemetry.addData("stick", " LY=" + dblLeftY + " LX=" + dblLeftX + " RX=" + dblRightX);
            telemetry.addData("raw", " L1=" + dblRawLeftMotor1 + " L2=" + dblRawLeftMotor2 + " R1=" + dblRawRightMotor1 + " R2=" + dblRawRightMotor2);
            telemetry.addData("power", " L1=" + dblLeftMotor1Power + " L2=" + dblLeftMotor2Power + " R1=" + dblRightMotor1Power + " R2=" + dblRawRightMotor2);
            telemetry.update();

            leftMotor1.setPower(dblLeftMotor1Power);
            leftMotor2.setPower(dblLeftMotor2Power);
            rightMotor1.setPower(dblRightMotor1Power);
            rightMotor2.setPower(dblRightMotor2Power);
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`
            //Left
            /**
            if (-gamepad1.left_stick_x == 1) {
                leftMotor1.setPower(-1);
                leftMotor2.setPower(1);
                rightMotor1.setPower(1);
                rightMotor2.setPower(-1);
            }
            //right
            else if (gamepad1.left_stick_x == 1) {
                leftMotor1.setPower(1);
                leftMotor2.setPower(-1);
                rightMotor1.setPower(-1);
                rightMotor2.setPower(1);
            }
            //forward
            else if (-gamepad1.left_stick_y == 1) {
                leftMotor1.setPower(1);
                leftMotor2.setPower(1);
                rightMotor1.setPower(1);
                rightMotor2.setPower(1);
            }
            //backward
            else if (gamepad1.left_stick_y == 1) {
                leftMotor1.setPower(-1);
                leftMotor2.setPower(-1);
                rightMotor1.setPower(-1);
                rightMotor2.setPower(-1);
            }
            //turn xx
            if (-gamepad1.right_stick_x == 1) {
                leftMotor1.setPower(-1);
                leftMotor2.setPower(-1);
                rightMotor1.setPower(1);
                rightMotor2.setPower(1);
            }
            //turn xy
            else if (gamepad1.right_stick_x == 1) {
                leftMotor1.setPower(1);
                leftMotor2.setPower(1);
                rightMotor1.setPower(-1);
                rightMotor2.setPower(-1);
            }
            //auto  stop
            if (gamepad1.left_stick_y == 0 && gamepad1.left_stick_x == 0 && gamepad1.right_stick_x == 0) {
                leftMotor1.setPower(0);
                leftMotor2.setPower(0);
                rightMotor1.setPower(0);
                rightMotor2.setPower(0);
            }
             */
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
            if (gamepad2.y) {

                MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.lion);
                mediaPlayer.start();
            }
*/
            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`
           /* if ((gamepad2.left_stick_y < 0) && (upMotor.getCurrentPosition()<FULL)){
                upMotor.setPower(-gamepad2.left_stick_y);
            }

                else if ((gamepad2.left_stick_y >0)&&(!liftLower.getState())){
                upMotor.setPower(-gamepad2.left_stick_y);
            }
            else {
                upMotor.setPower(0);
            }

           */ //upMotor.setPower(-gamepad2.left_stick_y);
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            relicMotor.setPower(-gamepad2.right_stick_y);
            upMotor.setPower(-gamepad2.left_stick_y);



            if(gamepad2.dpad_up){
                clawMotor.setPower(.5);
            }else if (gamepad2.dpad_down){
                clawMotor.setPower(-.5);
            }else {
                clawMotor.setPower(0);
            }



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



            //slideMotor.setPower(gamepad2.right_stick_y);
            //omg~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/*
            if (!gamepad2.right_bumper) {
                oneShotd2 = false;
            }


            if (gamepad2.right_bumper && !oneShotd2) {

                oneShotd2 = true;
//                wait(10);
                telemetry.addData("Status:", "'gamepad2.right' pressed");
                telemetry.update();

                if (rightClaw.getPosition() == RIGHT_CLAW_OPEN) {
                    telemetry.addData("Status:", "l claw close");
                    rightClaw.setPosition(RIGHT_CLAW_CLOSE);

                } else if ((rightClaw.getPosition() == RIGHT_CLAW_CLOSE)) {
                    telemetry.addData("Status:", "l claw open");
                    rightClaw.setPosition(RIGHT_CLAW_OPEN);

                }

                sleep(10);

            }
            telemetry.update();
*/

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
                    //DIAGNAL~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    //NE
                    //if (gamepad1.left_stick_y == 1 && gamepad1.left_stick_x == 1) {
                    //  leftMotor1.setPower(1);
                    //leftMotor2.setPower(0);
                    //rightMotor1.setPower(0);
                    //rightMotor2.setPower(1);
                    //}
                    //NW
                    //if (gamepad1.left_stick_y == 1 && gamepad1.left_stick_x == -1) {
                    //leftMotor1.setPower(0);
                    //leftMotor2.setPower(1);
                    //rightMotor1.setPower(1);
                    //rightMotor2.setPower(0);
                    //}
                    //SW
                    //if (gamepad1.left_stick_y == -1 && gamepad1.left_stick_x == -1) {
                    //leftMotor1.setPower(-1);
                    //leftMotor2.setPower(0);
                    //rightMotor1.setPower(0);
                    //rightMotor2.setPower(-1);
                    //}
                    //SE
                    //if (gamepad1.left_stick_y == -1 && gamepad1.left_stick_x == 1) {
                    //leftMotor1.setPower(0);
                    //leftMotor2.setPower(-1);
                    //rightMotor1.setPower(-1);
                    //rightMotor2.setPower(0);
                    //}
                    //DIAGNAL~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    //turn xx



                    //stop x
                    //if (gamepad1.left_stick_x == 0) {
                    //    leftMotor1.setPower(0);
                    //    leftMotor2.setPower(0);
                    //    rightMotor1.setPower(0);
                    //    rightMotor2.setPower(0);
                    //}


// 1~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//            if (upMotor.getCurrentPosition() < 10000) {
//                oneShotd = false;
//            }
//            telemetry.addData("Path0", "Starting at %7d :%7d",
//                    upMotor.getCurrentPosition());
//            upMotor.getCurrentPosition();
//            telemetry.update();
//
//            if (upMotor.getCurrentPosition() < 10000) {
//                oneShotd = false;
//            }
//            sleep(20000);
//            upMotor.setPower(gamepad1.left_stick_y);
//            telemetry.update();
////
//            if (gamepad1.x && !oneShotd) {
//
//                oneShotd = true;
//                telemetry.addData("Status:", "'gamepad2.x' pressed");
//
//                if (upMotor.getCurrentPosition()<) {
//                    telemetry.addData("Status:", "Close Gripper");
//                    upMotor.setPower(0);
//                } else if ((upMotor.getCurrentPosition()>)) {
//                    telemetry.addData("Status:", "Open Gripper");
//                    rightMotor1.setPower(-gamepad1.left_stick_y);
//                }
//
////                wait(10);
//            }
//            telemetry.update();

//2x~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`

//            if (!gamepad1.x) {
//                oneShotd2 = false;
//            }
//
//            if (gamepad1.x && !oneShotd2) {
//
//                oneShotd2 = true;
//                telemetry.addData("Status:", "'gamepad2.x' pressed");
//
//                if (rightMotor2.equals(-gamepad1.left_stick_y)) {
//                    telemetry.addData("Status:", "Close Gripper");
//                    rightMotor2.setPower(-gamepad1.right_stick_y);
//                } else if ((rightMotor2.equals(-gamepad1.right_stick_y))) {
//                    telemetry.addData("Status:", "Open Gripper");
//                    rightMotor2.setPower(-gamepad1.left_stick_y);
//                }
//
////                wait(10);
//            }
//            telemetry.update();
//
//
//            if (!gamepad1.x) {
//                oneShotd3 = false;
//            }
//
//            if (gamepad1.x && !oneShotd3) {
//
//                oneShotd3 = true;
//                telemetry.addData("Status:", "'gamepad2.x' pressed");
//
//                if (leftMotor2.equals(-gamepad1.right_stick_y)) {
//                    telemetry.addData("Status:", "Close Gripper");
//                    leftMotor2.setPower(-gamepad1.left_stick_y);
//                } else if ((leftMotor2.equals(-gamepad1.left_stick_y))) {
//                    telemetry.addData("Status:", "Open Gripper");
//                    leftMotor2.setPower(-gamepad1.right_stick_y);
//                }
//
////                wait(10);
//            }
//            telemetry.update();
//
//            if (!gamepad1.x) {
//                oneShotd3 = false;
//            }
//
//            if (gamepad1.x && !oneShotd3) {
//
//                oneShotd3 = true;
//                telemetry.addData("Status:", "'gamepad2.x' pressed");
//
//                if (leftMotor1.equals(-gamepad1.right_stick_y)) {
//                    telemetry.addData("Status:", "Close Gripper");
//                    leftMotor1.setPower(-gamepad1.left_stick_y);
//                } else if ((leftMotor1.equals(-gamepad1.left_stick_y))) {
//                    telemetry.addData("Status:", "Open Gripper");
//                    leftMotor1.setPower(-gamepad1.right_stick_y);
//                }
//
////                wait(10);
//            }
//            telemetry.update();
//
//3~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



//                if (gamepad2.a && !oneShot) {
//
//                    oneShot = true;
//                    telemetry.addData("Status:", "'gamepad2.a' pressed");
//
//                    if (leftClaw.getPosition() == CLAW_OPEN) {
//                        telemetry.addData("Status:", "Close Gripper");
//                        leftClaw.setPosition(CLAW_CLOSE);
//                    } else if ((leftClaw.getPosition() == CLAW_CLOSE)) {
//                        telemetry.addData("Status:", "Open Gripper");
//                        leftClaw.setPosition(CLAW_OPEN);
//                    }
//
////                wait(10);
//                }
//      }


//            if (gamepad2.b && !oneShotb) {
//
//                oneShotb = true;
//                telemetry.addData("Status:", "'gamepad2.b' pressed");
//
//                if (beaconHit.getPosition() == CLAW_MID) {
//                    telemetry.addData("Status:", "Beacon Hit Raise");
//                    beaconHit.setPosition(CLAW_CLOSE);
//                } else if ((beaconHit.getPosition() == CLAW_CLOSE)) {
//                    telemetry.addData("Status:", "Hit Beacon");
//                    beaconHit.setPosition(CLAW_MID);
//                }
//
////                wait(10);
//            }
//            telemetry.update();
//4~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

                    //            if (!gamepad1.x) {
                    //                  oneShotd = false;
//                }

//                if (gamepad1.x && !oneShotd) {
//
                    //                  oneShotd = true;
//                    telemetry.addData("Status:", "controls have changed");

                    // rightMotor1.setPower(-gamepad1.left_stick_y);
                    // rightMotor2.setPower(-gamepad1.left_stick_y);
                    // leftMotor1.setPower(-gamepad1.right_stick_y);
                    // leftMotor2.setPower(-gamepad1.right_stick_y);
                    //}
                    // {

//                wait(10);
                    //}
                    // telemetry.update();

//5x~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//            if (!gamepad1.x) {
//                oneShotd = false;
//            }
//
//            if (gamepad1.b && !oneShotd2) {
//
//                oneShotd2 = true;
//                telemetry.addData("Status:", "controls have changed");
//
//                rightMotor1.setPower(gamepad1.left_stick_y);
//                rightMotor2.setPower(gamepad1.left_stick_y);
//                leftMotor1.setPower(gamepad1.right_stick_y);
//                leftMotor2.setPower(gamepad1.right_stick_y);
//            }
//            {
//
//  //              wait(10);
//            }

//            telemetry.update();
//
//            if (!gamepad1.y) {
//                oneShotc = false;
//            }
//

//            if (gamepad1.y && !oneShotc) {
//
//                oneShotc = true;
//                telemetry.addData("Status:", "'gamepad2.y' pressed");
//
//                if (rightClaw.getPosition() == CLAW_OPEN) {
//                    telemetry.addData("Status:", "Close Gripper");
//                    rightClaw.setPosition(CLAW_CLOSE);
//                } else if ((rightClaw.getPosition() == CLAW_CLOSE)) {
//                    telemetry.addData("Status:", "Open Gripper");
//                    rightClaw.setPosition(CLAW_OPEN);
//                }
//
////                wait(10);
//            }
//            telemetry.update();


















