package by.manager.task.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import by.manager.task.dao.domain.Task;
import by.manager.task.dao.task.TaskDaoImpl;
import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.enums.TaskStatus;
import by.manager.task.utils.enums.TaskType;
import by.manager.task.utils.errors.exceptions.dao.DaoException;
import by.manager.task.utils.errors.exceptions.task.dao.TaskDaoException;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@DataSet
@Transactional
public class TaskDaoTest extends UnitilsJUnit4 {

    @SpringApplicationContext
    public ConfigurableApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("classpath:test-context.xml");
    }

    @SpringBeanByName
    private TaskDaoImpl taskDao;

    public final static Long TASK_ID_1 = 1L;
    public final static Long TASK_ID_2 = 2L;
    public final static String TASK_NAME_1 = "TASK_NAME_1";
    public final static String TASK_NAME_2 = "TASK_NAME_2";
    public final static Timestamp CREATION_TIME_1 = Timestamp.valueOf("2015-07-01 00:00:00.0");
    public final static Timestamp CREATION_TIME_2 = Timestamp.valueOf("2015-11-05 00:00:00.0");
    public final static TaskStatus TASK_STATUS_1 = TaskStatus.values()[0];
    public final static TaskStatus TASK_STATUS_2 = TaskStatus.values()[1];
    public final static TaskType TASK_TYPE_1 = TaskType.values()[0];
    public final static TaskType TASK_TYPE_2 = TaskType.values()[1];
    public final static Integer PRIORITY_1 = 1;
    public final static Integer PRIORITY_2 = 2;
    public final static TaskVO TASK_VO_1 = new TaskVO(TASK_ID_1, TASK_NAME_1, CREATION_TIME_1, TASK_STATUS_1, TASK_TYPE_1, PRIORITY_1);
    public final static TaskVO TASK_VO_2 = new TaskVO(TASK_ID_2, TASK_NAME_2, CREATION_TIME_2, TASK_STATUS_2, TASK_TYPE_2, PRIORITY_2);


    @Test
    public void addTest() throws DaoException {
        Task task = new Task();
        task.setName(TASK_NAME_1);
        task.setCreationTime(CREATION_TIME_1);
        task.setStatus(TASK_STATUS_1);
        task.setType(TASK_TYPE_1);
        task.setPriority(PRIORITY_1);
        Long taskId = taskDao.add(task);

        //cleaning the cache of hibernate
        taskDao.getSession().flush();
        taskDao.getSession().clear();
        //------------------------------

        assertNotNull(taskDao.get(taskId));
        assertEquals(task, taskDao.get(taskId));

    }

    @Test
    public void getTaskByIdTest() throws TaskDaoException {
        assertEquals(TASK_VO_1, taskDao.getTaskById(TASK_ID_1));

    }

    @Test
    public void getAllTasksTest() throws TaskDaoException {
        List<TaskVO> taskList = new ArrayList<>();
        taskList.add(TASK_VO_1);
        taskList.add(TASK_VO_2);
        assertEquals(taskList, taskDao.getAllTasks());

    }

    @Test
    public void getTasksByPerformerIdTest() throws TaskDaoException {
        List<TaskVO> taskList = new ArrayList<>();
        taskList.add(TASK_VO_1);
        assertEquals(taskList, taskDao.getTasksByPerformerId(TASK_ID_1));

    }

    @Test
    public void findAllAvailableTaskTest() throws TaskDaoException {
        List<TaskVO> taskList = new ArrayList<>();
        taskList.add(TASK_VO_1);
        assertEquals(taskList, taskDao.findAllAvailableTask());

    }

    @Test
    public void changeTaskStatusTest() throws DaoException, TaskDaoException {
        TaskVO newTaskStatusVO = new TaskVO();
        newTaskStatusVO.setId(TASK_ID_1);
        newTaskStatusVO.setStatus(TASK_STATUS_2);
        assertEquals(TASK_VO_1, taskDao.getTaskById(TASK_ID_1));

        //cleaning the cache of hibernate
        taskDao.getSession().flush();
        taskDao.getSession().clear();
        //------------------------------

        taskDao.changeTaskStatus(newTaskStatusVO);
        assertEquals(TASK_STATUS_2, taskDao.getTaskById(TASK_ID_1).getStatus());
    }

    @Test
    public void removeTest() throws TaskDaoException, DaoException {
        assertNotNull(taskDao.get(TASK_ID_1));
        taskDao.removeTask(TASK_ID_1);
        assertNull(taskDao.get(TASK_ID_1));
    }
}
