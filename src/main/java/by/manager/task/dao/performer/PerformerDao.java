package by.manager.task.dao.performer;

import by.manager.task.utils.beans.PerformerVO;
import by.manager.task.utils.enums.PerformerType;
import by.manager.task.utils.errors.exceptions.performer.dao.PerformerDaoException;

import java.util.List;

public interface PerformerDao {

    /**
     * @return
     * @throws PerformerDaoException
     */
    List<PerformerVO> getAllPerformers() throws PerformerDaoException;

    /**
     * @param performerType
     * @return
     * @throws PerformerDaoException
     */
    List<PerformerVO> getPerformersByType(PerformerType performerType) throws PerformerDaoException;

    /**
     * @param performerId
     * @throws PerformerDaoException
     */
    void removePerformer(Long performerId) throws PerformerDaoException;

    /**
     * @param performerId
     * @return
     * @throws PerformerDaoException
     */
    PerformerVO getPerformerById(Long performerId) throws PerformerDaoException;
}
