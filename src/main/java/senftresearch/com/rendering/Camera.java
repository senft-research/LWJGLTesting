package senftresearch.com.rendering;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;

public class Camera {

    private final Vector3f cameraPosition = new Vector3f(0.0f, 0.0f, 3.0f);
    private final Vector3f cameraFront = new Vector3f(0.0f, 0.0f, -1.0f);
    private final Vector3f cameraUp = new Vector3f(0.0f, 1.0f, 0.0f);

    private float yaw = -90.0f;
    private float pitch = 0.0f;
    private boolean firstMouse = true;
    private float previousXPos = 0;
    private float previousYPos = 0;
    private float sensitivity = 0.1f;

    public Camera(long windowId){
        glfwSetCursorPosCallback(windowId, (windowHandle, xpos, ypos) -> {
            float xPos = (float) xpos;
            float yPos = (float) ypos;

            if (firstMouse) {
                previousXPos = xPos;
                previousYPos = yPos;
                firstMouse = false;
            }

            float xOffset = xPos - previousXPos;
            float yOffset = previousYPos - yPos;
            xOffset *= sensitivity;
            yOffset *= sensitivity;

            yaw += xOffset;
            pitch += yOffset;

            if(pitch > 89.0f){
                pitch = 89.0f;
            }

            if(pitch < - 89.0f){
                pitch = - 89.0f;
            }
            updateCameraVectors();

            previousXPos = xPos;
            previousYPos = yPos;
         });
    }
    public Matrix4f getViewMatrix() {

        Vector3f targetVector = new Vector3f(cameraPosition).add(cameraFront);

        return new Matrix4f().lookAt(
                cameraPosition,
                targetVector,
                cameraUp
        );
    }

    public Vector3f getCameraPosition() {
        return cameraPosition;
    }

    public Vector3f getCameraFront() {
        return cameraFront;
    }

    public Vector3f getCameraUp(){
        return cameraUp;
    }

    private void updateCameraVectors(){
        Vector3f direction = new Vector3f();

        direction.x = (float)(
                Math.cos(Math.toRadians(yaw)) *
                        Math.cos(Math.toRadians(pitch))
        );

        direction.y = (float)(
                Math.sin(Math.toRadians(pitch))
        );

        direction.z = (float)(
                Math.sin(Math.toRadians(yaw)) *
                        Math.cos(Math.toRadians(pitch))
        );

        cameraFront.set(direction.normalize());

    }




}