package de.matejo.integrationhttp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.*
import org.springframework.web.util.UriComponentsBuilder
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostDataFlowTest extends Specification {

    @LocalServerPort
    private int port

    @Autowired
    private TestRestTemplate restTemplate

    def "first test"() {
        given: "a url"
        def url = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .port(port)
                .path("/helloworld")
                .build()
                .toUri()

        and: "a request entity"
        def requestHeaders = new HttpHeaders()
        requestHeaders.setContentType(MediaType.APPLICATION_JSON)
        def requestEntity = new HttpEntity<String>("heiöehef", requestHeaders)

        when: "request is sent"
        // def response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class)

        then: "status code is OK"
        //response.statusCodeValue == HttpStatus.OK.value()
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).statusCode == HttpStatus.OK
    }
}
