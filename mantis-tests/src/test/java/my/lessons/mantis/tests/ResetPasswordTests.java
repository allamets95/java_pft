package my.lessons.mantis.tests;

import my.lessons.mantis.app.HttpSession;
import my.lessons.mantis.model.MailMessage;
import my.lessons.mantis.model.UserData;
import my.lessons.mantis.model.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class ResetPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testResetPassword() throws IOException, MessagingException {
        Users users = app.db().users();
        UserData user = users.iterator().next().withPassword(app.getProperty("web.testPassword"));

        app.session().loginAsAdministrator();
        app.navigateTo().manageUsersPage();
        app.users().resetPasswordForUserById(user.getId());

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 30000);
        String address = findActivationLink(mailMessages, user.getEmail());
        app.navigateTo().reactivation(address);
        assertTrue(app.session().isLoggedInAs(user.getUserName()));

        String newPassword = "passwordnew";
        app.users().setNewPassword(newPassword);
        UserData userModified = new UserData(user).withPassword(newPassword);

        HttpSession session = app.newSession();
        assertFalse(session.login(user.getUserName(), user.getPassword()));
        assertFalse(session.isLoggedInAs(user.getUserName()));
        assertTrue(session.login(userModified.getUserName(), userModified.getPassword()));
        assertTrue(session.isLoggedInAs(userModified.getUserName()));

    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

    private String findActivationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mails = mailMessages.stream().filter((m)-> m.to.equals(email)).findAny().get();
        VerbalExpression expression = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return expression.getText(mails.text);
    }
}
