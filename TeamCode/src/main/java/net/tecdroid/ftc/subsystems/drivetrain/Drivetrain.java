package net.tecdroid.ftc.drivetrain;

import org.joml.Vector2d;


/**
 * Represents the basis for any arbitrary drivetrain
 */
public interface Drivetrain {

    /**
     * Causes the drivetrain to move with the desired velocities
     * @param linearVelocity The linear velocity , where +x faces the front side of the robot and +y faces the left side of the robot
     * @param angularVelocity The angular velocity, where +rx turns towards the left
     */
    public abstract void drive(Vector2d linearVelocity, double angularVelocity);

}
