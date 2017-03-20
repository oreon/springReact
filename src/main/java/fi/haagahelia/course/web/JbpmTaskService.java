package fi.haagahelia.course.web;

import org.springframework.stereotype.Service;

import java.net.URL;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.TaskSummary;
import org.kie.services.client.api.RemoteRuntimeEngineFactory;

/**
 * Created by singj2b on 3/16/2017.
 */
@Service
public class JbpmTaskService {

    private TaskService taskService;

    public TaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    JbpmTaskService(){

        String user = "krisv";
        String password = "krisv";
        URL serverRestUrl = null;

        try {
             serverRestUrl = new URL("http://localhost:8080/jbpm-console");
        }catch (Exception ex){

        }
        // String user = "krisv";
        String deploymentId = "demo:oneprocess:1.1";

        RuntimeEngine engine = RemoteRuntimeEngineFactory.newRestBuilder().addUrl(serverRestUrl)
                .addTimeout(5)
                .addDeploymentId(deploymentId).addUserName("krisv").addPassword(password)


                // if you're sending custom class parameters, make sure that
                // the remote client instance knows about them!
                // .addExtraJaxbClasses(MyType.class)
                .build();

        // Create KieSession and TaskService instances and use them
        KieSession ksession = engine.getKieSession();
        taskService = engine.getTaskService();
    }


}
