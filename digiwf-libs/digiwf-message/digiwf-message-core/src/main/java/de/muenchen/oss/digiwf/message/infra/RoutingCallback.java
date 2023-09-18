package de.muenchen.oss.digiwf.message.infra;

import de.muenchen.oss.digiwf.message.common.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.function.context.MessageRoutingCallback;
import org.springframework.messaging.Message;

import java.util.Map;

import static de.muenchen.oss.digiwf.message.infra.EventEmitterConfiguration.FUNCTION_ROUTING_ERROR;
import static de.muenchen.oss.digiwf.message.infra.EventEmitterConfiguration.MISSING_TYPE_HEADER_ERROR;

@RequiredArgsConstructor
public class RoutingCallback implements MessageRoutingCallback {

    private final Map<String, String> typeMappings;

    /**
     * Router for messages.
     * Either routes the message to the corresponding function (if present), or, in case the TYPE header is unknown or unset,
     * to the corresponding error functions.
     * unknown or unset.
     *
     * @param message incoming message
     * @return FunctionRoutingResult as described above
     */
    @Override
    public FunctionRoutingResult routingResult(final Message<?> message) {
        final String functionDefinition;

        if (message.getHeaders().containsKey(MessageConstants.TYPE)) {
            final String header = (String) message.getHeaders().get(MessageConstants.TYPE);
            functionDefinition = this.typeMappings.getOrDefault(header, FUNCTION_ROUTING_ERROR);
        } else {
            functionDefinition = MISSING_TYPE_HEADER_ERROR;
        }

        return new FunctionRoutingResult(functionDefinition);
    }

}
