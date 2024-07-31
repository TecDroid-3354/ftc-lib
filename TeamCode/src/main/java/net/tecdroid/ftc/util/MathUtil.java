package net.tecdroid.ftc.util;

import org.joml.Matrix2d;
import org.joml.Vector2d;

public class MathUtil {

    // For the curious: https://en.wikipedia.org/wiki/Rotation_matrix
    public static Vector2d rotateVector(Vector2d vector, double angleRadians) {
        Vector2d result = new Vector2d(vector);
        result.mul(new Matrix2d(
                Math.cos(angleRadians), -Math.sin(angleRadians),
                Math.sin(angleRadians), Math.cos(angleRadians)
        ));

        return result;
    }
}
