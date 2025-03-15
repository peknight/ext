package com.peknight.log4cats.ext.syntax

import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.extras.LogLevel

trait LoggerSyntax:
  extension [F[_]] (logger: Logger[F])
    def log(level: LogLevel = LogLevel.Info, t: Option[Throwable] = None)(message: => String): F[Unit] =
      given CanEqual[LogLevel, LogLevel] = CanEqual.derived
      (level, t) match
        case (LogLevel.Error, Some(throwable)) => logger.error(throwable)(message)
        case (LogLevel.Warn, Some(throwable)) => logger.warn(throwable)(message)
        case (LogLevel.Info, Some(throwable)) => logger.info(throwable)(message)
        case (LogLevel.Debug, Some(throwable)) => logger.debug(throwable)(message)
        case (LogLevel.Trace, Some(throwable)) => logger.trace(throwable)(message)
        case (LogLevel.Error, _) => logger.error(message)
        case (LogLevel.Warn, _) => logger.warn(message)
        case (LogLevel.Info, _) => logger.info(message)
        case (LogLevel.Debug, _) => logger.debug(message)
        case (LogLevel.Trace, _) => logger.trace(message)
  end extension
end LoggerSyntax
object LoggerSyntax extends LoggerSyntax
