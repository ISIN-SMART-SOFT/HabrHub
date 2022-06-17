package ru.isinsmartsoft.habr.hub.isin.architecture

import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import ru.isinsmartsoft.habr.hub.telegram.architecture.Architecture
import ru.isinsmartsoft.habr.hub.telegram.utils.defineTypeUpdate

private val log = KotlinLogging.logger { }

@Component
class ArchitectureIsin : Architecture {

    override fun processing(update: Update) {
        log.info { "New message ${update.defineTypeUpdate()} => $update" }
    }
}
