import info.mukel.telegrambot4s._
import info.mukel.telegrambot4s.api._
import info.mukel.telegrambot4s.Implicits._
import info.mukel.telegrambot4s.methods._
import info.mukel.telegrambot4s.models._
import net.spy.memcached.MemcachedClient

import scala.concurrent.Future
import scala.io.Source
import scalacache.memcached.MemcachedCache

/**
  * Returns price for specified car.
  */
object UkraineCarPriceBot extends TelegramBot with Polling with Commands {
  lazy val token = Source.fromFile("bot.token").getLines().mkString

//  override def onMessage(message: Message): Unit = {
//    for (text <- message.text)
//      request(SendMessage(message.sender, text))
//  }

//  override def shutdown(): Future[_] = {
//    App.memcachedCache.close()
//    system.terminate()
//  }
}

object App {


  /**
    * * Наличие ссылки на сайт AUTO.RIA с гиперссылкой на страницу https://AUTO.RIA.com,
не закрытой для индексации поисковыми системами, является единственным
обязательным требованием для использования сервиса.
    *
    *
    */

//
//  val memcachedCache = MemcachedCache()
//  val client = new Client(memcachedCache)
//  val queryTranslator = new QueryTranslator(client)
//  val analyzer = new CarPriceAnalyzer(client, queryTranslator)
//

  def main(args: Array[String]): Unit = {
    UkraineCarPriceBot.run()

  }
}