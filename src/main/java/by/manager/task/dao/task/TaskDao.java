package by.manager.task.dao.task;

import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.errors.exceptions.task.dao.TaskDaoException;

import java.util.List;
import java.util.Set;

public interface TaskDao {
    /**
     * @param taskId
     * @return
     * @throws TaskDaoException
     */
    TaskVO getTaskById(Long taskId) throws TaskDaoException;

    /**
     * @return
     * @throws TaskDaoException
     */
    List<TaskVO> getAllTasks() throws TaskDaoException;

    /**
     * @param id
     * @return
     * @throws TaskDaoException
     */
    List<TaskVO> getTasksByPerformerId(Long id) throws TaskDaoException;

    /**
     * @param taskId
     * @throws TaskDaoException
     */
    void removeTask(Long taskId) throws TaskDaoException;

    /**
     * @return
     * @throws TaskDaoException
     */
    List<TaskVO> findAllAvailableTask() throws TaskDaoException;

    /**
     * @param taskVO
     * @throws TaskDaoException
     */
    void changeTaskStatus(TaskVO taskVO) throws TaskDaoException;

    /**
     * @return
     * @param taskId
     */
    Set<Integer> getAvailablePriorityTask(Long taskId) throws TaskDaoException;
}
