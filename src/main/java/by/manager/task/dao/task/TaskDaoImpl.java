package by.manager.task.dao.task;

import by.manager.task.dao.BaseDao;
import by.manager.task.dao.domain.Task;
import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.enums.PerformerStatus;
import by.manager.task.utils.enums.TaskStatus;
import by.manager.task.utils.errors.exceptions.dao.DaoException;

import by.manager.task.utils.errors.exceptions.task.dao.TaskDaoException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("taskDao")
public class TaskDaoImpl extends BaseDao<Task, Long> implements TaskDao {

    @Autowired
    private Properties properties;

    @Override
    public TaskVO getTaskById(Long taskId) throws TaskDaoException {
        Task task;
        try {
            task = get(taskId);
        } catch (DaoException e) {
            throw new TaskDaoException(e);
        }
        return getTaskVOFromTask(task);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TaskVO> getAllTasks() throws TaskDaoException {
        String hql = properties.getProperty("task.getAllTasks");
        List taskList;
        Query query;
        try {
            query = getQuery(hql);
            taskList = query.list();
        } catch (DaoException e) {
            throw new TaskDaoException(e);
        }
        return getTaskVOListFromTaskList(taskList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TaskVO> getTasksByPerformerId(Long id) throws TaskDaoException {
        String hql = properties.getProperty("task.getTasksByPerformerId");
        List<Task> taskList;
        try {
            Query query = getQuery(hql);
            query.setParameter("id", id);
            taskList = query.list();
        } catch (DaoException e) {
            throw new TaskDaoException(e);
        }
        return getTaskVOListFromTaskList(taskList);
    }

    @Override
    public void removeTask(Long taskId) throws TaskDaoException {
        try {
            delete(load(taskId));
        } catch (DaoException e) {
            throw new TaskDaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TaskVO> findAllAvailableTask() throws TaskDaoException {
        String hql = properties.getProperty("task.findAllAvailableTask");
        List taskList;
        try {
            Query query = getQuery(hql);
            query.setParameter("performerStatus", PerformerStatus.ACTIVE);
            query.setParameter("taskStatus", TaskStatus.NOT_STARTED);
            taskList = query.list();
        } catch (DaoException e) {
            throw new TaskDaoException(e);
        }
        return getTaskVOListFromTaskList(taskList);
    }

    @Override
    public void changeTaskStatus(TaskVO taskVO) throws TaskDaoException {
        TaskStatus taskStatus = taskVO.getStatus();
        Long taskId = taskVO.getId();
        String hql = properties.getProperty("task.changeTaskStatus");
        try {
            Query query = getQuery(hql);
            query.setParameter("taskId", taskId);
            query.setParameter("taskStatus", taskStatus);
            query.executeUpdate();
        } catch (DaoException e) {
            throw new TaskDaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Integer> getAvailablePriorityTask(Long performerId) throws TaskDaoException {
        Set<Integer> prioritySet = new LinkedHashSet<>();
        Integer maxCountOfTask = Integer.valueOf(properties.getProperty("task.maxCountOfTask"));
        for(int i =1; i<=maxCountOfTask; ++i){
            prioritySet.add(i);
        }
        List<Integer> priorityList;
        String hql = properties.getProperty("task.getAvailablePriorityTask");
        try {
            Query query = getQuery(hql);
            query.setParameter("performerId", performerId);
            priorityList = query.list();
        } catch (DaoException e) {
            throw new TaskDaoException(e);
        }
        for (Integer currentPriority : priorityList) {
            prioritySet.remove(currentPriority);
        }
        return prioritySet;
    }

    private List<TaskVO> getTaskVOListFromTaskList(List<Task> taskList) {
        List<TaskVO> taskVOList = new ArrayList<>();
        for (Task currentTtask : taskList) {
            taskVOList.add(new TaskVO(currentTtask.getId(), currentTtask.getName(), currentTtask.getCreationTime(), currentTtask.getStatus(), currentTtask.getType(), currentTtask.getPriority()));
        }
        return taskVOList;
    }

    private TaskVO getTaskVOFromTask(Task task) throws TaskDaoException {
        if (task != null) {
            TaskVO taskVO = new TaskVO(task.getId(), task.getName(), task.getCreationTime(), task.getStatus(), task.getType(), task.getPriority());
            return taskVO;
        } else {
            throw new TaskDaoException();
        }
    }
}

