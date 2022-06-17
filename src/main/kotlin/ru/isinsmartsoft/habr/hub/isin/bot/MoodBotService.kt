package ru.isinsmartsoft.habr.hub.isin.bot

import org.springframework.stereotype.Component
import ru.isinsmartsoft.habr.hub.telegram.config.TelegramProperties
import ru.isinsmartsoft.habr.hub.telegram.service.TelegramBotService

@Component
class MoodBotService(
    telegramProperties: TelegramProperties
) : TelegramBotService(Bot.MOOD_BOT, telegramProperties)
