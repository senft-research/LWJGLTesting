package senftresearch.com;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.lwjgl.opengl.GL20.*;

public class Shader {
    private int id;
    private final String vertexCode;
    private final String fragmentCode;

    public Shader(String vertexPath, String fragmentPath) throws RuntimeException{
        try{
            vertexCode = getCodeFromPath(vertexPath);
            fragmentCode = getCodeFromPath(fragmentPath);
            initShaders();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void use(){
        glUseProgram(id);
    }

    public void setBool(String name, Boolean value){
        int boolInt = value ? 1 : 0;
        glUniform1i(glGetUniformLocation(id, name), boolInt);
    }

    public void setInt(String name, int value){
        glUniform1i(glGetUniformLocation(id, name), value);
    }

    public void setFloat(String name, float value){
        glUniform1f(glGetUniformLocation(id,name), value);
    }

    private void initShaders(){
        int vertex = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertex, vertexCode);
        glCompileShader(vertex);
        //TODO Check needed here to see if shader compile succeeded

        int fragment = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragment, fragmentCode);
        glCompileShader(fragment);
        //TODO Check needed here to see if shader compile succeeded


        id = glCreateProgram();
        glAttachShader(id, vertex);
        glAttachShader(id, fragment);
        glLinkProgram(id);
        //TODO check needed here to check if the program succeeded

        glDeleteShader(vertex);
        glDeleteShader(fragment);
    }

    private String getCodeFromPath(String path) throws Exception{
        try(InputStream input = this.getClass().getClassLoader().getResourceAsStream(path)){
            if (input == null){
                throw new RuntimeException("Resource not found: " + path);
            }
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        }
    }



}
