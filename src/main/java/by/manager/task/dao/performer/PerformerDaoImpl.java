package by.manager.task.dao.performer;

import by.manager.task.dao.BaseDao;
import by.manager.task.dao.domain.Performer;
import by.manager.task.utils.beans.PerformerVO;
import by.manager.task.utils.enums.PerformerType;
import by.manager.task.utils.errors.exceptions.dao.DaoException;
import by.manager.task.utils.errors.exceptions.performer.dao.PerformerDaoException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Repository("performerDao")
public class PerformerDaoImpl extends BaseDao<Performer, Long> implements PerformerDao {

    @Autowired
    private Properties properties;

    @SuppressWarnings("unchecked")
    @Override
    public List<PerformerVO> getAllPerformers() throws PerformerDaoException {
        String hql = properties.getProperty("performer.getAllPerformers");
        List<Performer> performerList;
        Query query;
        try {
            query = getQuery(hql);
            performerList = query.list();
        } catch (DaoException e) {
            throw new PerformerDaoException(e);
        }

        return getPerformerVOListFromPerformerList(performerList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PerformerVO> getPerformersByType(PerformerType performerType) throws PerformerDaoException {
        List<Performer> performerList;
        String hql = properties.getProperty("performer.getPerformersByType");
        Query query;
        try {
            query = getQuery(hql);
            query.setParameter("type", performerType);
            performerList = query.list();
        } catch (DaoException e) {
            throw new PerformerDaoException(e);
        }

        return getPerformerVOListFromPerformerList(performerList);
    }

    @Override
    public void removePerformer(Long performerId) throws PerformerDaoException {
        try {
            delete(load(performerId));
        } catch (DaoException e) {
            throw new PerformerDaoException(e);
        }
    }

    @Override
    public PerformerVO getPerformerById(Long performerId) throws PerformerDaoException {
        Performer performer;
        try {
            performer = get(performerId);
        } catch (DaoException e) {
            throw new PerformerDaoException(e);
        }
        return getPerformerVOFromPerformer(performer);
    }

    private List<PerformerVO> getPerformerVOListFromPerformerList(List<Performer> performerList) {
        List<PerformerVO> performerVOList = new ArrayList<>();
        for (Performer performer : performerList) {
            performerVOList.add(new PerformerVO(performer.getId(), performer.getName(), performer.getType(), performer.getStatus()));
        }
        return performerVOList;
    }

    private PerformerVO getPerformerVOFromPerformer(Performer performer) throws PerformerDaoException {
        if (performer != null) {
            PerformerVO performerVO = new PerformerVO(performer.getId(), performer.getName(), performer.getType(), performer.getStatus());
            return performerVO;
        } else {
            throw new PerformerDaoException();
        }
    }
}
