package senftresearch.com.rendering;

import org.lwjgl.BufferUtils;
import senftresearch.com.textures.Texture;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

/***
 * Represents the vertex information of a renderable mesh. Contains the logic required to initiate the buffers / vertex
 * arrays that allow the float array data to be read as individual vertices of the mesh.
 */
public class Mesh {
    private final float[] meshVertices;
    private final int[] meshIndices;
    private final int vertexArrayObject;
    private final int vertexCount;
    private final int vertexBufferObject;
    private final int elementBufferObject;
    private Texture texture;
    /**
     * Constructor that takes in a specified array of float values, to then be used to initialise a vertex array object
     * and its corresponding vertex buffer object. This allows the float array to be read as individual vertexes.
     * @param meshVertices The floats that represent the vertices of the mesh.
     */
    public Mesh(float[] meshVertices, int[] meshIndices){
        this.meshVertices = meshVertices;
        this.meshIndices = meshIndices;
        vertexBufferObject = glGenBuffers();
        vertexArrayObject = glGenVertexArrays();
        elementBufferObject = glGenBuffers();
        vertexCount = meshVertices.length / 8;
        initVAO();
    }

    public void AddTexture(String texturePath){
        this.texture = new Texture(texturePath);
    }
    /**
     * Initialises the vertex array object of the mesh, which contains the pointer and vertex buffer object required for
     * the GPU to read the float array as 3 point vertices.
     */
    private void initVAO(){
        int stride = 8 * Float.BYTES;

        glBindVertexArray(vertexArrayObject);

        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);

        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(meshVertices.length);
        vertexBuffer.put(meshVertices).flip();
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, elementBufferObject);

        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(meshIndices.length);
        indicesBuffer.put(meshIndices).flip();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, stride, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 3, GL_FLOAT, false, stride, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, 2, GL_FLOAT, false, stride, 6 * Float.BYTES);
        glEnableVertexAttribArray(2);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

    }

    /**
     * Renders the mesh after binding the VAO.
     */
    public void render(){
        glBindVertexArray(vertexArrayObject);
        glBindTexture(GL_TEXTURE_2D, texture.getTextureId());
        glDrawElements(GL_TRIANGLES, meshIndices.length, GL_UNSIGNED_INT, 0);
        glBindVertexArray(0);
    }

    /**
     * Cleans the VAO and VBO out of memory, to be called at exit of the programme.
     */
    public void cleanupMesh(){
        glDeleteBuffers(vertexBufferObject);
        glDeleteVertexArrays(vertexArrayObject);
    }

}
