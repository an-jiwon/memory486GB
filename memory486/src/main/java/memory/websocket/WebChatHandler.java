package memory.websocket;
import java.util.*;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebChatHandler extends TextWebSocketHandler { 
	
	// 접속한 클라이언트들의 Session을 저장 할 객체 생성 
	Map<String, WebSocketSession> users = new HashMap<String, WebSocketSession>(); 
	// 클라이언트가 연결될 때 호출되는 메소드 
	// 클라이언트를 Map에 저장 
	
	public void afterConnectionEstablished(WebSocketSession session){ 
		users.put(session.getId(), session); } 
	// 클라이언트의 연결이 해제될 때 호출되는 메소드 
	// Map에서 제거 
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status){ 
		users.remove(session.getId()); 
	}
	// 클라이언트에서 메시지가 왔을 때 호출되는 메소드 
	// 메시지를 모든 클라이언트에게 전송 
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 전송되어 온 메시지 
		String msg = message.getPayload(); 
		// 앞의 4글자를 제외한 부분을 가지고 메시지 만들기 
		TextMessage tMsg = new TextMessage(msg.substring(4)); 
		// Map의 모든 Value를 가져오기 
		Collection<WebSocketSession> list = users.values(); 
		// set의 모든 구성 요소에 mes를 전송
		for(WebSocketSession wss : list) {
			wss.sendMessage(tMsg); 
		} 
	}
}