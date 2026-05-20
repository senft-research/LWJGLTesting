package senftresearch.com.rendering;

import org.lwjgl.opengl.GL43;

import static org.lwjgl.opengl.GL11.*;

/**
 * Represents the logic of an OpenGL renderer. Responsible for rendering specified meshes via their corresponding shader.
 */
public class Renderer {

    private static Renderer instance;

    public static Renderer getInstance(){
        if(instance == null){
            instance = new Renderer();
        }
        return instance;
    }

    /**
     * Private constructor that initialises the Debug Outputs of OpenGL (to receive actual debug messages from OpenGL).
     */
    private Renderer(){
        glEnable(GL43.GL_DEBUG_OUTPUT);
        glEnable(GL43.GL_DEBUG_OUTPUT_SYNCHRONOUS);
    }

    /**
     * Clears the colour and depth buffers.
     */
    public void clear(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    /**
     * Draws the specified mesh via a corresponding shader.
     * @param mesh The mesh to render.
     * @param shader The shader to utilise when rendering the mesh.
     */
    public void draw (Mesh mesh, Shader shader){
        shader.use();
        mesh.render();
    }

    /**
     * Sets the viewport of the open window.
     * @param x The x coordinate to set.
     * @param y The y coordinate to set.
     * @param width The width of the viewport.
     * @param height The height of the viewport.
     */
    public void setViewport(int x, int y, int width, int height){
        glViewport(x, y, width, height);
    }
}
