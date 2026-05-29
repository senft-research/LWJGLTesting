package senftresearch.com.update;

/**
 * Represents the general functionality of an instance that is updated every frame.
 */
public interface Updateable {
    /**
     * Update the instance with per-frame logic.
     * @param windowId The id of the currently active Window.
     */
    void update(long windowId);
}
