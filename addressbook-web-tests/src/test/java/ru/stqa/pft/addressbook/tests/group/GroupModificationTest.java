package ru.stqa.pft.addressbook.tests.group;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void beforeMethod(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0){
            app.group().create(new GroupData("group1", null, null));
        }
    }

    @Test
    public void testModificationGroup(){
        List<GroupData> before = app.group().list();
        int index = before.size()-1;
        GroupData group = new GroupData(before.get(index).getId(),"group2", "group22", "group222");
        app.group().modify(index, group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        app.group().sortById(before, after);
        Assert.assertEquals(before, after);
    }

}
