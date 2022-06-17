package ru.isinsmartsoft.habr.hub.isin.bot

import ru.isinsmartsoft.habr.hub.telegram.model.bot.TelegramBotID

enum class Bot(
    private val officialBotName: String
) : TelegramBotID {
    MENTOR_BOT("JavaMentorDevBot"),
    MOOD_BOT("MoodDevBot");

    override fun getOfficialBotName(): String {
        return this.officialBotName
    }
}
