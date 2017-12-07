package com.vinaysshenoy.chronicle.specs.expressions

import com.vinaysshenoy.chronicle.expr.Between
import org.amshove.kluent.`should be false`
import org.amshove.kluent.`should be true`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object BetweenSpec : Spek({

  describe("A between expression with lower inclusive and upper exclusive bounds") {
    val between = Between(
        lower = 1L,
        upper = 10L,
        lowerExclusive = false,
        upperExclusive = true
    )

    describe("A value which lies within the range of the bounds") {
      val test = 5L

      on("evaluating the expression with the given test") {
        val result = between.evaluate(test)

        it("should return true") {
          result.`should be true`()
        }
      }
    }

    describe("A value which lies on the lower bound of the between range") {
      val test = 1L

      on("evaluating the expression with the given test") {
        val result = between.evaluate(test)

        it("should return true") {
          result.`should be true`()
        }
      }
    }

    describe("A value which lies on the upper bound of the between range") {
      val test = 10L

      on("evaluating the expression with the given test") {
        val result = between.evaluate(test)

        it("should return false") {
          result.`should be false`()
        }
      }
    }
  }
})