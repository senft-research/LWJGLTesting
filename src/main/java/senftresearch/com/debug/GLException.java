package senftresearch.com.debug;

import java.util.List;
import java.util.stream.Collectors;

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
