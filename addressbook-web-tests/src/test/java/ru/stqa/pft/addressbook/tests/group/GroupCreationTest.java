package ru.stqa.pft.addressbook.tests.group;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData("group1", null, null);
        app.group().create(group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(),before.size() + 1);

        int max1 = after.stream().max((g1, g2) -> Integer.compare(g1.getId(), g2.getId())).get().getId();
        group.setId(max1);
        before.add(group);
        app.group().sortById(before, after);
        Assert.assertEquals(before, after);
    }

}
