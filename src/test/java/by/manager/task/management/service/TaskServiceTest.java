package by.manager.task.management.service;

import by.manager.task.dao.domain.Performer;
import by.manager.task.dao.domain.Task;
import by.manager.task.dao.performer.PerformerDaoImpl;
import by.manager.task.dao.task.TaskDaoImpl;
import by.manager.task.management.service.task.TaskServiceImpl;
import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.enums.TaskStatus;
import by.manager.task.utils.enums.TaskType;
import by.manager.task.utils.errors.exceptions.dao.DaoException;
import by.manager.task.utils.errors.exceptions.task.dao.TaskDaoException;
import by.manager.task.utils.errors.exceptions.task.service.TaskServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @Mock
    private TaskDaoImpl taskDao;

    @Mock
    private PerformerDaoImpl performerDao;

    @InjectMocks
    private TaskServiceImpl taskService;


    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    public final static Long PERFORMER_ID = 1L;
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
    public final static int PRIORITY_1 = 1;
    public final static int PRIORITY_2 = 2;
    public final static TaskVO TASK_VO_1 = new TaskVO(TASK_ID_1, TASK_NAME_1, CREATION_TIME_1, TASK_STATUS_1, TASK_TYPE_1, PRIORITY_1);
    public final static TaskVO TASK_VO_2 = new TaskVO(TASK_ID_2, TASK_NAME_2, CREATION_TIME_2, TASK_STATUS_2, TASK_TYPE_2, PRIORITY_2);

    @Test(expected = TaskServiceException.class)
    public void getAllTasksTest() throws TaskDaoException, TaskServiceException {
        List<TaskVO> taskVOList = new ArrayList<>();
        taskVOList.add(TASK_VO_1);
        taskVOList.add(TASK_VO_2);
        List<TaskVO> resultList;

        when(taskDao.getAllTasks()).thenReturn(taskVOList);

        resultList = taskService.getAllTasks();
        assertEquals(taskVOList, resultList);

        verify(taskDao).getAllTasks();

        doThrow(new TaskDaoException()).when(taskDao).getAllTasks();
        taskService.getAllTasks();
    }

    @Test(expected = TaskServiceException.class)
    public void addTaskTest() throws DaoException, TaskServiceException {

        when(taskDao.add(any(Task.class))).thenReturn(TASK_ID_1);
        when(performerDao.load(anyLong())).thenReturn(new Performer());

        Long resultTaskId = taskService.addTask(TASK_VO_1, PERFORMER_ID);
        assertEquals(TASK_ID_1, resultTaskId);

        verify(taskDao).add(any(Task.class));
        verify(performerDao).load(anyLong());

        doThrow(new DaoException()).when(taskDao).add(any(Task.class));
        taskService.addTask(TASK_VO_1, PERFORMER_ID);
    }

    @Test(expected = TaskServiceException.class)
    public void getTasksByPerformerIdTest() throws TaskDaoException, TaskServiceException {
        List<TaskVO> taskVOList = new ArrayList<>();
        taskVOList.add(TASK_VO_1);
        List<TaskVO> resultList;

        when(taskDao.getTasksByPerformerId(anyLong())).thenReturn(taskVOList);

        resultList = taskService.getTasksByPerformerId(PERFORMER_ID);
        assertEquals(taskVOList, resultList);

        verify(taskDao).getTasksByPerformerId(anyLong());

        doThrow(new TaskDaoException()).when(taskDao).getTasksByPerformerId(anyLong());
        taskService.getTasksByPerformerId(PERFORMER_ID);
    }

    @Test(expected = TaskServiceException.class)
    public void removeTaskTest() throws TaskDaoException, TaskServiceException {
        taskService.removeTask(TASK_ID_1);
        verify(taskDao).removeTask(anyLong());

        doThrow(new TaskDaoException()).when(taskDao).removeTask(anyLong());
        taskService.removeTask(TASK_ID_1);
    }

    @Test(expected = TaskServiceException.class)
    public void findAllAvailableTaskTest() throws TaskDaoException, TaskServiceException {
        List<TaskVO> taskVOList = new ArrayList<>();
        taskVOList.add(TASK_VO_1);
        List<TaskVO> resultList;

        when(taskDao.findAllAvailableTask()).thenReturn(taskVOList);

        resultList = taskService.findAllAvailableTask();
        assertEquals(taskVOList, resultList);

        verify(taskDao).findAllAvailableTask();

        doThrow(new TaskDaoException()).when(taskDao).findAllAvailableTask();
        taskService.findAllAvailableTask();
    }

    @Test(expected = TaskServiceException.class)
    public void changeTaskStatusTest() throws TaskServiceException, TaskDaoException {
        taskService.saveTaskStatus(TASK_VO_1);
        verify(taskDao).saveTaskStatus(any(TaskVO.class));

        doThrow(new TaskDaoException()).when(taskDao).saveTaskStatus(any(TaskVO.class));
        taskService.saveTaskStatus(TASK_VO_1);
    }
}
