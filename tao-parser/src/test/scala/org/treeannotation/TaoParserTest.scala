package org.treeannotation

import org.scalatest.flatspec.AnyFlatSpec

import scala.io.Source

class TaoParserTest extends AnyFlatSpec {

  "tao parser" should "parse" in {
    val input = Source.fromInputStream(getClass.getResourceAsStream("/org/treeannotation/quirky.tao"))
      .mkString
    val tao = new TaoParser().parse(input)

    assert(tao.toString() == input)
  }

}
