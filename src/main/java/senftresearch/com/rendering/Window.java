package senftresearch.com.rendering;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;

/**
 * Represents the primary window of the OpenGL Context.
 */
public class Window {
    private final String title;
    private final int width;
    private final int height;
    private long windowId;

    /**
     * Constructs the Window, along with settings the dimensions of the window and its title.
     * @param title The title of the Window.
     * @param width The Window Width (in pixels).
     * @param height The Height Width (in pixels)
     */
    public Window(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        create();
    }

    /**
     * Creates the window via providing it a unique id via glfw methods, along with ensuring that the context is set to
     * the window and that the window is shown.
     */
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
        }
        glfwMakeContextCurrent(windowId);
        glfwSwapInterval(1);
        glfwShowWindow(windowId);

    }

    /**
     * Checks to see if the window has been requested to close or not.
     * @return True if the window should close, false otherwise.
     */
    public boolean shouldWindowClose(){
        return glfwWindowShouldClose(windowId);
    }

    /**
     * Swaps the background buffer (where things are currently being drawn) with the current buffer. Leading to
     * seamless transitions between frames.
     */
    public void swapBuffers(){
        glfwSwapBuffers(windowId);
    }

    /**
     * Frees and destroys the callbacks and window.
     */
    public void freeAndDestroy(){
        glfwFreeCallbacks(windowId);
        glfwDestroyWindow(windowId);
    }

    /**
     * Gets the height of the window (in pixels).
     * @return The height of the window.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the width of the window (in pixels).
     * @return The width of the window.
     */
    public int getWidth() {
        return width;
    }
}
