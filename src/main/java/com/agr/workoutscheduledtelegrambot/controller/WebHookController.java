package com.agr.workoutscheduledtelegrambot.controller;

import com.agr.workoutscheduledtelegrambot.bot.WSTelegramBot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebHookController {
    private final WSTelegramBot bot;

    public WebHookController(WSTelegramBot bot){
        this.bot = bot;
    }

//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
//        log.info("WebHookController.onUpdateReceived Update: " + update.toString());
//        return bot.onWebhookUpdateReceived(update);
//    }

    @GetMapping("/test")
    public String test() {
        return "Test controller accessed";
    }

    @PostMapping("/")
    public SendMessage onUpdateReceived(@RequestBody Update update) {
        return bot.handleUpdate(update);
    }
}
