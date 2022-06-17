package ru.isinsmartsoft.habr.hub.telegram

import org.telegram.telegrambots.meta.api.objects.Message
import ru.isinsmartsoft.habr.hub.telegram.model.enums.MarkupType

interface TelegramBotOperation {

    fun sendMessage(chatId: String, message: String, markupType: MarkupType = MarkupType.NONE): Message
}
