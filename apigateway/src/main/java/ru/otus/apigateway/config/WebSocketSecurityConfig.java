package ru.otus.apigateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

import static org.springframework.messaging.simp.SimpMessageType.*;

@Configuration
public class WebSocketSecurityConfig
        extends AbstractSecurityWebSocketMessageBrokerConfigurer {
    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                //.nullDestMatcher().authenticated()
                //.simpDestMatchers().permitAll()  //.authenticated()  //.hasRole("USER")
                .simpDestMatchers("/client-websocket","/taxi/**","/client/**").permitAll()  // .hasRole("USER")
                .simpSubscribeDestMatchers("/taxi/**", "/client/**").permitAll()    //.hasRole("USER")
                .simpTypeMatchers(CONNECT, UNSUBSCRIBE, DISCONNECT).permitAll()

        //.simpTypeMatchers(MESSAGE, SUBSCRIBE).denyAll()
                /*.anyMessage().denyAll()*/;
    }
    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}