package by.manager.task.management.service.executor.read;

import by.manager.task.management.service.executor.TaskExecutor;
import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.enums.TaskStatus;
import by.manager.task.utils.enums.TaskType;
import by.manager.task.utils.errors.exceptions.executor.read.ReadExecutorException;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ReadExecutorImpl implements TaskExecutor {

    private final static TaskType TASK_TYPE = TaskType.READ;

    @Override
    public TaskType getTaskType() {
        return TASK_TYPE;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public TaskVO executeTask(TaskVO taskVO) throws ReadExecutorException {
        File file = new File("d:\\files_to_work\\");
        File[] files = file.listFiles();
        if ((files != null ? files.length : 0) != 0) {
            for (File currentFile : files) {
                if (currentFile.canRead()) {
                    currentFile.delete();
                    taskVO.setStatus(TaskStatus.COMPLETED);
                    return taskVO;
                }
            }
        }
        taskVO.setStatus(TaskStatus.BLOCKED);
        return taskVO;
    }
}