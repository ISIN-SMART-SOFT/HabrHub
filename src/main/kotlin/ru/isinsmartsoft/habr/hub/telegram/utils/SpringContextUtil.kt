package ru.isinsmartsoft.habr.hub.telegram.utils

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

/**
 * Класс для работы с контекстом Spring и помощи non-manager java class object.
 */
@Component
class SpringContextUtil : ApplicationContextAware {

    companion object {
        private lateinit var applicationContext: ApplicationContext

        /**
         * Позволяет получить бин из класса который не управляется spring context.
         *
         * @param className название класса bean
         * @param <T>       тип bean
         * @return bean
        </T> */
        fun <T> getBean(className: Class<T>): T {
            return applicationContext.getBean(className)
        }
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        SpringContextUtil.applicationContext = applicationContext
    }
}
