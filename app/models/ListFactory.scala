package models

import scala.collection.JavaConversions._
import twitter4j._
import twitter4j.conf.ConfigurationBuilder

 object ListFactory {

   val twitter = getTwitter
   
   def getHomeList: Map[String, ResponseList[twitter4j.Status]] = {
     return Map("Home Feed" -> twitter.getHomeTimeline)
   }
   
   def getList(slug: String): Map[String, ResponseList[twitter4j.Status]] = {
      val page: Paging = new Paging(1)
      return Map(slug -> twitter.getUserListStatuses("gaudi08", slug, page))
   }

   def getTimeLine(user: String): Seq[twitter4j.Status] = {
     return twitter.getUserTimeline(user)
   }

   def search(word: String): Seq[twitter4j.Status] = {
     val query = new Query(word)
     query.setCount(100)
     val result = twitter.search(query)
     return result.getTweets
   }

   def getUser(screenName: String): User = {
      return twitter.showUser(screenName)
   }

   def getTwitter: Twitter = {
     return new TwitterFactory("conf/").getInstance
   }

}
