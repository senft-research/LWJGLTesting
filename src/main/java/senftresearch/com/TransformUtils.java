package senftresearch.com;

import org.joml.Matrix4f;

public class TransformUtils {
    public static Matrix4f rotateY(float angle){
        return new Matrix4f().identity().rotateY(angle);
    }
    public static Matrix4f rotateX(float angle){
        return new Matrix4f().identity().rotateX(angle);
    }
    public static Matrix4f rotateZ(float angle){
        return new Matrix4f().identity().rotateZ(angle);
    }

}
