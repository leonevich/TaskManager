package by.manager.task.management.facade;

import by.manager.task.management.service.executor.TaskExecutor;
import by.manager.task.management.service.performer.PerformerService;
import by.manager.task.management.service.task.TaskService;
import by.manager.task.utils.beans.PerformerVO;
import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.enums.PerformerType;
import by.manager.task.utils.errors.exceptions.executor.TaskExecutorException;
import by.manager.task.utils.errors.exceptions.executor.read.ReadExecutorException;
import by.manager.task.utils.errors.exceptions.performer.PerformerException;
import by.manager.task.utils.errors.exceptions.task.TaskException;
import by.manager.task.utils.errors.exceptions.performer.service.PerformerServiceException;
import by.manager.task.utils.errors.exceptions.task.service.TaskServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TaskManagerFacadeImpl implements TaskManagerFacade {

    @Autowired
    private PerformerService performerService;

    @Autowired
    private TaskService taskService;


    @Autowired
    @Qualifier("taskExecutorImpl")
    private TaskExecutor taskExecutor;

    @Override
    public List<TaskVO> getTasksByPerformerId(Long performerId) throws TaskException {
        try {
            return taskService.getTasksByPerformerId(performerId);
        } catch (TaskServiceException e) {
            throw new TaskException(e);
        }
    }

    @Override
    public List<PerformerVO> getAllPerformers() throws PerformerException {
        try {
            return performerService.getAllPerformers();
        } catch (PerformerServiceException e) {
            throw new PerformerException(e);
        }
    }

    @Override
    public void addTask(TaskVO task, Long performerId) throws TaskException {
        try {
            taskService.addTask(task, performerId);
        } catch (TaskServiceException e) {
            throw new TaskException(e);
        }
    }

    @Override
    public void addPerformer(PerformerVO performerVO) throws PerformerException {
        try {
            performerService.addPerformer(performerVO);
        } catch (PerformerServiceException e) {
            throw new PerformerException(e);
        }
    }

    @Override
    public void removeTask(Long taskId) throws TaskException {

        try {
            taskService.removeTask(taskId);
        } catch (TaskServiceException e) {
            throw new TaskException(e);
        }

    }

    @Override
    public List<PerformerVO> getPerformersByType(PerformerType performerType) throws PerformerException {
        List<PerformerVO> performerVOList;
        try {
            performerVOList = performerService.getPerformersByType(performerType);
        } catch (PerformerServiceException e) {
            throw new PerformerException(e);
        }
        return performerVOList;
    }

    @Override
    public TaskVO executeTask(TaskVO task) throws TaskExecutorException {
        TaskVO taskVO;
        try {
            taskVO = taskExecutor.executeTask(task);
        } catch (ReadExecutorException e) {
            throw new TaskExecutorException(e);
        }
        return taskVO;
    }

    @Override
    public void removePerformer(Long performerId) throws PerformerException {
        try {
            performerService.removePerformer(performerId);
        } catch (PerformerServiceException e) {
            throw new PerformerException(e);
        }
    }

    @Override
    public TaskVO getTask(Long taskId) throws TaskException {
        TaskVO taskVO;
        try {
            taskVO = taskService.getTaskById(taskId);
        } catch (TaskServiceException e) {
            throw new TaskException(e);
        }
        return taskVO;
    }

    @Override
    public void updateTask(TaskVO taskVO) throws TaskException {
        try {
            taskService.updateTask(taskVO);
        } catch (TaskServiceException e) {
            throw new TaskException(e);
        }
    }

    @Override
    public PerformerVO getPerformer(Long performerId) throws PerformerException {
        PerformerVO performerVO;
        try {
            performerVO = performerService.getPerformerById(performerId);
            return performerVO;
        } catch (PerformerServiceException e) {
            throw new PerformerException(e);
        }
    }

    @Override
    public void updatePerformer(PerformerVO performerVO) throws PerformerException {
        try {
            performerService.updatePerformer(performerVO);
        } catch (PerformerServiceException e) {
            throw new PerformerException(e);
        }
    }

    @Override
    public List<TaskVO> getAllTask() throws TaskException {
        List<TaskVO> taskVOList;
        try {
            taskVOList = taskService.getAllTasks();
        } catch (TaskServiceException e) {
            throw new TaskException(e);
        }
        return taskVOList;
    }

    @Override
    public Set<Integer> getAvailablePriorityTask(Long performerId) throws TaskException {
        Set<Integer> prioritySet;
        try {
            prioritySet = taskService.getAvailablePriorityTask(performerId);
        } catch (TaskServiceException e) {
            throw new TaskException(e);
        }
        return prioritySet;
    }
}
