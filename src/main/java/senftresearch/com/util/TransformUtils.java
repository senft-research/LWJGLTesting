package senftresearch.com.util;

import org.joml.Matrix4f;

/**
 * Util methods for providing `Matrix4f` instances that represent various types of transformations (such as rotation).
 */
public class TransformUtils {

    /**
     * Returns a matrix that can be used to rotate the vertices of a mesh around the x-axis.
     * @param angle The angle of rotation (in radians)
     * @return The rotation matrix.
     */
    public static Matrix4f rotateX(float angle){
        return new Matrix4f().identity().rotateX(angle);
    }

    /**
     * Returns a matrix that can be used to rotate the vertices of a mesh around the y-axis.
     * @param angle The angle of rotation (in radians)
     * @return The rotation matrix.
     */
    public static Matrix4f rotateY(float angle){
        return new Matrix4f().identity().rotateY(angle);
    }

    /**
     * Returns a matrix that can be used to rotate the vertices of a mesh around the z-axis.
     * @param angle The angle of rotation (in radians)
     * @return The rotation matrix.
     */
    public static Matrix4f rotateZ(float angle){
        return new Matrix4f().identity().rotateZ(angle);
    }

}
