package by.manager.task.management.service.executor;


import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.enums.TaskType;
import by.manager.task.utils.errors.exceptions.executor.TaskExecutorException;
import by.manager.task.utils.errors.exceptions.executor.read.ReadExecutorException;

public interface TaskExecutor {
    /**
     * @return
     */
    TaskType getTaskType();

    /**
     * @param taskVO
     * @return
     * @throws ReadExecutorException
     * @throws TaskExecutorException
     */
    TaskVO executeTask(TaskVO taskVO) throws ReadExecutorException, TaskExecutorException;
}
