package by.manager.task.management.service.performer;

import by.manager.task.dao.domain.Performer;
import by.manager.task.utils.beans.PerformerVO;
import by.manager.task.utils.enums.PerformerType;
import by.manager.task.utils.errors.exceptions.performer.service.PerformerServiceException;

import java.util.List;

public interface PerformerService {
    /**
     * @return
     * @throws PerformerServiceException
     */
    List<PerformerVO> getAllPerformers() throws PerformerServiceException;

    /**
     * @param performerVO
     * @throws PerformerServiceException
     */
    void addPerformer(PerformerVO performerVO) throws PerformerServiceException;

    /**
     * @param performerType
     * @return
     * @throws PerformerServiceException
     */
    List<PerformerVO> getPerformersByType(PerformerType performerType) throws PerformerServiceException;

    /**
     * @param performerId
     * @throws PerformerServiceException
     */
    void removePerformer(Long performerId) throws PerformerServiceException;

    /**
     * @param performerId
     * @return
     * @throws PerformerServiceException
     */
    PerformerVO getPerformerById(Long performerId) throws PerformerServiceException;

    /**
     * @param performerVO
     * @throws PerformerServiceException
     */
    void updatePerformer(PerformerVO performerVO) throws PerformerServiceException;
}
