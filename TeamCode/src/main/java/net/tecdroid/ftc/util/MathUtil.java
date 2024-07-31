package net.tecdroid.ftc.util;

import org.joml.Matrix2d;
import org.joml.Vector2d;

public class MathUtil {

    public static boolean isInsideRange(double value, double lo, double hi) {
        return value > lo && value < hi;
    }

    public static boolean isInsideRange(int value, int lo, int hi) {
        return value > lo && value < hi;
    }

    // For the curious: https://en.wikipedia.org/wiki/Rotation_matrix
    public static Vector2d rotateVector(Vector2d vector, double angleRadians) {
        return vector.mul(new Matrix2d(
                Math.cos(angleRadians), -Math.sin(angleRadians),
                Math.sin(angleRadians), Math.cos(angleRadians)
        ), new Vector2d());
    }

    /** @noinspection SuspiciousNameCombination*/
    public static Vector2d swapVectorComponents(Vector2d vector) {
        return new Vector2d(vector.y, vector.x);
    }
}
