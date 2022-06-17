package ru.isinsmartsoft.habr.hub.telegram.config

import org.springframework.boot.context.properties.ConfigurationProperties
import ru.isinsmartsoft.habr.hub.telegram.model.bot.TelegramBotID
import ru.isinsmartsoft.habr.hub.telegram.model.bot.TelegramBotInfo

@ConfigurationProperties(prefix = "telegram")
data class TelegramProperties(
    val bots: List<TelegramBotProperties> = mutableListOf(),
) {

    private fun getAllBotsInfo(): List<TelegramBotInfo> {
        return bots.map {
            TelegramBotInfo(
                name = it.name,
                token = it.token
            )
        }
    }

    fun findTelegramBotInfoByID(telegramBotID: TelegramBotID): TelegramBotInfo? {
        return getAllBotsInfo().find { it.name == telegramBotID.getOfficialBotName() }
    }

    class TelegramBotProperties {
        lateinit var name: String
        lateinit var token: String
    }
}
