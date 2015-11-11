package by.manager.task.management.service.task;

import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.errors.exceptions.task.service.TaskServiceException;

import java.util.List;
import java.util.Set;

public interface TaskService {

    /**
     * @return
     * @throws TaskServiceException
     */
    List<TaskVO> getAllTasks() throws TaskServiceException;

    /**
     * @param taskVO
     * @param performerId
     * @return
     * @throws TaskServiceException
     */
    Long addTask(TaskVO taskVO, Long performerId) throws TaskServiceException;

    /**
     * @param id
     * @return
     * @throws TaskServiceException
     */
    List<TaskVO> getTasksByPerformerId(Long id) throws TaskServiceException;

    /**
     * @param taskId
     * @throws TaskServiceException
     */
    void removeTask(Long taskId) throws TaskServiceException;

    /**
     * @return
     * @throws TaskServiceException
     */
    List<TaskVO> findAllAvailableTask() throws TaskServiceException;

    /**
     * @param taskVO
     * @throws TaskServiceException
     */
    void saveTaskStatus(TaskVO taskVO) throws TaskServiceException;

    /**
     * @param taskId
     * @return
     * @throws TaskServiceException
     */
    TaskVO getTaskById(Long taskId) throws TaskServiceException;

    /**
     * @param taskVO
     * @throws TaskServiceException
     */
    void updateTask(TaskVO taskVO) throws TaskServiceException;

    /**
     * @return
     * @throws TaskServiceException
     * @param performerId
     */
    Set<Integer> getAvailablePriorityTask(Long performerId) throws TaskServiceException;
}
