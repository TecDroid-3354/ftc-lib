package net.tecdroid.ftc.util;

import org.joml.Matrix2d;
import org.joml.Vector2d;

import lombok.experimental.UtilityClass;
import lombok.val;

@UtilityClass
public class MathUtil {

    // For the curious: https://en.wikipedia.org/wiki/Rotation_matrix
    public Vector2d rotateVector(Vector2d vector, double angleRadians) {
        return new Vector2d(vector).mul(new Matrix2d(
                Math.cos(angleRadians), -Math.sin(angleRadians),
                Math.sin(angleRadians), Math.cos(angleRadians)
        ));
    }
}
