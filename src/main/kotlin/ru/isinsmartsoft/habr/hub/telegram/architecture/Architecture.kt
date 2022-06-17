package ru.isinsmartsoft.habr.hub.telegram.architecture

import org.telegram.telegrambots.meta.api.objects.Update

interface Architecture {

    fun processing(update: Update)
}

interface TelegramApplication
