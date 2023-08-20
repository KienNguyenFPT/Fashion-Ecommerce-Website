/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Raiku
 */
@ServerEndpoint("/chatmessenger/{roomId}")
public class MessengerWebSocketController {

    @OnMessage
    public String onMessage(String message) {
        return null;
    }
    
}
