package com.vinaysshenoy.chronicle.expr

class GreaterThanOrEqualTo(private val value: Long): Expression {

  override fun evaluate(test: Long) = test >= value
}