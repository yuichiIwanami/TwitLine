package controllers

import play.api.mvc._
import models.ListFactory
import twitter4j.{ResponseList, Status}
import play.api.data._
import play.api.data.Forms._

object TweetLine extends Controller {

  def line = Action {
    val lists: Map[String, ResponseList[twitter4j.Status]]
      = ListFactory.getHomeList ++ ListFactory.getList("it-software") ++ ListFactory.getList("technology-business") ++ ListFactory.getList("it-news")
    Ok(views.html.line(lists))
  }

  def getProfile(screenName: String) = Action {
    Ok(views.html.profile(ListFactory.getUser(screenName),
        ListFactory.getTimeLine(screenName),
        ListFactory.search("@" + screenName)))
  }

  def search = Action { implicit request =>
    val word = Form("word" ->text).bindFromRequest.get
    Ok(views.html.search(word, ListFactory.search(word)))
  }

}