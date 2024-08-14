package com.peknight.cats.parse.ext.syntax

import cats.parse.{Parser, Parser0}

trait ParserSyntax:
  extension [A] (parser: Parser0[A])
    def flatMapS0[B](f: A => Either[String, B]): Parser0[B] =
      handleFlatMap[A, B, Parser0](parser)(_.flatMap)(f)
    def flatMapE0[B](f: A => Either[Parser.Error, B]): Parser0[B] =
      flatMapS0[B](a => f(a).left.map(_.toString()))
  end extension

  extension [A] (parser: Parser[A])
    def flatMapS[B](f: A => Either[String, B]): Parser[B] =
      handleFlatMap[A, B, Parser](parser)(_.flatMap)(f)
    def flatMapE[B](f: A => Either[Parser.Error, B]): Parser[B] =
      flatMapS[B](a => f(a).left.map(_.toString()))
  end extension

  private def handleFlatMap[A, B, P[X] <: Parser0[X]](parser: P[A])(flatMap: P[A] => (A => Parser0[B]) => P[B])
                                                     (f: A => Either[String, B]): P[B] =
    flatMap(parser) { f(_) match
      case Left(error) => Parser.failWith(error)
      case Right(value) => Parser.pure(value)
    }
end ParserSyntax
object ParserSyntax extends ParserSyntax
