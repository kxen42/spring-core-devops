package guru.springframework.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import guru.springframework.test.jms.FakeJmsBroker;

/**
 * Created by jt on 5/7/16.
 */


@Configuration
public class SpringBootJavaConfig {
    @Value("${guru.jms.server}")
    String jmsServer;

    @Value("${guru.jms.port}")
    Integer jmsPort;

    @Value("${guru.jms.user}")
    String jmsUser;

    @Value("${guru.jms.password}")
    String jmsPassword;

    @Bean
    public FakeJmsBroker fakeJmsBroker() {
        FakeJmsBroker fakeJmsBroker = new FakeJmsBroker();
        fakeJmsBroker.setUrl(jmsServer);
        fakeJmsBroker.setPort(jmsPort);
        fakeJmsBroker.setUser(jmsUser);
        fakeJmsBroker.setPassword(jmsPassword);
        return fakeJmsBroker;
    }
}
