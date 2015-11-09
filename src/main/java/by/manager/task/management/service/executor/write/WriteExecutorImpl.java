package by.manager.task.management.service.executor.write;


import by.manager.task.management.service.executor.TaskExecutor;
import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.enums.TaskStatus;
import by.manager.task.utils.enums.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@Component
public class WriteExecutorImpl implements TaskExecutor {

    private TaskType taskType = TaskType.WRITE;

    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    @Autowired
    private Properties properties;

    @Override
    public TaskVO executeTask(TaskVO taskVO) {
        String fullFileName = properties.getProperty("file.path") + taskVO.getName() + "." + properties.getProperty("file.expansion");
        String content = properties.getProperty("file.content");
        try (FileOutputStream fileOutputStream = new FileOutputStream(fullFileName)) {
            byte[] buffer = content.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
            taskVO.setStatus(TaskStatus.COMPLETED);
            return taskVO;
        } catch (IOException ex) {
            taskVO.setStatus(TaskStatus.BLOCKED);
            return taskVO;
        }
    }
}
