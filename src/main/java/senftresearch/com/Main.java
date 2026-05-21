package senftresearch.com;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import senftresearch.com.rendering.Mesh;
import senftresearch.com.rendering.Renderer;
import senftresearch.com.rendering.Shader;
import senftresearch.com.rendering.Window;
import senftresearch.com.util.TransformUtils;

import static org.lwjgl.glfw.GLFW.*;

public class Main {

    // The meshes are split into 3 segments (first 3 are position, second 3 are colour, final 2 are texture coords)
    float[] vertices = {
            0.5f,  0.5f, 0.0f,   1.0f, 0.0f, 0.0f,   1.0f, 1.0f,   // top right
            0.5f, -0.5f, 0.0f,   0.0f, 1.0f, 0.0f,   1.0f, 0.0f,   // bottom right
            -0.5f, -0.5f, 0.0f,   0.0f, 0.0f, 1.0f,   0.0f, 0.0f,   // bottom left
            -0.5f,  0.5f, 0.0f,   1.0f, 1.0f, 0.0f,   0.0f, 1.0f    // top left
    };

    // Indices for the element buffer. this tells the mesh which of the coordinates to use to render which triangles
    // so the value represents the "vertex" to use, e.g: 0 represents the `0.5f, 0.5f, 0.0f` vertex.
    int[] indices = {
            0, 1, 3,
            1, 2, 3
    };

    float[] cubeVertices = {
            // Each of these sets of vertices represent a different face of the cube, with EBOs not being used.

            // back face
            -0.5f, -0.5f, -0.5f,  1.0f, 0.0f, 0.0f,   0.0f, 0.0f,
            0.5f, -0.5f, -0.5f,  0.0f, 1.0f, 0.0f,   1.0f, 0.0f,
            0.5f,  0.5f, -0.5f,  0.0f, 0.0f, 1.0f,   1.0f, 1.0f,
            0.5f,  0.5f, -0.5f,  0.0f, 0.0f, 1.0f,   1.0f, 1.0f,
            -0.5f,  0.5f, -0.5f,  1.0f, 1.0f, 0.0f,   0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  1.0f, 0.0f, 0.0f,   0.0f, 0.0f,

            // front face
            -0.5f, -0.5f,  0.5f,  1.0f, 0.0f, 1.0f,   0.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  0.0f, 1.0f, 1.0f,   1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f, 1.0f,   1.0f, 1.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f, 1.0f,   1.0f, 1.0f,
            -0.5f,  0.5f,  0.5f,  0.5f, 0.5f, 0.5f,   0.0f, 1.0f,
            -0.5f, -0.5f,  0.5f,  1.0f, 0.0f, 1.0f,   0.0f, 0.0f,

            // left face
            -0.5f,  0.5f,  0.5f,  1.0f, 0.5f, 0.0f,   1.0f, 0.0f,
            -0.5f,  0.5f, -0.5f,  0.5f, 0.0f, 1.0f,   1.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  0.2f, 0.8f, 0.2f,   0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  0.2f, 0.8f, 0.2f,   0.0f, 1.0f,
            -0.5f, -0.5f,  0.5f,  0.0f, 0.7f, 0.9f,   0.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  1.0f, 0.5f, 0.0f,   1.0f, 0.0f,

            // right face
            0.5f,  0.5f,  0.5f,  0.8f, 0.3f, 0.3f,   1.0f, 0.0f,
            0.5f,  0.5f, -0.5f,  0.3f, 0.8f, 0.3f,   1.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.3f, 0.3f, 0.8f,   0.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.3f, 0.3f, 0.8f,   0.0f, 1.0f,
            0.5f, -0.5f,  0.5f,  0.9f, 0.9f, 0.2f,   0.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  0.8f, 0.3f, 0.3f,   1.0f, 0.0f,

            // bottom face
            -0.5f, -0.5f, -0.5f,  0.6f, 0.1f, 0.1f,   0.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.1f, 0.6f, 0.1f,   1.0f, 1.0f,
            0.5f, -0.5f,  0.5f,  0.1f, 0.1f, 0.6f,   1.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  0.1f, 0.1f, 0.6f,   1.0f, 0.0f,
            -0.5f, -0.5f,  0.5f,  0.7f, 0.2f, 0.7f,   0.0f, 0.0f,
            -0.5f, -0.5f, -0.5f,  0.6f, 0.1f, 0.1f,   0.0f, 1.0f,

            // top face
            -0.5f,  0.5f, -0.5f,  0.2f, 0.9f, 0.9f,   0.0f, 1.0f,
            0.5f,  0.5f, -0.5f,  0.9f, 0.2f, 0.9f,   1.0f, 1.0f,
            0.5f,  0.5f,  0.5f,  0.9f, 0.9f, 0.2f,   1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  0.9f, 0.9f, 0.2f,   1.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  0.2f, 0.9f, 0.2f,   0.0f, 0.0f,
            -0.5f,  0.5f, -0.5f,  0.2f, 0.9f, 0.9f,   0.0f, 1.0f
    };

    private int width = 800;
    private int height = 800;
    private Shader shader;
    private Window window;

    public void run(){
        init();
        loop();
        window.freeAndDestroy();
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init(){
        GLFWErrorCallback.createPrint(System.err).set();
        if (glfwPlatformSupported(GLFW_PLATFORM_X11)) {
            glfwInitHint(GLFW_PLATFORM, GLFW_PLATFORM_X11);
        }
        if(!glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        window = new Window("Test Window", width, height);
        GL.createCapabilities();
    }

    private void loop(){
        Renderer renderer = Renderer.getInstance();
        renderer.setViewport(0,0, window.getWidth(), window.getHeight());
        Mesh mesh = new Mesh(cubeVertices);
        mesh.AddTexture("src/main/resources/textures/container.jpg");
        this.shader = new Shader("shaders/basic.vert", "shaders/basic.frag");

        while(!window.shouldWindowClose()){
            renderer.clear();
            double timeValue = glfwGetTime();
            shader.setMatrix("transform", TransformUtils.rotateX((float) timeValue).mul(TransformUtils.rotateZ(0.5f)));
            renderer.draw(mesh, shader);
            window.swapBuffers();
            glfwPollEvents();
        }
        mesh.cleanupMesh();

    }

    public static void main(String[] args){
        new Main().run();
    }
}
