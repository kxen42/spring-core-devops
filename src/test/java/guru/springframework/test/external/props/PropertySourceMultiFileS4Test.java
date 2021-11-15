package guru.springframework.test.external.props;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import guru.springframework.test.jms.FakeJmsBroker;
import guru.test.config.external.props.ExternalPropsMultiFileSpring4Config;

/**
 * Test loading FakeJmsBroker bean given ExternalPropsMultiFileSpring4Config.
 * <p>
 * Created by jt on 5/7/16.
 */
@SpringBootTest(classes = ExternalPropsMultiFileSpring4Config.class)
public class PropertySourceMultiFileS4Test {

    @Autowired
    FakeJmsBroker fakeJmsBroker;

    @Test
    public void testPropsSet() {
        assertEquals("10.10.10.123", fakeJmsBroker.getUrl());
        assertEquals(3330, fakeJmsBroker.getPort().intValue());
        assertEquals("Ron", fakeJmsBroker.getUser());
        assertEquals("&%$)(*&#^!@!@#$", fakeJmsBroker.getPassword());
    }
}