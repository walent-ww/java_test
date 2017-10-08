package ru.stqa.pft.rest.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by user on 08.10.17.
 */
public class RestTest extends TestBase{

    @Test
    public void testFixed() throws IOException {
        // на момент написания теста таск с id=1 в состоянии Resolved (Т.е можно проверять)
        int idIssue = 1;
        skipIfNotFixed(idIssue);
        assertFalse(isIssueOpen(idIssue));
    }

    @Test
    public void testNotFixed() throws IOException {
        // на момент написания теста таск с id=2 в состоянии In Progress (т.е не исправлен)
        int idIssue = 2;
        skipIfNotFixed(idIssue);
        assertTrue(isIssueOpen(idIssue));
    }

    @Test
    public void test() throws IOException {
        Set<Issue> issues = app.rest().getIssue();
        Issue issue = issues.iterator().next();
        skipIfNotFixed(issue.getId());
        assertTrue(isIssueOpen(issue.getId()));
    }


}
