package senftresearch.com;

import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.opengl.GL20.*;

public class Shader {
    private int id;
    private String vertexCode;
    private String fragmentCode;
    private int vertex;
    private int fragment;


    public Shader(CharSequence vertexPath, CharSequence fragmentPath){
        //TODO remember how to get the shaders from the resources path
    }

    public void setBool(String name, boolean value){

    }

    public void setInt(String name, int value){

    }

    public void setFloat(String name, float value){

    }

    public void use(){
        glUseProgram(id);
    }

    private void initShaders(){
        vertex = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertex, vertexCode);
        glCompileShader(vertex);
        //TODO rest of logic required
    }

}
