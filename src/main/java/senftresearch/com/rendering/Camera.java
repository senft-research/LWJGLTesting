package senftresearch.com.rendering;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Represents the positioning of the view space of the monitor from the context of a "Camera" object viewing the scene.
 */
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
    private float cameraSpeed = 0.05f;

    private Matrix4f projectionMatrix;

    /**
     * Constructor that sets up the cursor and keypress callbacks, influencing the various matrices of the Camera view.
     * @param window The OpenGL Window Instance.
     */
    public Camera(Window window){
        long windowId = window.getWindowId();

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

        glfwSetKeyCallback(windowId, (windowHandle, key, scancode, action, mods ) -> {


            Vector3f cameraRight = initCameraRight();
            switch (key){
                case GLFW_KEY_W:
                    this.cameraPosition.add(new Vector3f(cameraFront).normalize().mul(cameraSpeed));
                    break;

                case GLFW_KEY_S:
                    this.cameraPosition.sub(
                            new Vector3f(cameraFront).normalize().mul(cameraSpeed)
                    );
                    break;
                case GLFW_KEY_D:
                    this.cameraPosition.add(
                            new Vector3f(cameraRight).mul(cameraSpeed)
                    );
                    break;
                case GLFW_KEY_A:
                    this.cameraPosition.sub(
                            new Vector3f(cameraRight).mul(cameraSpeed)
                    );
                    break;
            }
        });
        this.projectionMatrix = new Matrix4f()
                .perspective(
                        (float) Math.toRadians(45.0f),
                        (float) window.getHeight() / window.getWidth(),
                        0.1f,
                        100.0f
                );
    }
    public Matrix4f getViewMatrix() {

        Vector3f targetVector = new Vector3f(cameraPosition).add(cameraFront);

        return new Matrix4f().lookAt(
                cameraPosition,
                targetVector,
                cameraUp
        );
    }

    /**
     * Gets the projection matrix that represents the projection perspective of the camera's view lense.
     * @return The camera's projection matrix.
     */
    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    /**
     * Runs the various key checks that may influence the camera's direction and position per loop.
     * @param window The window where the camera resides.
     */
    public void loopLogic(Window window){

        if (glfwGetKey(window.getWindowId(), GLFW_KEY_W) == GLFW_PRESS) {
            this.cameraPosition.add(
                    new Vector3f(cameraFront).normalize().mul(cameraSpeed)
            );
        }


        if (glfwGetKey(window.getWindowId(), GLFW_KEY_S) == GLFW_PRESS) {
            this.cameraPosition.sub(
                    new Vector3f(cameraFront).normalize().mul(cameraSpeed)
            );
        }

        Vector3f cameraRight = new Vector3f();
        cameraFront.cross(cameraUp, cameraRight).normalize();

        if (glfwGetKey(window.getWindowId(), GLFW_KEY_D) == GLFW_PRESS) {
            this.cameraPosition.add(
                    new Vector3f(cameraRight).mul(cameraSpeed)
            );
        }

        if (glfwGetKey(window.getWindowId(), GLFW_KEY_A) == GLFW_PRESS) {
            this.cameraPosition.sub(
                    new Vector3f(cameraRight).mul(cameraSpeed)
            );
        }

        if(glfwGetKey(window.getWindowId(), GLFW_KEY_ESCAPE) == GLFW_PRESS){
            glfwSetWindowShouldClose(window.getWindowId(), true);
        }
    }

    /**
     * Updates the direction of the camera in terms of rotation, based on mouse inputs effecting pitch, roll and yaw.
     */
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

    /**
     * Inits the camera right vector as a matrix.
     * @return The camera's right vector.
     */
    private Vector3f initCameraRight(){
        Vector3f cameraRight = new Vector3f();
        cameraFront.cross(cameraUp, cameraRight).normalize();
        return cameraRight;
    }


}