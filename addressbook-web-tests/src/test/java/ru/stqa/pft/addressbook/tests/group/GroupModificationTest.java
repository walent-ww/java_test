package ru.stqa.pft.addressbook.tests.group;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

public class GroupModificationTest extends TestBase {

    @Test
    public void testModificationGroup(){
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("group2", "group22", "group222"));
        app.getGroupHelper().submitGroupMoification();
        app.getGroupHelper().returnGroupPage();
    }


}
