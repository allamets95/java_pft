package my.lessons.mantis.tests;


import my.lessons.mantis.model.MailMessage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class RegistrationTests extends TestBase {
    //@BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistrartion() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String password = "123456";
        String user = String.format("user%s", now);
        //String email = String.format("user%s@localhost", now);
        String email = String.format("user%s@localhost", now);
        app.james().createUser(user,password);
        app.registration().start(user, email);
        //List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        List<MailMessage>mailMessages = app.james().waitForMail(user,password,60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        app.newSession().login(user, password);
        Assert.assertTrue(app.newSession().login(user, password));


    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);

    }

    // @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
