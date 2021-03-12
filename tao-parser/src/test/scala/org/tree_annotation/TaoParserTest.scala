package org.tree_annotation

import org.scalatest.flatspec.AnyFlatSpec

import scala.io.Source

class TaoParserTest extends AnyFlatSpec {

  "tao parser" should "parse" in {
    val input = Source.fromInputStream(getClass.getResourceAsStream("/org/tree_annotation/quirky.tao"))
      .mkString
    val tao = new TaoParser().parse(input)

    assert(tao.toString() == input)
  }

}
