package com.vinaysshenoy.chronicle

import com.vinaysshenoy.chronicle.expr.Equal
import com.vinaysshenoy.chronicle.expr.Expression
import com.vinaysshenoy.chronicle.expr.GreaterThanOrEqualTo
import com.vinaysshenoy.chronicle.expr.LesserThanOrEqualTo
import com.vinaysshenoy.chronicle.operation.Operation
import com.vinaysshenoy.chronicle.operation.TimeSinceLastOccurrence
import com.vinaysshenoy.chronicle.operation.TimesDone
import com.vinaysshenoy.chronicle.operation.TimesDoneSince

class Watch private constructor(private val chronicle: Chronicle, private val operations: List<Operation>, val name: String = "") {


  class Builder internal constructor(private val chronicle: Chronicle, private val name: String = "") {

    private val operations = mutableListOf<Operation>()

    fun timesDone(event: String, count: Long) = timesDone(event, Equal(count))

    fun timesDone(event: String, expr: Expression): Builder {
      operations.add(TimesDone(chronicle.store, event, expr))
      return this
    }

    fun hasDone(event: String) = timesDone(event, GreaterThanOrEqualTo(1))

    fun timesDoneSince(event: String, timeMillis: Long, count: Long) = timesDoneSince(event, timeMillis, Equal(count))

    fun timesDoneSince(event: String, timeMillis: Long, expr: Expression): Builder {
      operations.add(TimesDoneSince(chronicle.store, event, timeMillis, expr))
      return this
    }

    fun timeSinceLastOccurrence(event: String, durationMillis: Long) = timeSinceLastOccurrence(event, LesserThanOrEqualTo(durationMillis))

    fun timeSinceLastOccurrence(event: String, expr: Expression): Builder {
      operations.add(TimeSinceLastOccurrence(chronicle.store, event, System.currentTimeMillis(), expr))
      return this
    }

    fun build() = Watch(chronicle, operations.toList(), name)
  }
}