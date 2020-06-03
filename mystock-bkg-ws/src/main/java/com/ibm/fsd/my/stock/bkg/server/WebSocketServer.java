package com.ibm.fsd.my.stock.bkg.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ibm.fsd.my.stock.bkg.config.RabbitmqConfig;
import com.ibm.fsd.my.stock.bkg.feign.FeignClientService;

@ServerEndpoint("/wsserver/{userId}")
@Component
public class WebSocketServer {
		
		@Autowired
		private FeignClientService client;
	    static Log log=LogFactory.getLog(WebSocketServer.class);
	    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
	    private static int onlineCount = 0;
	    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
	    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<String,WebSocketServer>();
	    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
	    private Session session;
	    /**接收userId*/
	    private String userId="";
	    /**
	     * 连接建立成功调用的方法
	     * @param session
	     * @param userId
	     */
	    @OnOpen
	    public void onOpen(Session session, @PathParam("userId") String userId) {
	        this.session = session;
	        this.userId=userId;
	        if(webSocketMap.containsKey(userId)){
	            webSocketMap.remove(userId);
	            webSocketMap.put(userId,this);
	            //加入set中
	        }else{
	            webSocketMap.put(userId,this);
	            //加入set中
	            addOnlineCount();
	            //在线数加1
	        }

	        log.info("用户连接:"+userId+",当前在线人数为:" + getOnlineCount());

	        try {
	            sendMessage("连接成功");
	        } catch (IOException e) {
	            log.error("用户:"+userId+",网络异常!!!!!!");
	        }
	    }

	    /**
	     * 连接关闭调用的方法
	     */
	    @OnClose
	    public void onClose() {
	        if(webSocketMap.containsKey(userId)){
	            webSocketMap.remove(userId);
	            //从set中删除
	            subOnlineCount();
	        }
	        log.info("用户退出:"+userId+",当前在线人数为:" + getOnlineCount());
	    }

	    /**
	     * 收到客户端消息后调用的方法
	     *
	     * @param message 客户端发送过来的消息*/
	    @OnMessage
	    public void onMessage(String message, Session session) {
	        log.info("用户消息:"+userId+",报文:"+message);
	        //可以群发消息
	        //消息保存到数据库、redis
	        if(!StringUtils.isEmpty(message)){
	            try {
	                //解析发送的报文
	                JSONObject jsonObject = JSON.parseObject(message);
	                //追加发送人(防止串改)
	                jsonObject.put("fromUserId",this.userId);
	                String toUserId=jsonObject.getString("toUserId");
	                //传送给对应toUserId用户的websocket
	                if(!StringUtils.isEmpty(toUserId)&&webSocketMap.containsKey(toUserId)){
	                    webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
	                }else{
	                    log.error("请求的userId:"+toUserId+"不在该服务器上");
	                    //否则不在这个服务器上，发送到mysql或者redis
	                }
	            }catch (Exception e){
	                e.printStackTrace();
	            }
	        }
	    }

	    /**
	     * 连接建立有错误调用的方法
	     * @param session
	     * @param error
	     */
	    @OnError
	    public void onError(Session session, Throwable error) {
	    	try {
		        log.error("用户错误:"+userId+",原因:"+error.getMessage());
	    	}
	    	catch (Exception ex) {
	    		ex.printStackTrace();
	    	}
	    }
	    /**
	     * 实现服务器主动推送
	     */
	    public void sendMessage(String message) throws IOException {
	    	if (this.session != null) {
		        this.session.getBasicRemote().sendText(message);
	    	}
	    }


	    /**
	     * 发送自定义消息
	     */
	    public static void sendInfo(String message,@PathParam("userId") String userId) throws IOException {
	        log.info("发送消息到:"+userId+"，报文:"+message);
	        if(!StringUtils.isEmpty(userId) && webSocketMap.containsKey(userId)){
	            webSocketMap.get(userId).sendMessage(message);
	        }else{
	            log.error("用户"+userId+",不在线！");
	        }
	    }

	    public static synchronized int getOnlineCount() {
	        return onlineCount;
	    }

	    public static synchronized void addOnlineCount() {
	        WebSocketServer.onlineCount++;
	    }

	    public static synchronized void subOnlineCount() {
	        WebSocketServer.onlineCount--;
	    }
	    public static List<String> getAllUsersID() {
	    	List<String> returnList = new ArrayList<String>();
	    	Enumeration<String> keyEnm = WebSocketServer.webSocketMap.keys();
	    	while (keyEnm.hasMoreElements()) {
	    		returnList.add(keyEnm.nextElement());
	    	}
	    	return returnList;
	    }
	    
	    @RabbitListener(queues = RabbitmqConfig.QUEUE_A)
	    public void consumeMqMsgQueue(@Payload byte[] body) {
	    	try {
	    		String message = new String(body, "UTF-8");
	    		log.info("从mq获取Msg:"+ message);
	    		this.sendMessage(message);
	    	} catch(Exception ex) {
	    		log.error("从mq接收Msg报错:"+ ex.getMessage());
	    	}
	    }
}
