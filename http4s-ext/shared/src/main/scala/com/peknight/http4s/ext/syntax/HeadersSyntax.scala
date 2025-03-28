package com.peknight.http4s.ext.syntax

import cats.effect.Sync
import cats.syntax.eq.*
import cats.syntax.functor.*
import cats.syntax.traverse.*
import com.comcast.ip4s.IpAddress
import com.peknight.cats.effect.ext.Clock
import org.http4s.CacheDirective.`max-age`
import org.http4s.headers.*
import org.http4s.{Headers, Uri}

import java.time.Instant
import scala.concurrent.duration.*

trait HeadersSyntax:
  extension (headers: Headers)
    def getLocation(uri: Uri): Option[Uri] = headers.get[Location].map(loc => uri.resolve(loc.uri))
    def getLastModified: Option[Instant] = headers.get[`Last-Modified`].map(_.date.toInstant)
    def getExpiration[F[_]: Sync]: F[Option[Instant]] =
      headers.get[`Cache-Control`]
        .flatMap { _.values.collectFirst {
          case `max-age`(deltaSeconds) if deltaSeconds =!= 0.second =>
            Clock.realTimeInstant[F].map(_.plusSeconds(deltaSeconds.toSeconds))
        }}
        .sequence
        .map(_.orElse(headers.get[Expires].map(_.expirationDate.toInstant)))
    def getLinks(relation: String): Option[List[Uri]] = headers.get[Link].map(_.values.collect {
      case link: LinkValue if link.rel.contains(relation) => link.uri
    })
    def getRetryAfter: Option[Instant] =
      headers.get[`Retry-After`].flatMap { date =>
        date.retry match
          case Left(date) => Some(date.toInstant)
          case Right(seconds) => headers.get[Date].map(_.date.toInstant.plusSeconds(seconds))
      }
    def getXForwardedFor: List[IpAddress] =
      headers.get[`X-Forwarded-For`]
        .map(_.values.collect {
          case Some(ipAddress) => ipAddress
        })
        .getOrElse(Nil)
  end extension
end HeadersSyntax
object HeadersSyntax extends HeadersSyntax
