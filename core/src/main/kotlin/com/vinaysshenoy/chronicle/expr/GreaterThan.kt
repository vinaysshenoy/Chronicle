package com.vinaysshenoy.chronicle.expr

class GreaterThan(private val value: Long) : Expression {

  override fun evaluate(test: Long) = test > value
}