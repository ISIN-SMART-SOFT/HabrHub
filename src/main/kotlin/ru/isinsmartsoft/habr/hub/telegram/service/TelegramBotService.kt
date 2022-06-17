package ru.isinsmartsoft.habr.hub.telegram.service

import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import ru.isinsmartsoft.habr.hub.telegram.TelegramBotOperation
import ru.isinsmartsoft.habr.hub.telegram.bot.TelegramIsinkaLongPollingBot
import ru.isinsmartsoft.habr.hub.telegram.config.TelegramProperties
import ru.isinsmartsoft.habr.hub.telegram.model.bot.TelegramBotID
import ru.isinsmartsoft.habr.hub.telegram.model.enums.MarkupType

abstract class TelegramBotService(
    private val telegramBotID: TelegramBotID,
    private val telegramProperties: TelegramProperties
) : TelegramBotOperation {

    private lateinit var telegramIsinkaLongPollingBot: TelegramIsinkaLongPollingBot

    override fun sendMessage(chatId: String, message: String, markupType: MarkupType): Message {
        val sendMessage = SendMessage.builder()
            .chatId(chatId)
            .text(message)
            .replyMarkup(null)
            .build()

        when (markupType) {
            MarkupType.MARKDOWN -> sendMessage.enableMarkdownV2(true)
            MarkupType.HTML -> sendMessage.enableHtml(true)
            MarkupType.NONE -> {}
        }
        return telegramIsinkaLongPollingBot.execute(sendMessage)
    }

    init {
        val botInfo = telegramProperties.findTelegramBotInfoByID(telegramBotID)
        if (botInfo != null) {
            val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
            this.telegramIsinkaLongPollingBot = TelegramIsinkaLongPollingBot(botInfo.name, botInfo.token)
            telegramBotsApi.registerBot(telegramIsinkaLongPollingBot)
            println("Initialization Bot => ${telegramBotID.getOfficialBotName()}")
        }
    }

    fun getID(): TelegramBotID {
        return this.telegramBotID
    }
}
