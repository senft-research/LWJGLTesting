package senftresearch.com.rendering;

import org.lwjgl.opengl.GL43;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    private static Renderer instance;

    public static Renderer getInstance(){
        if(instance == null){
            instance = new Renderer();
        }
        return instance;
    }

    private Renderer(){
        glEnable(GL43.GL_DEBUG_OUTPUT);
        glEnable(GL43.GL_DEBUG_OUTPUT_SYNCHRONOUS);
    }


    public void clear(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void draw (Mesh mesh, Shader shader){
        shader.use();
        mesh.render();
    }

    public void setViewport(int x, int y, int width, int height){
        glViewport(x, y, width, height);
    }
}
