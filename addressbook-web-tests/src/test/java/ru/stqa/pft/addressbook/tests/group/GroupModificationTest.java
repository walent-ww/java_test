package ru.stqa.pft.addressbook.tests.group;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void beforeMethod(){
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("group1"));
        }

       /* if (app.group().list().size() == 0){
            app.group().create(new GroupData().withName("group1"));
        }*/
    }

    @Test
    public void testModificationGroup(){
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().
                withId(modifiedGroup.getId()).withName("group2").withFooter("group22").withHeader("group222");
        app.group().modify(group);
        Assert.assertEquals(app.group().count(), before.size());
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }

}
