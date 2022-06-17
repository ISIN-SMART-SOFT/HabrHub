package ru.isinsmartsoft.habr.hub.telegram.bot

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import ru.isinsmartsoft.habr.hub.telegram.architecture.Architecture
import ru.isinsmartsoft.habr.hub.telegram.utils.SpringContextUtil

class TelegramIsinkaLongPollingBot(
    private val botUsername: String,
    private val botToken: String
) : TelegramLongPollingBot() {
    override fun getBotToken(): String = botToken

    override fun getBotUsername(): String = botUsername

    override fun onUpdateReceived(update: Update) {
        // Обработка сообщения / Message processing
        SpringContextUtil.getBean(Architecture::class.java).processing(update)
    }
}
