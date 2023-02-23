package example.shared.streaming;

import example.process.dto.StartProcessDto;
import io.muenchendigital.digiwf.message.process.impl.dto.CorrelateMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class Consumers {

    @Bean
    public Consumer<Message<Object>> onTechnicalErrorConsumer() {
        return message -> log.warn("Technical Error: {}", message.toString());
    }

    @Bean
    public Consumer<Message<Object>> onIncidentConsumer() {
        return message -> log.warn("Incident:  {}", message.toString());
    }

    @Bean
    public Consumer<Message<StartProcessDto>> startProcessConsumer() {
        return message -> log.info(message.toString());
    }

    @Bean
    public Consumer<Message<CorrelateMessageDto>> correlateMessageConsumer() {
        return message -> {
            if (message.getPayload().getPayloadVariables() == null) {
                // incident
                throw new RuntimeException("No variables defined.");

                // technical error
//                throw new TechnicalError(message.getPayload().getProcessInstanceId(), "400", "No variables defined.");
            }
            log.info(message.getPayload().toString());
        };
    }

}
