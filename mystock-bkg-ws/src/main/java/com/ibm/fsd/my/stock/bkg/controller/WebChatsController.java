package com.ibm.fsd.my.stock.bkg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.fsd.my.stock.bkg.server.WebChatsServer;
import com.ibm.fsd.my.stock.bkg.server.WebSocketServer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "WebChatsController", description = "Web chats controller")
@RequestMapping(value = "/api/chat")
public class WebChatsController {

    @GetMapping("index")
    @ApiOperation(notes = "首页", value = "index")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("当前用户人数："+WebChatsServer.getOnlineCount());
    }

    @GetMapping("page")
    @ApiOperation(notes = "页面", value = "websocket")
    public ModelAndView page(){
        return new ModelAndView("websocket");
    }
    
    @GetMapping("users")
    @ApiOperation(notes = "页面", value = "websocket")
    public ResponseEntity<List<String>> users(){
    	List<String> userlist = new ArrayList<String>();
    	userlist = WebSocketServer.getAllUsersID();
    	return ResponseEntity.ok(userlist);
    }

    @PostMapping("/push/{toUserId}")
    @ApiOperation(notes = "发送消息给{toUserId}", value = "push")
    public ResponseEntity<String> pushToWeb(String message, @PathVariable String toUserId) throws IOException {
        WebSocketServer.sendInfo(message,toUserId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }
}
