package senftresearch.com.debug;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Thrown when an OpenGL call causes a GL Error. Acts as a way to convert the usually unthrown Open GL errors in a
 * way that can be handled via try-catches.
 */
public class GLException extends RuntimeException {
  private final List<Integer> errorIds;

  public GLException(String operationName, List<Integer> errorIds){
    super(buildMessage(operationName, errorIds));
    this.errorIds = errorIds;
  }

  private static String buildMessage(String operationName, List<Integer> errorIds){
    String joinedErrors = errorIds.stream()
            .map(GLDebugger::errorIdToString)
            .collect(Collectors.joining(", "));
    return "OpenGL error during '" + operationName + "'. Errors: " + joinedErrors;
  }

  public List<Integer> getErrorIds() {
    return errorIds;
  }
}
