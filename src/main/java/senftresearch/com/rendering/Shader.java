package senftresearch.com.rendering;

import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.charset.StandardCharsets;

import static org.lwjgl.opengl.GL20.*;

/**
 * Represents the general logic of a shader in OpenGL. Contains logic for vertex and fragmentation shaders.
 */
public class Shader {
    private int id;
    private final String vertexCode;
    private final String fragmentCode;

    /**
     * Constructor that acquires the code for both shaders from the specified paths, along with initialising the shaders
     * in a shader program.
     * @param vertexPath The path to the vertex shader.
     * @param fragmentPath The path to the fragmentation shader.
     */
    public Shader(String vertexPath, String fragmentPath) throws RuntimeException{
        try{
            vertexCode = getCodeFromPath(vertexPath);
            fragmentCode = getCodeFromPath(fragmentPath);
            initShaders();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Uses the shader in the current OpenGL context.
     */
    public void use(){
        glUseProgram(id);
    }

    /**
     * Sets a boolean uniform value within the shader program.
     * @param name The name of the uniform value to set.
     * @param value The value to set.
     */
    public void setBool(String name, Boolean value){
        int boolInt = value ? 1 : 0;
        glUniform1i(glGetUniformLocation(id, name), boolInt);
    }

    /**
     * Sets an int uniform value within the shader program.
     * @param name The name of the uniform value to set.
     * @param value The value to set.
     */
    public void setInt(String name, int value){
        glUniform1i(glGetUniformLocation(id, name), value);
    }

    /**
     * Sets a float uniform value within the shader program.
     * @param name The name of the uniform value to set.
     * @param value The value to set.
     */
    public void setFloat(String name, float value){
        glUniform1f(glGetUniformLocation(id,name), value);
    }

    public void setMatrix(String name, Matrix4f matrix){
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(16);
            matrix.get(buffer);
            glUniformMatrix4fv(glGetUniformLocation(id, name), false, buffer);
        }
    }
    /**
     * Initialises the shaders by creating both shaders individually and compiling them, to then link them together in
     * a shader program.
     */
    private void initShaders(){
        int vertex = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertex, vertexCode);
        glCompileShader(vertex);
        if (glGetShaderi(vertex, GL_COMPILE_STATUS) == GL_FALSE) {
            System.out.println(glGetShaderInfoLog(vertex));
        }

        int fragment = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragment, fragmentCode);
        glCompileShader(fragment);

        if (glGetShaderi(fragment, GL_COMPILE_STATUS) == GL_FALSE) {
            System.out.println(glGetShaderInfoLog(fragment));
        }

        id = glCreateProgram();
        glAttachShader(id, vertex);
        glAttachShader(id, fragment);
        glLinkProgram(id);

        if (glGetProgrami(id, GL_LINK_STATUS) == GL_FALSE) {
            System.out.println(glGetProgramInfoLog(id));
        }

        glDeleteShader(vertex);
        glDeleteShader(fragment);
    }

    /**
     * Helper method for locating a shader within the Java resources directory and returning its code.
     * @param path The path to the shader.
     * @return The code of the shader.
     */
    private String getCodeFromPath(String path) throws Exception{
        try(InputStream input = this.getClass().getClassLoader().getResourceAsStream(path)){
            if (input == null){
                throw new RuntimeException("Resource not found: " + path);
            }
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        }
    }



}
