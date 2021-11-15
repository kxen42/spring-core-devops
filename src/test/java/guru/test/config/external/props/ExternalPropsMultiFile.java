package guru.test.config.external.props;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import guru.springframework.test.jms.FakeJmsBroker;

/**
 * Demo files in simple list
 * <p>
 * Created by jt on 5/7/16.
 */
@Configuration
@PropertySource({"classpath:testing.properties", "classpath:encrypted-testing.properties"})
public class ExternalPropsMultiFile {

    @Autowired
    Environment env;

    @Bean
    public FakeJmsBroker fakeJmsBrokerMulti() {
        FakeJmsBroker fakeJmsBroker = new FakeJmsBroker();
        fakeJmsBroker.setUrl(env.getProperty("guru.jms.server"));
        fakeJmsBroker.setPort(env.getRequiredProperty("guru.jms.port", Integer.class));
        fakeJmsBroker.setUser(env.getProperty("guru.jms.user"));
        fakeJmsBroker.setPassword(env.getProperty("guru.jms.encrypted.password"));
        return fakeJmsBroker;
    }
}
