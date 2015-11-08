package by.manager.task.web;


import by.manager.task.management.facade.TaskManagerFacade;
import by.manager.task.utils.beans.PerformerVO;
import by.manager.task.utils.enums.PerformerStatus;
import by.manager.task.utils.enums.PerformerType;
import by.manager.task.utils.errors.exceptions.performer.PerformerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class PerformerController {

    @Autowired
    private TaskManagerFacade taskManagerFacade;

    @RequestMapping(value = "/add/performer", method = RequestMethod.POST)
    public String addPerformer(@ModelAttribute("performer") PerformerVO performerVO,
                               Map<String, Object> map) {
        try {
            taskManagerFacade.addPerformer(performerVO);
        } catch (PerformerException e) {
            return "error404";
        }
        map.put("statusValues", PerformerStatus.values());
        map.put("typeValues", PerformerType.values());
        map.put("performerVO", new PerformerVO());
        try {
            map.put("performerList", taskManagerFacade.getAllPerformers());
        } catch (PerformerException e) {
            return "error404";
        }
        return "redirect:/index";
    }


    @RequestMapping("/delete/performer/{performerId}")
    public String deletePerformer(@PathVariable("performerId") Long performerId, Map<String, Object> map) {
        try {
            taskManagerFacade.removePerformer(performerId);
        } catch (PerformerException e) {
            return "error404";
        }
        map.put("statusValues", PerformerStatus.values());
        map.put("typeValues", PerformerType.values());
        map.put("performerVO", new PerformerVO());
        try {
            map.put("performerList", taskManagerFacade.getAllPerformers());
        } catch (PerformerException e) {
            return "error404";
        }
        return "redirect:/index";
    }

    @RequestMapping("/edit/performer/{performerId}")
    public String editPerformer(@PathVariable("performerId") Long performerId, Map<String, Object> map) {

        try {
            PerformerVO performerVO = taskManagerFacade.getPerformer(performerId);
            map.put("statusValues", PerformerStatus.values());
            map.put("typeValues", PerformerType.values());
            map.put("performerVO", performerVO);
        } catch (PerformerException e) {
            return "error404";
        }
        return "edit_performer";
    }

    @RequestMapping("/update/performer")
    public String updatePerformer(@ModelAttribute("task") PerformerVO performerVO) {

        try {
            taskManagerFacade.updatePerformer(performerVO);
        } catch (PerformerException e) {
            return "error404";
        }

        return "redirect:/index";
    }
}
