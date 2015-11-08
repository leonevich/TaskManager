package by.manager.task.management.service.scheduler;

import by.manager.task.management.service.executor.TaskExecutor;
import by.manager.task.management.service.task.TaskService;
import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.errors.exceptions.executor.TaskExecutorException;
import by.manager.task.utils.errors.exceptions.executor.read.ReadExecutorException;
import by.manager.task.utils.errors.exceptions.task.service.TaskServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

@Component
public class ScheduledCallExecutor {
    private static Logger log = Logger.getLogger(ScheduledCallExecutor.class);

    @Autowired
    @Qualifier("taskExecutorImpl")
    private TaskExecutor taskExecutor;

    @Autowired
    private TaskService taskService;

    @Scheduled(fixedRate = 3000)
    public void pollDatabase() {
        List<TaskVO> taskList = null;
        try {
            taskList = taskService.findAllAvailableTask();
            log.info("Find all available tasks");
        } catch (TaskServiceException e) {
            log.error("Can't get list of available tasks " + e.getMessage());
        }
        if (taskList != null) {
            for (TaskVO currentTask : taskList) {
                try {
                    taskExecutor.executeTask(currentTask);
                } catch (ReadExecutorException e) {
                    log.error("Can't call read executor for task " + currentTask);
                } catch (TaskExecutorException e) {
                    log.error("Can't call executor for task " + currentTask);
                }
            }
        }
    }
}


