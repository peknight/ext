package com.peknight.http4s.client.ext.proxy

import com.comcast.ip4s.{Host, Port}

trait ReverseConfig:
  def host: Host
  def port: Option[Port]
  def subdomain: String
end ReverseConfig
