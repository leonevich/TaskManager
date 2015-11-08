package by.manager.task.management.service.executor;

import by.manager.task.management.service.task.TaskService;
import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.enums.TaskStatus;
import by.manager.task.utils.enums.TaskType;
import by.manager.task.utils.errors.exceptions.executor.TaskExecutorError;
import by.manager.task.utils.errors.exceptions.executor.TaskExecutorException;
import by.manager.task.utils.errors.exceptions.executor.read.ReadExecutorException;
import by.manager.task.utils.errors.exceptions.task.service.TaskServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskExecutorImpl implements TaskExecutor {

    @Autowired
    public List<TaskExecutor> executorList;

    @Autowired
    private TaskService taskService;

    @Override
    public TaskType getTaskType() {
        return null;
    }

    @Override
    public TaskVO executeTask(TaskVO task) throws ReadExecutorException, TaskExecutorException {

        if (task != null) {
            TaskType taskType = task.getType();
            if (taskType != null) {
                TaskExecutor taskExecutor = null;
                for (TaskExecutor currentExecutor : executorList) {
                    if (currentExecutor.getTaskType() == taskType) {
                        taskExecutor = currentExecutor;
                        break;
                    }
                }
                if (taskExecutor != null) {
                    task.setStatus(TaskStatus.IN_PROGRESS);
                    try {
                        taskService.changeTaskStatus(task);
                    } catch (TaskServiceException e) {
                        throw new TaskExecutorException(TaskExecutorError.TASK_EXECUTOR_000);
                    }
                    taskExecutor.executeTask(task);
                    try {
                        taskService.changeTaskStatus(task);
                    } catch (TaskServiceException e) {
                        throw new TaskExecutorException(TaskExecutorError.TASK_EXECUTOR_000);
                    }
                    return task;
                } else {
                    throw new TaskExecutorException(TaskExecutorError.TASK_EXECUTOR_000);
                }
            } else {
                throw new TaskExecutorException(TaskExecutorError.TASK_EXECUTOR_000);
            }
        } else {
            throw new TaskExecutorException(TaskExecutorError.TASK_EXECUTOR_000);
        }
    }
}
