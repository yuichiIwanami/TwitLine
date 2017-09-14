package utils

import java.util.regex.Pattern

/**
 * Created by forrest on 12/15/13.
 */

object ReplaceLink {

  def All(text: String): String = ScreenName(Url(text))

  val URL_PATTERN = Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+")
  val URL_A_TAG = "<a href='$0' target='_blank'>$0</a>"
  def Url(text: String): String
      = URL_PATTERN.matcher(text).replaceAll(URL_A_TAG)

  val SCREEN_NAME_PAT = Pattern.compile("@{1}[\\w\\.\\-/]+")
  val SCREEN_A_TAG = "<a href='https://twitter.com/$0' target='_blank'>$0</a>"
  def ScreenName(text: String): String
    = SCREEN_NAME_PAT.matcher(text).replaceAll(SCREEN_A_TAG)

  val HASH_TAG_PAT = Pattern.compile("#{1}[\\S]+")
  val HASH_A_TAG = "<a href='https://twitter.com/$0' target='_blank'>$0</a>"
  def HashTag(text: String): String
    = HASH_TAG_PAT.matcher(text).replaceAll(HASH_A_TAG)
}
