package com.peknight.http4s.server.ext

object AutoRenewalCertificatesServer:

end AutoRenewalCertificatesServer

//import cats.effect._
//import cats.effect.std.Dispatcher
//import cats.syntax.all._
//import fs2.Stream
//import org.http4s._
//import org.http4s.ember.server.EmberServerBuilder
//import org.http4s.server.Server
//import org.typelevel.log4cats.Logger
//import org.typelevel.log4cats.slf4j.Slf4jLogger
//import java.security.cert.X509Certificate
//import java.time.Instant
//import javax.net.ssl.SSLContext
//import scala.concurrent.duration._
//
//case class ServerState[F[_]](
//                              server: Server,
//                              certs: NonEmptyList[X509Certificate],
//                              sslContext: SSLContext
//                            )
//
//object DynamicCertServer {
//  // 自动获取 Logger
//  private implicit def logger[F[_]: Async]: Logger[F] = Slf4jLogger.getLogger
//
//  def buildServer[F[_]: Async](
//                                getNewCerts: F[NonEmptyList[X509Certificate]],
//                                checkInterval: FiniteDuration = 1.hour
//                              ): Resource[F, Unit] = {
//    // 状态存储：当前服务器实例和证书信息
//    Ref[F].of(Option.empty[ServerState[F]]).toResource.flatMap { stateRef =>
//      // 主服务逻辑
//      val serverResource = for {
//        // 初始证书加载
//        initialCerts <- Resource.eval(getNewCerts)
//        sslContext <- Resource.eval(buildSSLContext(initialCerts))
//        server <- startEmberServer(sslContext)
//        _ <- Resource.eval(stateRef.set(Some(ServerState(server, initialCerts, sslContext))))
//      } yield ()
//
//      // 证书检查定时任务
//      val certCheckScheduler = schedulerStream(stateRef, getNewCerts, checkInterval)
//
//      // 组合资源和后台任务
//      serverResource.onFinalize(Logger[F].info("Main server shutting down")) *>
//        certCheckScheduler.compile.drain.background
//    }.void
//  }
//
//  // 构建 SSL 上下文
//  private def buildSSLContext[F[_]: Sync](certs: NonEmptyList[X509Certificate]): F[SSLContext] =
//    Sync[F].delay {
//      val keyStore = toKeyStore(certs) // 假设已实现 toKeyStore
//      SSLContextBuilder()
//        .loadTrustMaterial(keyStore, null)
//        .build()
//    }
//
//  // 启动 Ember 服务器
//  private def startEmberServer[F[_]: Async](sslContext: SSLContext): Resource[F, Server] =
//    EmberServerBuilder.default[F]
//      .withHost("0.0.0.0")
//      .withPort(8443)
//      .withHttpApp(Routes.of[F] { case GET -> Root => Ok("Secure Server!") })
//      .withSSLContext(sslContext)
//      .build
//
//  // 定时检查调度流
//  private def schedulerStream[F[_]: Async](
//                                            stateRef: Ref[F, Option[ServerState[F]]],
//                                            getNewCerts: F[NonEmptyList[X509Certificate]],
//                                            interval: FiniteDuration
//                                          ): Stream[F, Unit] = {
//    val checkAction = for {
//      now <- Sync[F].delay(Instant.now())
//      stateOpt <- stateRef.get
//      _ <- stateOpt.traverse { state =>
//        val earliestExpiry = state.certs.map(_.getNotAfter.toInstant).minimum
//        val remaining = Duration.fromNanos(java.time.Duration.between(now, earliestExpiry).toNanos)
//
//        if (remaining < 7.days) {
//          Logger[F].info("Certificate expires soon, renewing...") *>
//            renewServer(stateRef, state, getNewCerts)
//        } else {
//          Logger[F].info(s"Certificate still valid, ${remaining.toHours} hours remaining")
//        }
//      }
//    } yield ()
//
//    Stream.awakeEvery[F](interval).evalMap(_ => checkAction)
//  }
//
//  // 更新服务器实例
//  private def renewServer[F[_]: Async](
//                                        stateRef: Ref[F, Option[ServerState[F]]],
//                                        oldState: ServerState[F],
//                                        getNewCerts: F[NonEmptyList[X509Certificate]]
//                                      ): F[Unit] = {
//    val renewAction = for {
//      // 关闭旧服务器
//      _ <- Logger[F].info(s"Shutting down old server on port ${oldState.server.address.getPort}")
//      _ <- oldState.server.shutdown
//
//      // 创建新证书和服务器
//      newCerts <- getNewCerts
//      newSslContext <- buildSSLContext(newCerts)
//      newServer <- startEmberServer(newSslContext).allocated
//
//      // 更新状态
//      _ <- stateRef.set(Some(ServerState(newServer._1, newCerts, newSslContext)))
//      _ <- Logger[F].info(s"Server renewed on port ${newServer._1.address.getPort}")
//    } yield ()
//
//    renewAction.handleErrorWith { e =>
//      Logger[F].error(e)("Failed to renew server, keeping old instance") *>
//        stateRef.set(Some(oldState)) // 回滚到旧状态
//    }
//  }
//}