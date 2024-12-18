package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "ytt")
public class ytt extends OpMode {
    DcMotor mot1;
    DcMotor mot2;
    DcMotor mot3;
    DcMotor mot4;
    DcMotor mot5;
    DcMotor mot6;
    DcMotor mot7;
    CRServo servo1;
    double ticks = 2786.2;
    double s1 = 1;


    @Override
    public void init() {
        telemetry.addData("initialization", "is too successful");
        telemetry.update();

        mot1 = hardwareMap.get(DcMotor.class, "mot1");      //   1    2 <<the wheels on the bus goes round and round
        mot2 = hardwareMap.get(DcMotor.class, "mot2");      //   3    4
        mot3 = hardwareMap.get(DcMotor.class, "mot3");
        mot4 = hardwareMap.get(DcMotor.class, "mot4");
        mot5 = hardwareMap.get(DcMotor.class, "mot5");
        mot6 = hardwareMap.get(DcMotor.class, "mot6");
        mot7 = hardwareMap.get(DcMotor.class, "mot7");
        servo1 = hardwareMap.get(CRServo.class, "servo1");

        mot1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mot2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mot3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mot4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mot5.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mot6.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mot7.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        mot3.setDirection(DcMotorSimple.Direction.REVERSE);
        mot1.setDirection(DcMotorSimple.Direction.REVERSE);


    }

    @Override
    public void loop() {

//meth
       /* float y =-gamepad1.left_stick_y;
        float x= gamepad1.left_stick_x;
        float rot=gamepad1.right_stick_x;
        double pi= Math.PI;
        double deg= 1*pi/4;
        double xD= (x*Math.sin(deg)- y * Math.cos(deg));
        double yD= (x*Math.cos(deg) + y* Math.sin(deg));
        telemetry.addData("X:", gamepad1.left_stick_x);
        telemetry.addData("Y:", gamepad1.left_stick_y);
        telemetry.addData("yval:",yD);
        telemetry.addData("xval:",xD);

//booga booga shit to control sensitivity
        if (xD > -s1 && xD < s1) {
        xD=0;
        }
        if (yD > -s1 && yD < s1) {
            yD=0;
        }


//rot as in brain rot
        if (gamepad1.y){
            mot1.setPower(1);
        }
        if (gamepad1.x){
            mot2.setPower(1);
        }
        if (gamepad1.a){
            mot3.setPower(1);
        }
        if (gamepad1.b){
            mot4.setPower(1);
        }

        else if (gamepad1.left_stick_x == 0) {

            mot1.setPower(rot);
            mot2.setPower(-rot);
            mot4.setPower(-rot);
            mot3.setPower(rot);
        }
        else{
            mot1.setPower(xD);
            mot2.setPower(yD);
            mot3.setPower(xD);
            mot4.setPower(yD);
        }

*/

//test motor
        if (gamepad1.b) {

            s1 = 2;

        }
        if (gamepad1.y) {

            s1 = 1;

        }
        if (gamepad1.left_bumper) {
            mot6.setPower(1);
        } else if (gamepad1.right_bumper) {
            mot6.setPower(-1);
        } else {
            mot6.setPower(0);
        }

        if(gamepad1.left_bumper){
            mot7.setPower(1);
        }else if (gamepad1.right_bumper){
            mot7.setPower(-1);
        }else{
            mot7.setPower(0);
        }


        double y = -gamepad1.left_stick_y; // Remember, Y stick is reversed! // For moving forward and backward.
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing. For strafing left and right.
        double rx = gamepad1.right_stick_x; // rx means turning
        boolean slow_mode_button = gamepad1.dpad_down; // For improve handling at the cost of speed.
        // Calculate the denominator to normalize the motor powers, ensuring it's at least 1 to avoid division by zero
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        double leftFrontPower = (y + x + rx) / denominator;
        double leftBackPower = (y - x + rx) / denominator;
        double rightFrontPower = (y - x - rx) / denominator;
        double rightBackPower = (y + x - rx) / denominator;


        mot1.setPower(leftFrontPower / s1);
        mot3.setPower(leftBackPower / s1);
        mot4.setPower(rightBackPower / s1);
        mot2.setPower(rightFrontPower / s1);

        telemetry.addData("BackLeftEncoder", mot3.getCurrentPosition());
        telemetry.addData("BackRightEncoder", mot4.getCurrentPosition());
        telemetry.addData("FrontLeftEncoder", mot1.getCurrentPosition());
        telemetry.addData("FrontRightEncoder", mot2.getCurrentPosition());
        telemetry.addData("Suppress", s1);
        telemetry.addData("Arm ", mot5.getCurrentPosition());
        telemetry.addData("Rotation motor for arm (Part 1)", mot6.getCurrentPosition());
        telemetry.addData("Rotation motor for arm (Part 2)", mot7.getCurrentPosition());

        if (gamepad1.a) {
            mot5.setPower(1);
        } else if (gamepad1.x) {
            mot5.setPower(-1);
        } else {
            mot5.setPower(0);
        }
    }

}






