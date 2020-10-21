package services
import model.TrafficLight
import play.api.libs.json.JsValue
import com.google.inject.ImplementedBy
import play.api.mvc.Result

import scala.concurrent.Future

@ImplementedBy(classOf[CachingTrafficLightService])
trait TrafficLightService {
  def all: Seq[TrafficLight]
  def get(id: Int): Option[TrafficLight]
  def getFuture(id: Int): Future[TrafficLight]
  def save(tl: Map[Int, TrafficLight]): Unit
  def changeToGreen(id: Int): Future[TrafficLight]
  def changeToRedFromGreen(id: Int): Future[TrafficLight]
  def changeToRedFromOrange(id: Int): Future[TrafficLight]

}
