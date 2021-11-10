package guru.test.config.external.props;

import guru.springframework.test.jms.FakeJmsBroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

/**
 * Demo Spring 4 {@code @PropertySources} and {@code @PropertySource}
 * <p>
 * Created by jt on 5/7/16.
 * Renamed by kxenson on 11/09/21.
 */
@Configuration
@PropertySources({
    @PropertySource("classpath:testing.properties"),
    @PropertySource("classpath:encrypted-testing.properties")
})
public class ExternalPropsMultiFileSpring4Config {

    @Autowired
    Environment env;

    @Bean
    public FakeJmsBroker fakeJmsBrokerMultiS4() {
        FakeJmsBroker fakeJmsBroker = new FakeJmsBroker();
        fakeJmsBroker.setUrl(env.getProperty("guru.jms.server"));
        fakeJmsBroker.setPort(env.getRequiredProperty("guru.jms.port", Integer.class));
        fakeJmsBroker.setUser(env.getProperty("guru.jms.user"));
        fakeJmsBroker.setPassword(env.getProperty("guru.jms.encrypted.password"));
        return fakeJmsBroker;
    }
}