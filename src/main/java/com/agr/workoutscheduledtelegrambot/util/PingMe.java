package com.agr.workoutscheduledtelegrambot.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Slf4j
public class PingMe {
    @Value("${pingme.url}")
    private String pingUrl;

    @Scheduled(fixedRateString = "${pingme.period-ms}")
    public void ping() {
        try {
            URL url = new URL(pingUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            connection.disconnect();
        } catch (IOException ignored) { }
    }
}

