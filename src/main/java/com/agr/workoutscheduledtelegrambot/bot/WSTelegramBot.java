package com.agr.workoutscheduledtelegrambot.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Getter
@AllArgsConstructor
public class WSTelegramBot extends TelegramWebhookBot {
    private final String botUsername;
    private final String botToken;
    private final String botPath;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return botPath;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        log.info("WSTelegramBot.onWebhookUpdateReceived Update: " + update.toString());
        return handleUpdate(update);
    }

    public SendMessage handleUpdate(Update update) {
        log.info("handleUpdate");
        SendMessage replyMessage = null;
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            log.info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            replyMessage = new SendMessage(message.getChatId().toString(), "ПОлучено");
        }
        return replyMessage;
    }
}
