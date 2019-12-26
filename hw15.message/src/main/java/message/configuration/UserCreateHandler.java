package message.configuration;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserCreateHandler extends TextWebSocketHandler {
    private List<WebSocketSession> establishedSessions = new CopyOnWriteArrayList<>();

    // Вызывается после установки соединения. Добавляет клиента в общий список.
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        establishedSessions.add(session);
    }

    // Вызывается после прерывания соединения. Удаляет клиента из списка.
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        establishedSessions.remove(session);
    }

    // Вызывается после получения сообщения. Рассылает его всем подключенным клиентам.
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {

    }
}
