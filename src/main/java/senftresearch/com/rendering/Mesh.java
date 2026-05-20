package senftresearch.com.rendering;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {
    private final float[] meshVertices;
    private final int vertexArrayObject;
    private final int vertexCount;
    private final int vertexBufferObject;

    public Mesh(float[] meshVertices){
        this.meshVertices = meshVertices;
        vertexBufferObject = glGenBuffers();
        vertexArrayObject = glGenVertexArrays();
        vertexCount = meshVertices.length / 3;
        initVAO();
    }

    private void initVAO(){
        glBindVertexArray(vertexArrayObject);

        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);

        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(meshVertices.length);
        vertexBuffer.put(meshVertices).flip();
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

    }

    public void render(){
        glBindVertexArray(vertexArrayObject);
        glDrawArrays(GL_TRIANGLES, 0, vertexCount);
    }

    public void cleanupMesh(){
        glDeleteBuffers(vertexBufferObject);
        glDeleteVertexArrays(vertexArrayObject);
    }

}
