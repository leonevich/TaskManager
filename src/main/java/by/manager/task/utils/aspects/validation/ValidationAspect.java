package by.manager.task.utils.aspects.validation;


import by.manager.task.utils.beans.TaskVO;
import by.manager.task.utils.errors.exceptions.validation.ValidationAspectException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@Aspect
public class ValidationAspect {

    public ValidationAspect() {
    }

  /*  @Around("execution (* by.manager.task.web.TaskManagerController.addPerformer(..)) && args(taskVO, performerId,result)")
    public String setCreationTaskTimeAspect(ProceedingJoinPoint pjp, TaskVO taskVO, Long performerId, BindingResult result) throws ValidationAspectException {
        try {
            pjp.proceed(new Object[]{taskVO, performerId});
        } catch (Throwable e) {
            throw new ValidationAspectException(e);
        }

        return null;

    }*/
}
