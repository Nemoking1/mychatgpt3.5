package com.qyt.chatgpt;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

//    @PostMapping("/chat")
//    public String chat(@RequestParam("message") String message, Model model) {
//        String response = OpenAIAPI.chat(message);
//        model.addAttribute("message", message);
//        model.addAttribute("response", response);
//        return "index";
//    }
    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam("message") String message, Model model) {
        String response = OpenAIAPI.chat(message);
        model.addAttribute("message", message);
        model.addAttribute("response", response);
        return ResponseEntity.ok(response);
    }
}

