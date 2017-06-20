package info.mukel.telegrambot4s

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

package object api {

  /**
    * Future.transformWith for Scala 2.11
    */
  implicit class FutureTransformWith[T](future: Future[T]) {
    def transformWith[S](f: (Try[T]) ⇒ Future[S])(implicit executor: ExecutionContext): Future[S] = {
      future
        .flatMap(t => f(Success(t)))
        .recoverWith { case e => f(Failure(e)) }
    }
  }
}
