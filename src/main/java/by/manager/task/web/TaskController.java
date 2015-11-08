package by.manager.task.web;

import by.manager.task.management.facade.TaskManagerFacade;
import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.enums.TaskStatus;
import by.manager.task.utils.enums.TaskType;
import by.manager.task.utils.errors.exceptions.task.TaskException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("/performer/{performerId}")
public class TaskController {

    @Autowired
    private TaskManagerFacade taskManagerFacade;


    @RequestMapping
    public String getTaskByPerformerId(@PathVariable("performerId") Long performerId, Map<String, Object> map) {
        try {
            map.put("taskList", taskManagerFacade.getTasksByPerformerId(performerId));
            map.put("listOfAvailablePriorityTask", taskManagerFacade.getAvailablePriorityTask(performerId));
            map.put("statusValues", TaskStatus.values());
            map.put("typeValues", TaskType.values());
            map.put("task", new TaskVO());
            map.put("performerId", performerId);
        } catch (TaskException e) {
            return "error404";
        }

        return "performer";
    }

    @RequestMapping(value = "/add/task", method = RequestMethod.POST)
    public String addTask(@ModelAttribute("task") TaskVO taskVO, @PathVariable("performerId") Long performerId, Map<String, Object> map) {

        try {
            taskManagerFacade.addTask(taskVO, performerId);
        } catch (TaskException e) {
            return "error404";
        }

        return "redirect:/performer/" + performerId;
    }

    @RequestMapping(value = "/edit/task/update", method = RequestMethod.POST)
    public String updateTask(@ModelAttribute("task") TaskVO taskVO, @PathVariable("performerId") Long performerId) {

        try {
            taskManagerFacade.updateTask(taskVO);
        } catch (TaskException e) {
            return "error404";
        }

        return "redirect:/performer/" + performerId;
    }

    @RequestMapping("/edit/task/{taskId}")
    public String editTask(@PathVariable Long taskId, @PathVariable Long performerId, Map<String, Object> map) {

        try {
            TaskVO taskVO = taskManagerFacade.getTask(taskId);
            map.put("listOfAvailablePriorityTask", taskManagerFacade.getAvailablePriorityTask(performerId));
            map.put("statusValues", TaskStatus.values());
            map.put("typeValues", TaskType.values());
            map.put("task", taskVO);
            map.put("performerId", performerId);
        } catch (TaskException e) {
            return "error404";
        }
        return "edit_task";
    }

    @RequestMapping("/delete/task/{taskId}")
    public String deleteTask(@PathVariable Long taskId, @PathVariable Long performerId, Map<String, Object> map) {
        map.put("taskVO", new TaskVO());
        try {
            taskManagerFacade.removeTask(taskId);
        } catch (TaskException e) {
            return "error404";
        }
        try {
            map.put("taskList", taskManagerFacade.getTasksByPerformerId(performerId));
        } catch (TaskException e) {
            return "error404";
        }
        return "redirect:/performer/" + performerId;
    }

    @RequestMapping("/sort/task")
    public String sortTask(@PathVariable Long taskId, @PathVariable Long performerId, Map<String, Object> map) {
        map.put("taskVO", new TaskVO());
        try {
            taskManagerFacade.removeTask(taskId);
        } catch (TaskException e) {
            return "error404";
        }
        try {
            map.put("taskList", taskManagerFacade.getTasksByPerformerId(performerId));
        } catch (TaskException e) {
            return "error404";
        }
        return "redirect:/performer/" + performerId;
    }
}
