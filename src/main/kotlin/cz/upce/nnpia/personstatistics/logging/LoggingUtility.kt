package cz.upce.nnpia.personstatistics.logging

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LoggingUtility {
	companion object {
		fun getLogger(): Logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)

		fun getLogger(loggerType: LoggerType): Logger = LoggerFactory.getLogger(loggerType.name)
	}

	enum class LoggerType {
		SECURITY, APPLICATION
	}
}
