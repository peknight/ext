package com.peknight.http4s.ext.uri

import org.http4s.Uri.Scheme

package object scheme:
  def ws: Scheme = Scheme.unsafeFromString("ws")
  def wss: Scheme = Scheme.unsafeFromString("wss")
end scheme
