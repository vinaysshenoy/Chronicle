package com.vinaysshenoy.chronicle.operation

import com.vinaysshenoy.chronicle.Store
import com.vinaysshenoy.chronicle.expr.Expression

internal interface Operation {

  fun run(): Boolean
}

internal class TimesDone(private val store: Store, private val event: String, private val expr: Expression) : Operation {

  override fun run() = expr.evaluate(store.countOf(event))
}

internal class TimesDoneSince(private val store: Store, private val event: String, private val timeMillis: Long, private val expr: Expression) : Operation {

  override fun run() = expr.evaluate(store.countOfSince(event, timeMillis))
}

internal class TimeSinceLastOccurrence(private val store: Store, private val event: String, private val currentTimeMillis: Long, private val expr: Expression) : Operation {

  override fun run() =
      store.latestOf(event)?.let { expr.evaluate(currentTimeMillis - it.eventTimeMillis) } ?: false
}