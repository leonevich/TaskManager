package by.manager.task.management.service.performer;


import by.manager.task.dao.performer.PerformerDaoImpl;
import by.manager.task.dao.domain.Performer;

import by.manager.task.utils.beans.PerformerVO;
import by.manager.task.utils.enums.PerformerType;
import by.manager.task.utils.errors.exceptions.dao.DaoException;
import by.manager.task.utils.errors.exceptions.performer.dao.PerformerDaoException;
import by.manager.task.utils.errors.exceptions.performer.service.PerformerServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerformerServiceImpl implements PerformerService {

    @Autowired
    private PerformerDaoImpl performerDao;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<PerformerVO> getAllPerformers() throws PerformerServiceException {
        List<PerformerVO> performerList;
        try {
            performerList = performerDao.getAllPerformers();
        } catch (PerformerDaoException e) {
            throw new PerformerServiceException(e);
        }
        return performerList;
    }

    @Override
    @Transactional
    public void addPerformer(PerformerVO performerVO) throws PerformerServiceException {
        Performer performer = new Performer(performerVO.getName(), performerVO.getType(), performerVO.getStatus());
        try {
            performerDao.saveOrUpdate(performer);
        } catch (DaoException e) {
            throw new PerformerServiceException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<PerformerVO> getPerformersByType(PerformerType performerType) throws PerformerServiceException {
        List<PerformerVO> performerList;
        try {
            performerList = performerDao.getPerformersByType(performerType);
        } catch (PerformerDaoException e) {
            throw new PerformerServiceException(e);
        }
        return performerList;
    }

    @Override
    @Transactional
    public void removePerformer(Long performerId) throws PerformerServiceException {
        try {
            performerDao.removePerformer(performerId);
        } catch (PerformerDaoException e) {
            throw new PerformerServiceException(e);
        }
    }

    @Override
    @Transactional
    public PerformerVO getPerformerById(Long performerId) throws PerformerServiceException {
        PerformerVO performerVO;
        try {
            performerVO = performerDao.getPerformerById(performerId);
            return performerVO;
        } catch (PerformerDaoException e) {
            throw new PerformerServiceException(e);
        }
    }

    @Override
    @Transactional
    public void updatePerformer(PerformerVO performerVO) throws PerformerServiceException {
        try {
            if (performerVO != null) {
                Performer performer = performerDao.get(performerVO.getId());
                performer.setId(performerVO.getId());
                performer.setName(performerVO.getName());
                performer.setType(performerVO.getType());
                performer.setStatus(performerVO.getStatus());
            } else {
                throw new PerformerServiceException();
            }
        } catch (DaoException e) {
            throw new PerformerServiceException(e);
        }
    }
}
