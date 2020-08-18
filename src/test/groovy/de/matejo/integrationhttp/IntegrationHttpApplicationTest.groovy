package de.matejo.integrationhttp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class IntegrationHttpApplicationTest extends Specification {

    @Autowired
    private IntegrationHttpApplication application

    def "application start works"() {
        expect:
        application != null
    }
}
