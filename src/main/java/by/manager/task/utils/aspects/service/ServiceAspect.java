package by.manager.task.utils.aspects.service;


import by.manager.task.dao.domain.Performer;
import by.manager.task.dao.performer.PerformerDaoImpl;
import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.enums.PerformerType;
import by.manager.task.utils.enums.TaskStatus;
import by.manager.task.utils.enums.TaskType;
import by.manager.task.utils.errors.exceptions.aspect.ServiceAspectException;
import by.manager.task.utils.errors.exceptions.dao.DaoException;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@Aspect
public class ServiceAspect {

    private static Logger log = Logger.getLogger(ServiceAspect.class);

    public ServiceAspect() {
    }

    @Autowired
    PerformerDaoImpl performerDao;

    @Around("execution (* by.manager.task.management.service.task.TaskServiceImpl.addTask(..)) && args(taskVO, performerId)")
    public void setTaskAspect(ProceedingJoinPoint pjp, TaskVO taskVO, Long performerId) throws ServiceAspectException {
        log.info("Set creation task time");
        Timestamp creationTime;
        Date now = new Date();
        long time = now.getTime();
        creationTime = new Timestamp(time);
        taskVO.setCreationTime(creationTime);
        taskVO.setStatus(TaskStatus.NOT_STARTED);
        PerformerType performerType;
        try {
            Performer performer = performerDao.get(performerId);
            performerType = performer.getType();
        } catch (DaoException e) {
            throw new ServiceAspectException(e);
        }

        if (PerformerType.READER.equals(performerType)) {
            taskVO.setType(TaskType.READ);

            try {
                pjp.proceed(new Object[]{taskVO, performerId});
            } catch (Throwable e) {
                throw new ServiceAspectException(e);
            }
        }

        if (PerformerType.WRITER.equals(performerType)) {
            taskVO.setType(TaskType.WRITE);
            try {
                pjp.proceed(new Object[]{taskVO, performerId});
            } catch (Throwable e) {
                throw new ServiceAspectException(e);
            }
        }
    }

    @Before("execution (* by.manager.task.management.service.task.TaskServiceImpl.findAllAvailableTask(..))")
    public void findAllAvailableTaskAspect() {
        log.info("Find all available task");

    }
}
