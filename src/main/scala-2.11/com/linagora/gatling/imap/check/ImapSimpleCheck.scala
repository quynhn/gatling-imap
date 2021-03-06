package com.linagora.gatling.imap.check

import com.linagora.gatling.imap.protocol.ImapResponses
import io.gatling.commons.validation.{Failure, Validation}
import io.gatling.core.check.CheckResult
import io.gatling.core.session.Session

import scala.collection.mutable

object ImapSimpleCheck {
  val DefaultMessage = "Imap check failed"
}

case class ImapSimpleCheck(validate: ImapResponses => Boolean, errorMessage: ImapResponses => String = _ => ImapSimpleCheck.DefaultMessage) extends ImapCheck {
  override def check(responses: ImapResponses, session: Session)(implicit cache: mutable.Map[Any, Any]): Validation[CheckResult] = {
    if (validate(responses))
      CheckResult.NoopCheckResultSuccess
    else
      Failure(errorMessage(responses))
  }
}
