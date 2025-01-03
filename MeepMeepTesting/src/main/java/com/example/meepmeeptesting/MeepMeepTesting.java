package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-8, 63, Math.PI/ 2))
                        .lineTo(new Vector2d(-8, 35 ))
                        .waitSeconds(2)
                        .lineTo(new Vector2d(-34, 37 ))
                        .lineTo(new Vector2d(-34, 10 ))
                        .lineTo(new Vector2d(-45, 10 ))
                        .lineTo(new Vector2d(-45, 55 ))//to box
                        .lineTo(new Vector2d(-45, 10 ))
                        .lineTo(new Vector2d(-52, 10 ))
                        .lineTo(new Vector2d(-52, 55 ))
                        .lineTo(new Vector2d(-52, 10 ))
                        .lineTo(new Vector2d(-60, 10 ))
                        .lineTo(new Vector2d(-60, 55 ))
                        .lineTo(new Vector2d(-60,40))

                        .lineTo(new Vector2d(-34, 60 ))





                                                .build());




        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
