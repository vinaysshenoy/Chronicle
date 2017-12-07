package com.vinaysshenoy.chronicle.expr

class Between @JvmOverloads constructor(lower: Long, upper: Long, lowerExclusive: Boolean = false, upperExclusive: Boolean = true) : Expression {

  private val range = when {
    !lowerExclusive && !upperExclusive -> lower..upper
    !lowerExclusive && upperExclusive -> lower until upper
    lowerExclusive && !upperExclusive -> (lower + 1)..upper
    lowerExclusive && upperExclusive -> (lower + 1) until upper
    else -> throw IllegalStateException("Should never happen")
  }

  override fun evaluate(test: Long) = test in range
}