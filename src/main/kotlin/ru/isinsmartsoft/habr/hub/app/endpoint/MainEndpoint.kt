package ru.isinsmartsoft.habr.hub.app.endpoint

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.isinsmartsoft.habr.hub.isin.bot.MentorBotService
import ru.isinsmartsoft.habr.hub.isin.bot.MoodBotService

@RestController
class MainEndpoint(
    private val mentorBotService: MentorBotService,
    private val moodBotService: MoodBotService
) {

    @GetMapping("/main")
    fun mainPage(): String {
        mentorBotService.sendMessage("223275509", "It is bot mentor")
        moodBotService.sendMessage("223275509", "It is bot mood")
        return "Main Page"
    }
}
