package de.matejo.integrationhttp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.dsl.Http;

@Configuration
public class PostDataFlow {

    public static final String REPLY_CHANNEL = "replyChannel";

    @Qualifier(REPLY_CHANNEL)
    @Bean
    public DirectChannel replyChannel() {
        return new DirectChannel();
    }

    @Bean
    IntegrationFlow postData(@Qualifier(REPLY_CHANNEL) DirectChannel replyChannel) {
        return IntegrationFlows
                .from(Http
                        .inboundGateway("/helloworld")
                        .replyChannel(replyChannel) //  add direct channel here
                        .requestPayloadType(String.class)
                        .requestMapping(m -> m
                                .consumes(MediaType.APPLICATION_JSON_VALUE)
                                .methods(HttpMethod.POST))
                .statusCodeFunction(re -> HttpStatus.OK))
                .channel(replyChannel)
                .get();
    }
}
