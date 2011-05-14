package final_project.control;

import final_project.model.*;
import final_project.view.PoolObserverPanel.Status;

public interface PoolObserver {
    void addCompleteResult(CompleteResult result);
    void changeMatchResult(CompleteResult result);
    void setStatus(Status status);
}