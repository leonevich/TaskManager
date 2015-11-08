package by.manager.task.management.service.executor.write;


import by.manager.task.management.service.executor.TaskExecutor;
import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.enums.TaskStatus;
import by.manager.task.utils.enums.TaskType;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class WriteExecutorImpl implements TaskExecutor {

    private TaskType taskType = TaskType.WRITE;

    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    @Override
    public TaskVO executeTask(TaskVO taskVO) {
        String fullFileName = "d:\\files_to_work\\" + taskVO.getName() + ".txt";
        String text = "Any file";
        try (FileOutputStream fileOutputStream = new FileOutputStream(fullFileName)) {
            byte[] buffer = text.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
            taskVO.setStatus(TaskStatus.COMPLETED);
            return taskVO;
        } catch (IOException ex) {
            taskVO.setStatus(TaskStatus.BLOCKED);
            return taskVO;
        }
    }
}
