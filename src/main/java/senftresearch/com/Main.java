package senftresearch.com;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import senftresearch.com.rendering.Mesh;
import senftresearch.com.rendering.Renderer;
import senftresearch.com.rendering.Shader;
import senftresearch.com.rendering.Window;

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
        renderer.setViewport(0,0, window.getWidth()+50, window.getHeight());
        Mesh mesh = new Mesh(vertices, indices);
        mesh.AddTexture("src/main/resources/textures/container.jpg");
        this.shader = new Shader("shaders/basic.vert", "shaders/basic.frag");

        while(!window.shouldWindowClose()){
            renderer.clear();
            double timeValue = glfwGetTime();
            shader.setMatrix("transform",TransformUtils.rotateZ((float) timeValue));
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
