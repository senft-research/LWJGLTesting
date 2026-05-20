package senftresearch.com.rendering;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;

public class Window {
    private final String title;
    private final int width;
    private final int height;
    private long windowId;
    public Window(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        create();
    }

    private void create(){
        glfwDefaultWindowHints();
        windowId = glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
        if(windowId == MemoryUtil.NULL){
            throw new RuntimeException("Failed to create GLFW window");
        }
        try(MemoryStack stack = MemoryStack.stackPush()){
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);
            glfwGetWindowSize(windowId, pWidth, pHeight);
            // Wayland doesnt allow for positioning of windows so this is useless haha
            //GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        }
        glfwMakeContextCurrent(windowId);
        glfwSwapInterval(1);
        glfwShowWindow(windowId);

    }

    public boolean shouldWindowClose(){
        return glfwWindowShouldClose(windowId);
    }

    public void swapBuffers(){
        glfwSwapBuffers(windowId);
    }

    public void freeAndDestroy(){
        glfwFreeCallbacks(windowId);
        glfwDestroyWindow(windowId);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
