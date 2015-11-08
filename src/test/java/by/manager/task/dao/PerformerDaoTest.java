package by.manager.task.dao;

import by.manager.task.dao.domain.Performer;
import by.manager.task.dao.domain.Task;
import by.manager.task.dao.performer.PerformerDaoImpl;
import by.manager.task.utils.beans.PerformerVO;
import by.manager.task.utils.enums.PerformerStatus;
import by.manager.task.utils.enums.PerformerType;
import by.manager.task.utils.errors.exceptions.dao.DaoException;
import by.manager.task.utils.errors.exceptions.performer.dao.PerformerDaoException;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


@DataSet
@Transactional
public class PerformerDaoTest extends UnitilsJUnit4 {

    @SpringApplicationContext
    public ConfigurableApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("classpath:test-context.xml");
    }

    @SpringBeanByName
    private PerformerDaoImpl performerDao;

    public final static Long PERFORMER_ID_1 = 1L;
    public final static Long PERFORMER_ID_2 = 2L;
    public final static String PERFORMER_NAME_1 = "PERFORMER_NAME_1";
    public final static String PERFORMER_NAME_2 = "PERFORMER_NAME_2";
    public final static PerformerType PERFORMER_TYPE_1 = PerformerType.values()[0];
    public final static PerformerType PERFORMER_TYPE_2 = PerformerType.values()[1];
    public final static PerformerStatus PERFORMER_STATUS_1 = PerformerStatus.values()[0];
    public final static PerformerStatus PERFORMER_STATUS_2 = PerformerStatus.values()[1];
    public final static PerformerVO PERFORMER_VO_1 = new PerformerVO(PERFORMER_ID_1, PERFORMER_NAME_1, PERFORMER_TYPE_1, PERFORMER_STATUS_1);
    public final static PerformerVO PERFORMER_VO_2 = new PerformerVO(PERFORMER_ID_2, PERFORMER_NAME_2, PERFORMER_TYPE_2, PERFORMER_STATUS_2);


    @Test
    public void addPerformerTest() throws DaoException {
        Performer performer = new Performer();
        performer.setName(PERFORMER_NAME_1);
        performer.setStatus(PERFORMER_STATUS_1);
        performer.setType(PERFORMER_TYPE_1);
        Long performerId = performerDao.add(performer);

        //cleaning the cache of hibernate
        performerDao.getSession().flush();
        performerDao.getSession().clear();
        //------------------------------

        assertNotNull(performerDao.get(performerId));
        assertEquals(performer, performerDao.get(performerId));

    }

    @Test
    public void getAllPerformersTest() throws PerformerDaoException {
        List<PerformerVO> performerVOList = new ArrayList<>();
        performerVOList.add(PERFORMER_VO_1);
        performerVOList.add(PERFORMER_VO_2);
        assertEquals(performerVOList, performerDao.getAllPerformers());

    }

    @Test
    public void getPerformersByTypeTest() throws PerformerDaoException {
        List<PerformerVO> performerVOList = new ArrayList<>();
        performerVOList.add(PERFORMER_VO_1);
        assertEquals(performerVOList, performerDao.getPerformersByType(PERFORMER_TYPE_1));
        assertNotEquals(performerVOList, performerDao.getPerformersByType(PERFORMER_TYPE_2));
    }

    @Test
    public void removePerformerTest() throws DaoException, PerformerDaoException {
        assertNotNull(performerDao.get(PERFORMER_ID_1));
        performerDao.removePerformer(PERFORMER_ID_1);
        assertNull(performerDao.get(PERFORMER_ID_1));
    }
}
