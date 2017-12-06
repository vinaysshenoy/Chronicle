package com.vinaysshenoy.chronicle.expr

class LesserThanOrEqualTo(private val value: Long) : Expression {

  override fun evaluate(test: Long) = test <= value
}