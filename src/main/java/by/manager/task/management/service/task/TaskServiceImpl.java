package by.manager.task.management.service.task;

import by.manager.task.dao.domain.Performer;
import by.manager.task.dao.performer.PerformerDaoImpl;
import by.manager.task.dao.task.TaskDaoImpl;
import by.manager.task.dao.domain.Task;
import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.errors.exceptions.dao.DaoException;
import by.manager.task.utils.errors.exceptions.task.dao.TaskDaoException;
import by.manager.task.utils.errors.exceptions.task.service.TaskServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDaoImpl taskDao;

    @Autowired
    private PerformerDaoImpl performerDao;

    @Override
    @Transactional
    public List<TaskVO> getAllTasks() throws TaskServiceException {
        List<TaskVO> taskList = null;
        try {
            taskList = taskDao.getAllTasks();
        } catch (TaskDaoException e) {
            throw new TaskServiceException(e);
        }
        return taskList;
    }

    @Override
    @Transactional
    public Long addTask(TaskVO taskVO, Long performerId) throws TaskServiceException {
        try {
            Performer performer = performerDao.load(performerId);
            Task task = new Task(taskVO.getName(), taskVO.getCreationTime(), taskVO.getStatus(), taskVO.getType(), taskVO.getPriority(), performer);
            Long taskId = taskDao.add(task);
            return taskId;
        } catch (DaoException e) {
            throw new TaskServiceException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<TaskVO> getTasksByPerformerId(Long id) throws TaskServiceException {
        List<TaskVO> performerTasksList;
        try {
            performerTasksList = taskDao.getTasksByPerformerId(id);
        } catch (TaskDaoException e) {
            throw new TaskServiceException(e);
        }
        return performerTasksList;
    }

    @Override
    @Transactional
    public void removeTask(Long taskId) throws TaskServiceException {
        try {
            taskDao.removeTask(taskId);
        } catch (TaskDaoException e) {
            throw new TaskServiceException(e);
        }
    }

    @Override
    @Transactional
    public List<TaskVO> findAllAvailableTask() throws TaskServiceException {
        List<TaskVO> taskList;
        try {
            taskList = taskDao.findAllAvailableTask();
        } catch (TaskDaoException e) {
            throw new TaskServiceException(e);
        }
        return taskList;
    }

    @Override
    @Transactional
    public void changeTaskStatus(TaskVO taskVO) throws TaskServiceException {
        try {
            taskDao.changeTaskStatus(taskVO);
        } catch (TaskDaoException e) {
            throw new TaskServiceException(e);
        }
    }

    @Override
    @Transactional
    public TaskVO getTaskById(Long taskId) throws TaskServiceException {
        TaskVO taskVO;
        try {
            taskVO = taskDao.getTaskById(taskId);
        } catch (TaskDaoException e) {
            throw new TaskServiceException(e);
        }
        return taskVO;
    }

    @Override
    @Transactional
    public void updateTask(TaskVO taskVO) throws TaskServiceException {
        try {
            if (taskVO != null) {
                Task task = taskDao.get(taskVO.getId());
                task.setId(taskVO.getId());
                task.setStatus(taskVO.getStatus());
                task.setType(taskVO.getType());
                task.setCreationTime(taskVO.getCreationTime());
                task.setPriority(taskVO.getPriority());
                task.setName(taskVO.getName());
                taskDao.update(task);
            } else {
                throw new TaskServiceException();
            }
        } catch (DaoException e) {
            throw new TaskServiceException(e);
        }
    }

    @Override
    @Transactional
    public Set<Integer> getAvailablePriorityTask(Long performerId) throws TaskServiceException {
        Set<Integer> prioritySet;
        try {
            prioritySet = taskDao.getAvailablePriorityTask(performerId);
        } catch (TaskDaoException e) {
            throw new TaskServiceException(e);
        }
        return prioritySet;
    }
}
