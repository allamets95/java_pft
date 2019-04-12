package my.lessons.mantis.tests;
import my.lessons.mantis.model.Issue;
import my.lessons.mantis.model.Project;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException,  RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project: projects){
            System.out.println(project.getName());
        }

    }

    @Test
    public void testCreateIssue() throws RemoteException, javax.xml.rpc.ServiceException, MalformedURLException {
        Set<Project> projects=app.soap().getProjects();
        Issue issue =new Issue().withSummary("Test issue").withDescription("Test issue description").withProject(projects.iterator().next());
        Issue created=app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }

}