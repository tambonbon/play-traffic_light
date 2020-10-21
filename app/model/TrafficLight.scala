package model

import model.Color.Color

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import services.CachingTrafficLightService
import services.TrafficLightService
case class TrafficLight(id: Int, color: Color)

object TrafficLight {
  var ongoingRequests: Map[Int, Future[TrafficLight]] = Map.empty
}
