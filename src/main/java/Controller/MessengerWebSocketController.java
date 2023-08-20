/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Raiku
 */
@ServerEndpoint("/chatmessenger/{roomId}")
public class MessengerWebSocketController {

    private static Map<String, Set<Session>> rooms = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") String room) {
        rooms.computeIfAbsent(room, key -> ConcurrentHashMap.newKeySet()).add(session);
    }

    @OnMessage
    public void onMessage(Session session, String message, @PathParam("roomId") String room) {
        broadcast(room, message);
    }

    @OnClose
    public void onClose(Session session, @PathParam("roomId") String room) {
        Set<Session> roomSessions = rooms.get(room);
        if (roomSessions != null) {
            roomSessions.remove(session);
        }
    }

    private void broadcast(String room, String message) {
        Set<Session> roomSessions = rooms.get(room);
        if (roomSessions != null) {
            roomSessions.forEach(session -> {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    throw new Error(e.getMessage());
                }
            });
        }
    }
    
}
