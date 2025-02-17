package de.muenchen.oss.digiwf.message.example.message.service;

import de.muenchen.oss.digiwf.message.core.api.MessageApi;
import de.muenchen.oss.digiwf.message.example.message.dto.Message;
import de.muenchen.oss.digiwf.message.example.message.dto.MessageSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageApi sendMessageApi;

    private final String messageDestination = "dwf-message-example";

    public MessageSuccess sendMessage(final Message message) {
        // send a message to the destination
        final boolean success = this.sendMessageApi.sendMessage(message, Map.of("type", "logMessage"),  this.messageDestination);
            return new MessageSuccess(success, success ? "Message was successfully sent": "Sending message failed");
    }

}
