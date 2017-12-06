package com.vinaysshenoy.chronicle

import com.vinaysshenoy.chronicle.expr.Equal
import com.vinaysshenoy.chronicle.expr.Expression
import com.vinaysshenoy.chronicle.expr.GreaterThanOrEqualTo
import com.vinaysshenoy.chronicle.expr.LesserThanOrEqualTo

class Watch private constructor(private val chronicle: Chronicle, val name: String = "") {



  class Builder internal constructor(private val chronicle: Chronicle, private val name: String = "") {

    fun timesDone(event: String, count: Long) = timesDone(event, Equal(count))

    fun timesDone(event: String, expr: Expression): Builder {
      return this
    }

    fun hasDone(event: String) = timesDone(event, GreaterThanOrEqualTo(1))

    fun timesDoneSince(event: String, timeMillis: Long, count: Long) = timesDoneSince(event, timeMillis, Equal(count))

    fun timesDoneSince(event: String, timeMillis: Long, expr: Expression): Builder {
      return this
    }

    fun timeSinceLastOccurrence(event: String, durationMillis: Long) = timeSinceLastOccurrence(event, LesserThanOrEqualTo(durationMillis))

    fun timeSinceLastOccurrence(event: String, expr: Expression): Builder {
      return this
    }

    fun build() = Watch(chronicle, name)
  }
}