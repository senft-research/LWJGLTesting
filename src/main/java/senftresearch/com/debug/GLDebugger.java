package senftresearch.com.debug;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/**
 * Debugger that is responsible for detecting errors thrown by OpenGL calls, and then throwing a
 * {@linkplain GLException representative java exception}.
 */
public class GLDebugger {

    /**
     * Private static instance of the GLDebugger, as per the Singleton Pattern.
     */
    private static GLDebugger instance;

    /**
     * Static method for retrieving the singleton instance of the GLDebugger.
     * @return The singleton GLDebugger instance.
     */
    public static GLDebugger getInstance(){
        if(instance == null){
            instance = new GLDebugger();
        }
        return instance;
    }

    /**
     * <p>Guards a specified Runnable, ensuring any OpenGL calls within the Runnable have any potential GL Errors thrown
     * as representative {@linkplain GLException GL Exception Instances}.
     * </p>
     * <i>(Developer's Note: It should be noted that, technically, this function can have 0 OpenGL calls within it,
     * but this would be nonsensical practice.
     * <br>
     * Additionally, if OpenGL calls within the codebase are not guarded, if errors occur in those non-guarded calls,
     * they would be captured by the next guarded runnable instead, leading to misleading exceptions being thrown. </i>
     * @param operationName The designated name for the guarded Runnable.
     * @param glRunnable The runnable to guard.
     */
    public void guard(String operationName, Runnable glRunnable){
        glRunnable.run();
        checkError(operationName);
    }

    /**
     * Checks the current error flags currently recorded deu to a specified operation.
     * @param operationName The name of the operation being guarded.
     * @throws GLException Throws when a GL Error / Errors are identified.
     */
    private void checkError(String operationName) throws GLException {
        List<Integer> errorIds = new ArrayList<>();
        int errorId;
        while((errorId = glGetError()) != GL_NO_ERROR){
            errorIds.add(errorId);
        }

        if(!errorIds.isEmpty()){
            throw new GLException(operationName, errorIds);

        }
    }

    /**
     * Static method that converts the Error Id of an OpenGL Error into a representative string message.
     * @param errorId The id to convert.
     * @return Representative error message.
     */
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
