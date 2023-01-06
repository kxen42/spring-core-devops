package guru.springframework.test.ds;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 5/21/16.
 */
@Component
@Profile("qa2")
public class QA2DataSource implements FakeDataSource {

    @Override
    public String getConnectionInfo() {
        return "I'm the QA2 Datasource";
    }
}
