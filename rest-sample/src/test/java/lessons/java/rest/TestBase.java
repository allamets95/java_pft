package lessons.java.rest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.Set;


public class TestBase {
    org.slf4j.Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
    }

    public Set<Issue> getIssuesSet(String requestJson) {
        String json = RestAssured.get(requestJson).asString();
        JsonElement parsedJson = new JsonParser().parse(json);
        JsonElement issues = parsedJson.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    public Set<Issue> getIssue() throws IOException {
        return getIssuesSet("http://demo.bugify.com/api/issues.json");
    }

    public Issue getIssueById(int id) throws IOException {
        Issue issue = new Issue();
        getIssuesSet(String.format("http://demo.bugify.com/api/issues/%s.json", id))
                .stream().findAny().map((b) -> {
            return issue.withId(b.getId())
                    .withSubject(b.getSubject()).withDescription(b.getDescription())
                    .withStateName(b.getStateName());
        });

        return issue;
    }


    public int createIssue(Issue issue) throws IOException {
        String json = RestAssured.given()
                .parameter("subject", issue.getSubject())
                .parameter("description", issue.getDescription())
                .post("http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsedJson = new JsonParser().parse(json);
        return parsedJson.getAsJsonObject().get("issue_id").getAsInt();
    }


    public boolean isIssueOpen(int issueId) throws IOException {

        System.out.println("This issue has status: " + getIssueById(issueId).getStateName());
        if (getIssueById(issueId).getStateName().equals("Closed") || getIssueById(issueId).getStateName().equals("Resolved")) {
            return false;
        }

        return true;

    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            logger.info("This test was ignored! Because there is issue with ID " + issueId);
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}