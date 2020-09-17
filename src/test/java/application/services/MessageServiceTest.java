package application.services;

import application.entities.message.Message;
import application.entities.user.User;
import application.repositories.IMessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MessageServiceTest {

    @Mock
    private IMessageRepository messageRepository;
    @Mock
    private FileService fileService;
    @Mock
    private UserService userService;
    @InjectMocks
    private MessageService messageService;

    @Test
    void builderTest() {
        Optional<Message> message = messageService.buildMessage("test text",
                "test tag", null, new User());

        assertTrue(message.isPresent());
    }

    @Test
    void buildWithNullUser() {
        Optional<Message> message = messageService.buildMessage("test text",
                "test tag", null, null);
        assertFalse(message.isPresent());
    }
}
