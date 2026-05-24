package senftresearch.com.debug;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class GLDebugger {
    private static GLDebugger instance;

    public static GLDebugger getInstance(){
        if(instance == null){
            instance = new GLDebugger();
        }
        return instance;
    }

    public void checkError(String operationName) throws GLException {
        List<Integer> errorIds = new ArrayList<>();
        int errorId;
        while((errorId = glGetError()) != GL_NO_ERROR){
            errorIds.add(errorId);
        }

        if(!errorIds.isEmpty()){
            throw new GLException(operationName, errorIds);

        }
    }

    public void guard(String operationName, Runnable glCall){
        glCall.run();
        checkError(operationName);
    }
    public static String errorIdToString(int errorId){
        return switch (errorId) {
            case GL_INVALID_ENUM -> "GL_INVALID_ENUM";
            case GL_INVALID_VALUE -> "GL_INVALID_VALUE";
            case GL_INVALID_OPERATION -> "GL_INVALID_OPERATION";
            case GL_STACK_OVERFLOW -> "GL_STACK_OVERFLOW";
            case GL_STACK_UNDERFLOW -> "GL_STACK_UNDERFLOW";
            case GL_OUT_OF_MEMORY -> "GL_OUT_OF_MEMORY";
            default -> "UNKNOWN_ERROR: " + errorId;
        };
    }
}
