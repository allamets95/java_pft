package lessons.tests.addressbook.tests;


import com.thoughtworks.xstream.XStream;
import lessons.tests.addressbook.model.GroupData;
import lessons.tests.addressbook.model.Groups;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GroupCreationTests extends TestBase {

    @DataProvider

    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
        return groups.stream().map((g)->new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
        if (!app.group().isThereAGroup()) app.group().createGroup(group);
        Groups before = app.group().allg();
        app.group().createGroup(group);
        Groups after = app.group().allg();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));


    }

    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        GroupData group = new GroupData().withName("test222").withHeader("test1").withFooter("test2'");
        if (!app.group().isThereAGroup()) app.group().createGroup(group);
        Groups before = app.group().allg();
        app.group().createGroup(group);
        assertThat(app.group(). сount(), equalTo(before.size()));
        Groups after = app.group().allg();
        assertThat(after, equalTo(before));

    }
}