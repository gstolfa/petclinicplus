package net.larsbehnke.petclinicplus.jpa.populator;

import java.util.Collection;

import net.larsbehnke.petclinicplus.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class XmlUserDataReaderTest {

    private XmlUserDataReader reader;

    @Before
    public void setUpTest() {
        reader = new XmlUserDataReader();
    }

    @Test
    public void testRead() {
        reader.setInitialUsersFile("src/main/webapp/WEB-INF/initialusers.xml");
        Collection<User> users = reader.read();
        Assert.assertEquals(users.size(), 3);
      
        
    }
}
