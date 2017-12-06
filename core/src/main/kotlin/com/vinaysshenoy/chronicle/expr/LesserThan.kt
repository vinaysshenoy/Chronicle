package com.vinaysshenoy.chronicle.expr

class LesserThan(private val value: Long): Expression {

  override fun evaluate(test: Long) = test < value
}