package com.bing.rabbitmqtest.middleware.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * websocket应用
 * @author sunyibing
 * @date 2024/4/10
 */
@Controller
@RequestMapping(name = "websocket应用", path = "/websocket")
public class WebSocketController {

    @GetMapping(name = "", path = "/index/{cid}")
    public String run(@PathVariable(value = "cid") String cid, Model model) {
        model.addAttribute("cid", cid);
        return "websocket.html";
    }
}
