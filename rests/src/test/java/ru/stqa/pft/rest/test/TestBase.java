package ru.stqa.pft.rest.test;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import ru.stqa.pft.rest.model.ApplicationManager;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager();

    public boolean isIssueOpen(int issueId) throws IOException {
        String json = app.rest().getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/" + issueId + ".json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> iss = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
        //
        return (iss.iterator().next().getStatus().equals("Open")
                || iss.iterator().next().getStatus().equals("In Progress")
                || iss.iterator().next().getStatus().equals("Re-opened")
        );

    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}

