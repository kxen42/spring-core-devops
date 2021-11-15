package guru.springframework.test.external.props;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

import guru.springframework.SpringCoreDevOpsApplication;
import guru.springframework.test.jms.FakeJmsBroker;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by jt on 5/7/16.
 */
// TODO: haven't got this working with Spring Boot 2.5.6
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {SpringCoreDevOpsApplication.class})
@SpringBootTest(classes = {SpringCoreDevOpsApplication.class})
@TestPropertySource("/application.properties")
public class SpringBootPropertiesTest {
    @Autowired
    FakeJmsBroker fakeJmsBroker;

    @Test
    public void testPropsSet() {
        assertEquals("10.10.10.123", fakeJmsBroker.getUrl());
        assertEquals(3330, fakeJmsBroker.getPort().intValue());
        assertEquals("Ron", fakeJmsBroker.getUser());
        assertEquals("Burgundy", fakeJmsBroker.getPassword());
    }
}
