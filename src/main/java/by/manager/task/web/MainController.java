package by.manager.task.web;

import by.manager.task.management.facade.TaskManagerFacade;
import by.manager.task.utils.beans.PerformerVO;
import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.enums.PerformerStatus;
import by.manager.task.utils.enums.PerformerType;
import by.manager.task.utils.enums.TaskStatus;
import by.manager.task.utils.enums.TaskType;
import by.manager.task.utils.errors.exceptions.performer.PerformerException;
import by.manager.task.utils.errors.exceptions.task.TaskException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class MainController {

    @Autowired
    private TaskManagerFacade taskManagerFacade;


    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String listPerformers(Map<String, Object> map) {
        map.put("statusValues", PerformerStatus.values());
        map.put("typeValues", PerformerType.values());
        map.put("performerVO", new PerformerVO());
        try {
            map.put("performerList", taskManagerFacade.getAllPerformers());
        } catch (PerformerException e) {
            return "error404";
        }

        return "performers";
    }

    @RequestMapping("/all/task")
    public String allTask(Map<String, Object> map) {
        try {
            map.put("taskList", taskManagerFacade.getAllTask());
        } catch (TaskException e) {
            return "error404";
        }
        return "tasks";
    }

    @RequestMapping(value = "/edit/task/update", method = RequestMethod.POST)

    public String updateTask(@ModelAttribute("task") TaskVO taskVO) {

        try {
            taskManagerFacade.updateTask(taskVO);
        } catch (TaskException e) {
            return "error404";
        }

        return "redirect:/all/task";
    }

    @RequestMapping("/delete/task/{taskId}")
    public String deleteTask2(@PathVariable Long taskId, Map<String, Object> map) {
        map.put("taskVO", new TaskVO());
        try {
            taskManagerFacade.removeTask(taskId);
        } catch (TaskException e) {
            return "error404";
        }
        try {
            map.put("taskList", taskManagerFacade.getAllTask());
        } catch (TaskException e) {
            return "error404";
        }
        return "redirect:/all/task";
    }

    @RequestMapping("/tasks/generator")
    public String tasksGenerator() {
        PerformerVO performerVO;
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 30; ++i) {
            if (i % 2 == 0) {
                performerVO = new PerformerVO("reader_" + (i + 1), PerformerType.READER, PerformerStatus.values()[random.nextInt(2)]);
            } else {
                performerVO = new PerformerVO("writer_" + (i + 1), PerformerType.WRITER, PerformerStatus.values()[random.nextInt(2)]);
            }
            try {
                taskManagerFacade.addPerformer(performerVO);
            } catch (PerformerException e) {
                e.printStackTrace();
            }
        }
        TaskVO taskVO;
        List<PerformerVO> performerVOList = null;
        try {
            performerVOList = taskManagerFacade.getAllPerformers();
        } catch (PerformerException e) {
            e.printStackTrace();
        }
        for (PerformerVO currentPerformer : performerVOList) {
            Long currentPerformerId = currentPerformer.getId();
            for (int k = 0; k < 20; ++k) {
                taskVO = new TaskVO("task_" + (k + 1) + "_performer_" + currentPerformerId, TaskStatus.NOT_STARTED, TaskType.values()[random.nextInt(2)], (k + 1));

                try {
                    taskManagerFacade.addTask(taskVO, currentPerformerId);
                } catch (TaskException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/index";
    }
}









