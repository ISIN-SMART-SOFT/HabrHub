package ru.isinsmartsoft.habr.hub.app.props

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "storage")
class StorageProperties(
    val location: String = "upload-dir"
)
