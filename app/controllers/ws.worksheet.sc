case class TrafficLight (id: Int, color: String)
var list: Set[TrafficLight] = {
      Set (  
        TrafficLight(1, "Green"),
        TrafficLight(2, "Green"),
        TrafficLight(3, "Orange"),
        TrafficLight(4, "Red"),
        TrafficLight(2, "Green")
      )
    }

val map1 = list.zipWithIndex.map{case(v,i) => (v, i+1)}
// trafficLightsMap.foldLeft(Map.empty[Int,String])()
val map2 = Set(
    TrafficLight(1,"Green"),
    TrafficLight(5, "Red")
)
val map3 = map2.zipWithIndex.map{case(v,i) => (v, i+1)}.toMap
val map4 = map1 ++ map2

import TrafficLight._
import scala.collection.mutable

    var mapp: mutable.Map[Int, String] = {
      mutable.Map (  
        1 -> "Green",
        2 -> "Green",
        3 -> "Orange",
        4 -> "Red"
        // TrafficLight(1, "Green"),
        // TrafficLight(2, "Green"),
        // TrafficLight(3, "Orange"),
        // TrafficLight(4, "Red")
      )
    }

    mapp.keySet.contains(5)

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import scala.collection.mutable
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

val a = Future{ Thread.sleep(1*1000); 42}
val b = a.map(_*2)

def timedFuture[T](future: Future[T]) = {
    val start = System.currentTimeMillis()
    future.onComplete({
      case _ => println(s"Future took ${System.currentTimeMillis() - start} ms")
    })
    future
  }
  
  // I want to be able to measure how long this function would take
  def wantToMeasure = Future{
    Thread.sleep(500)
    "Foo"
  }

  val result1 = timedFuture(wantToMeasure)
  result1