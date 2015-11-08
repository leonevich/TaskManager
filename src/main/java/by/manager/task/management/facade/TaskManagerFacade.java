package by.manager.task.management.facade;

import by.manager.task.utils.beans.PerformerVO;
import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.enums.PerformerType;
import by.manager.task.utils.errors.exceptions.executor.TaskExecutorException;
import by.manager.task.utils.errors.exceptions.performer.PerformerException;
import by.manager.task.utils.errors.exceptions.task.TaskException;

import java.util.List;
import java.util.Set;

public interface TaskManagerFacade {
    /**
     * @param performerId
     * @return
     * @throws TaskException
     */
    List<TaskVO> getTasksByPerformerId(Long performerId) throws TaskException;

    /**
     * @return
     * @throws PerformerException
     */
    List<PerformerVO> getAllPerformers() throws PerformerException;

    /**
     * @param taskVO
     * @param performerId
     * @throws TaskException
     */
    void addTask(TaskVO taskVO, Long performerId) throws TaskException;

    /**
     * @param performerVO
     * @throws PerformerException
     */
    void addPerformer(PerformerVO performerVO) throws PerformerException;

    /**
     * @param taskId
     * @throws TaskException
     */
    void removeTask(Long taskId) throws TaskException;

    /**
     * @param performerType
     * @return
     * @throws PerformerException
     */
    List<PerformerVO> getPerformersByType(PerformerType performerType) throws PerformerException;

    /**
     * @param taskVO
     * @return
     */
    TaskVO executeTask(TaskVO taskVO) throws TaskExecutorException;

    /**
     * @param performerId
     * @throws PerformerException
     */
    void removePerformer(Long performerId) throws PerformerException;

    /**
     * @param taskId
     * @return
     * @throws TaskException
     */
    TaskVO getTask(Long taskId) throws TaskException;

    /**
     * @param taskVO
     * @throws TaskException
     */
    void updateTask(TaskVO taskVO) throws TaskException;

    /**
     * @param performerId
     * @return
     * @throws PerformerException
     */
    PerformerVO getPerformer(Long performerId) throws PerformerException;

    /**
     * @param performerVO
     * @throws PerformerException
     */
    void updatePerformer(PerformerVO performerVO) throws PerformerException;

    /**
     * @return
     * @throws TaskException
     */
    List<TaskVO> getAllTask() throws TaskException;

    /**
     *
     * @return
     * @throws TaskException
     * @param performerId
     */
    Set<Integer> getAvailablePriorityTask(Long performerId) throws TaskException;
}
