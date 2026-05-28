package senftresearch.com.textures;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

/**
 * Represents the information of a texture to apply to a {@linkplain senftresearch.com.rendering.Mesh mesh}. Takes in
 * the path of the texture (jpg, png etc) and provide texture's id to other parts of the rendering pipeline.
 */
public class Texture {
    private ByteBuffer image;
    private int width;
    private int height;
    // Channels do not seem to be used, should investigate.
    private int channels;
    private int textureId;
    private ByteBuffer data;
    public Texture(String textureName){
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer widthBuf = stack.mallocInt(1);
            IntBuffer heightBuf = stack.mallocInt(1);
            IntBuffer nrChannels = stack.mallocInt(1);

            data = stbi_load(textureName, widthBuf, heightBuf, nrChannels, 0);

            if (data == null) {
                throw new RuntimeException("Failed to load image: " + STBImage.stbi_failure_reason());
            }

            width = widthBuf .get();
            height = heightBuf .get();
            channels = nrChannels.get();
            textureId = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureId);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, data);
            glGenerateMipmap(GL_TEXTURE_2D);
            stbi_image_free(data);
        }
    }

    /**
     * Gets the id of the texture, allowing other OpenGL methods to utilise it in the context of the rendering pipeline.
     * @return The unique id of the texture
     */
    public int getTextureId(){
        return textureId;
    }
}
