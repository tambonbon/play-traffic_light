package services
import javax.inject.Inject
import model.TrafficLight.ongoingRequests
import model.{Color, TrafficLight}
import play.api.cache.AsyncCacheApi
import play.api.libs.json._
import play.api.libs.ws._
import play.api.mvc._
import play.api.cache._
import scala.language.postfixOps
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

class CachingTrafficLightService @Inject() extends TrafficLightService {
   var db = Map (
    1 -> TrafficLight(1, Color.Green),
    2 -> TrafficLight(2, Color.Orange),
    3 -> TrafficLight(3, Color.Red)
  )

  override def all: Seq[TrafficLight] = {
    db.values.toSeq.sortBy(_.id)
  }

  override def get(id: Int): Option[TrafficLight] = {
    db.get(id)
  }

  override def getFuture(id: Int): Future[TrafficLight] = Future {
    db(id)
  }
  override def save(tl: Map[Int, TrafficLight]): Unit = {
    db ++= tl
  }
//  override def saveFuture(tl: Map[Int, Future[TrafficLight]]): Future[Result] = Future {
//    db ++= tl
//  }
//  override def update: JsValue = {
//
//  }

  def trafficLightsList: List[TrafficLight] =
    db.values.toList

  override def changeToGreen(id: Int): Future[TrafficLight] = Future {
    // Step 1. Make the light Green
    val greenTrafficLight = TrafficLight(id, Color.Green)
    db += id -> greenTrafficLight

    greenTrafficLight
  }

  override def changeToRedFromGreen(id: Int): Future[TrafficLight] = {
    val request = Future {
      // Step 1. Make the light Orange
      val orangeTrafficLight = TrafficLight(id, Color.Orange)
      db += id -> orangeTrafficLight

      // Step 2. Wait 15 seconds
      Thread.sleep(15 * 1000)

      // Step 3. Make the light Red
      val redTrafficLight = TrafficLight(id, Color.Red)
      db += id -> redTrafficLight

      // Step 4. Finish the request.
      ongoingRequests -= id

      redTrafficLight
    }

    ongoingRequests += id -> request

    request
  }


  override def changeToRedFromOrange(id: Int): Future[TrafficLight] = {
    val ongoingRequestOpt = ongoingRequests.get(id)
    ongoingRequestOpt match {
      case Some(ongoingRequest) =>
        ongoingRequest
      case None =>
        Future.successful(TrafficLight(id, Color.Red))
    }
  }
}
