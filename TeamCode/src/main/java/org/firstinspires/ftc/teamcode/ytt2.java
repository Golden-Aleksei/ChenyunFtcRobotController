package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "ytt2")
public class ytt2 extends OpMode {
    DcMotor mot1;
    DcMotor mot2;
    DcMotor mot3;
    DcMotor mot4;
    DcMotor mot6;
    DcMotor mot7;
    DcMotor vipmain;//viper
DcMotor vipside;


    CRServo servo1;//INTAKE
    Servo zservo;
    Servo xservo;

    //use with rmot
    double ticks = 2786.2;
    double newtarget;
    double s1 = 1;
    int pos = 0;
    int limit=666;// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    int viperpos;

    @Override

    public void init() {
        telemetry.addData("initialization", "is too successful");
        telemetry.update();

        mot1 = hardwareMap.get(DcMotor.class, "leftFront");      //   1    2 <<the wheels on the bus goes round and round
        mot2 = hardwareMap.get(DcMotor.class, "rightFront");      //   3    4
        mot3 = hardwareMap.get(DcMotor.class, "leftBack");
        mot4 = hardwareMap.get(DcMotor.class, "rightBack");
        vipmain = hardwareMap.get(DcMotor.class, "vipmain");
        mot6 = hardwareMap.get(DcMotor.class, "mot6");
        mot7 = hardwareMap.get(DcMotor.class, "mot7");
        vipside= hardwareMap.get(DcMotor.class,"vipside");


        servo1 = hardwareMap.get(CRServo.class, "servo1");
        zservo = hardwareMap.get(Servo.class, "zservo");
        xservo = hardwareMap.get(Servo.class, "xservo");

        mot1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mot2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mot3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mot4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mot6.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        vipmain.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        vipside.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        mot1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mot2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mot3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mot4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mot6.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mot7.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        vipside.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        vipmain.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        vipmain.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mot6.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        mot3.setDirection(DcMotorSimple.Direction.REVERSE);
        mot1.setDirection(DcMotorSimple.Direction.REVERSE);
        mot6.setDirection(DcMotorSimple.Direction.REVERSE);
        vipside.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override


    public void loop() {
        /*
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


        if (gamepad2.left_bumper) {
            servo1.setPower(1);
        } else if (gamepad2.right_bumper) {
            servo1.setPower(-1);
        } else {
            servo1.setPower(0);
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
        telemetry.addData("Slow mode: ", slow_mode_button);
        telemetry.addData("servox", xservo.getPosition());
        telemetry.addData("mot6", mot6.getCurrentPosition());
        telemetry.addData("mot7", mot7.getCurrentPosition());
        telemetry.addData("pos", pos);
        telemetry.addData("xservo", xservo.getPosition());
        telemetry.addData("vipermain", vipmain.getCurrentPosition());
        telemetry.addData("viperside", vipside.getCurrentPosition());
        telemetry.addData("viper2", viperpos);
        telemetry.update();


        //servo controls
        if (gamepad2.dpad_up) {
            zservo.setPosition(zservo.getPosition() - .01);
        } else if (gamepad2.dpad_down) {

            zservo.setPosition(zservo.getPosition() + .01);//tune this
        }

        if (gamepad2.x) {
            xservo.setPosition(.3); ///this might get changed to a servo servo

        }
        if (gamepad2.b) {
            xservo.setPosition(0.6);
        }



        if (gamepad2.left_trigger > 0) {
            if (viperpos < 0) {
                viperpos += 70;
            }
             // Increase target position
        } else if (gamepad2.right_trigger > 0) {
            if (viperpos > -2500) {
                viperpos -= 70;
            } // Decrease target position
            if (viperpos<-2500){
                viperpos=-2500;
            }
        }
        vipmain.setTargetPosition(viperpos);
        if(vipmain.getCurrentPosition()> viperpos-20 &&vipmain.getCurrentPosition()< viperpos+20){
            vipmain.setPower(0);
        }
        else if (vipmain.getCurrentPosition()> viperpos-50 &&vipmain.getCurrentPosition()< viperpos+50){
            vipmain.setPower(.1);
        }
        else if (vipmain.getCurrentPosition()> viperpos-100 &&vipmain.getCurrentPosition()< viperpos+100){
            vipmain.setPower(.2);
        }
        else if (vipmain.getCurrentPosition()> viperpos-200 &&vipmain.getCurrentPosition()< viperpos+200){
            vipmain.setPower(.5);
        }
        else{
            vipmain.setPower(1);
        }

        vipmain.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if(vipmain.getCurrentPosition()>viperpos) {
            vipside.setPower(-vipmain.getPower());
        }else if (vipmain.getCurrentPosition()<viperpos) {
            vipside.setPower(vipmain.getPower());
        }


        if (gamepad2.a){
            viperpos=-1600;
            vipmain.setTargetPosition(viperpos);
            mot6.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            vipmain.setPower(.5);
            zservo.setPosition(1);



        }






        if (gamepad2.right_stick_y > 0) {
            pos = pos + ((int) (gamepad2.right_stick_y * 7));
            if (pos > limit) {
                pos = limit;
            }

        }
        if (gamepad2.right_stick_y < 0) {
            pos = pos + ((int) (gamepad2.right_stick_y * 7));
            if (pos < 0) {

                pos = 0;
            }
        }
    mot6.setTargetPosition(pos);


        if(mot6.getCurrentPosition()> pos-10 &&mot6.getCurrentPosition()< pos+10){
            mot6.setPower(.1);
        }
        else if (mot6.getCurrentPosition()> pos-50 &&mot6.getCurrentPosition()< pos+50){
            mot6.setPower(.1);
        }
        else if (mot6.getCurrentPosition()> pos-100 &&mot6.getCurrentPosition()< pos+100){
            mot6.setPower(.2);
        }
        else if (mot6.getCurrentPosition()> pos-200 &&mot6.getCurrentPosition()< pos+200){
            mot6.setPower(.5);
        }
        else{
            mot6.setPower(1);
        }

        mot6.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if(mot6.getCurrentPosition()>pos) {
            mot7.setPower(-mot6.getPower());
        }else if (mot6.getCurrentPosition()<pos) {
            mot7.setPower(mot6.getPower());
        }



    }





}