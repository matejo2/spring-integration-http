package de.matejo.integrationhttp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.dsl.Http;

@Configuration
public class PostDataFlow {

    @Bean
    IntegrationFlow postData() {
        return IntegrationFlows
                .from(Http
                        .inboundGateway("/helloworld")
                        // .replyChannel() add direct channel here
                        .requestPayloadType(String.class)
                        .requestMapping(m -> m
                                .consumes(MediaType.APPLICATION_JSON_VALUE)
                                .methods(HttpMethod.POST))
                .statusCodeFunction(re -> HttpStatus.OK))
                //.channel()
                .get();
    }
}
