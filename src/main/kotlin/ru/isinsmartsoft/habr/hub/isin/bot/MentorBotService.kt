package ru.isinsmartsoft.habr.hub.isin.bot

import org.springframework.stereotype.Component
import ru.isinsmartsoft.habr.hub.telegram.config.TelegramProperties
import ru.isinsmartsoft.habr.hub.telegram.service.TelegramBotService

@Component
class MentorBotService(
    telegramProperties: TelegramProperties
) : TelegramBotService(Bot.MENTOR_BOT, telegramProperties)
