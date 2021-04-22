package com.agr.workoutscheduledtelegrambot.bot;

import com.agr.workoutscheduledtelegrambot.service.CommunicationService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Getter
@Service
public class WSTelegramBot extends TelegramWebhookBot {
    private final CommunicationService communicationService;
    private final BotConfig botConfig;
    private final TelegramBotsApi telegramBotsApi;

    public WSTelegramBot(CommunicationService communicationService,
                         BotConfig botConfig, TelegramBotsApi telegramBotsApi) throws TelegramApiException {
        this.communicationService = communicationService;
        this.botConfig = botConfig;
        this.telegramBotsApi = telegramBotsApi;
        SetWebhook setWebhook = new SetWebhook(botConfig.getBotPath());
        this.setWebhook(setWebhook);

        telegramBotsApi.registerBot(this, setWebhook);
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

    @Override
    public String getBotPath() {
        return botConfig.getBotPath();
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return handleUpdate(update);
    }

    private BotApiMethod<?> handleUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            return communicationService.handleCallBackQuery(update.getCallbackQuery());
        }
        if (update.getMessage() != null && update.getMessage().hasText()) {
            return communicationService.handleMessage(update.getMessage());
        }
        return null;
    }
}
