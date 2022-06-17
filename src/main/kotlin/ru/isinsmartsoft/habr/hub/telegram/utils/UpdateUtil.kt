package ru.isinsmartsoft.habr.hub.telegram.utils

import org.telegram.telegrambots.meta.api.objects.Update
import ru.isinsmartsoft.habr.hub.telegram.model.enums.TypeUpdate

fun Update.isCommand(): Boolean {
    return this.message.isCommand
}

fun Update.isPollAnswer(): Boolean {
    return this.pollAnswer != null
}

fun Update.defineTypeUpdate(): TypeUpdate {
    return when {
        this.isPollAnswer() -> TypeUpdate.POLL_ANSWER
        this.isCommand() -> TypeUpdate.COMMAND
        else -> TypeUpdate.MESSAGE
    }
}
