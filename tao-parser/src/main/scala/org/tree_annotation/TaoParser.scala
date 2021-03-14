package org.tree_annotation

import scala.collection.mutable.Stack
import scala.collection.mutable.ListBuffer

class TaoParser {
  def parse(str: String): Tao = tao(new Input(str))

  private def tao(input: Input): Tao = {
    val tao = new Tao()
    while (!input.atBound()) {
      var part = tree(input)
      if (part.tag == "other") {
        part = op(input)
        if (part.tag == "other") {
          part = note(input)
        }
      }
      tao.push(part)
    }
    tao
  }

  private def tree(input: Input): Tagged = {
    if (input.at('[')) {
      input.next()
      input.bound(']')
      val tree = tao(input)
      input.unbound()
      input.next()
      Tree(tree)
    } else Other
  }

  private def op(input: Input): Tagged = {
    if (input.at('`')) {
      input.next()
      if (input.done()) input.error("op")
      else Op(input.next())
    } else Other
  }

  private def note(input: Input): Tagged = {
    if (meta(input)) input.error("note")
    var note = "" + input.next()
    while (!(meta(input) || input.done())) note += input.next()
    Note(note)
  }

  private def meta(input: Input): Boolean = input.at('[') || input.at('`') || input.at(']')
  class Tagged(val tag: String) {}
  // todo: protect parts
  case class Tao() extends Tagged("tao") {
    private val parts: ListBuffer[Tagged] = ListBuffer[Tagged]()

    def push(part: Tagged) = parts += part

    override def toString(): String = parts.foldLeft("")(_ + _)
  }
  case class Tree(val tao: Tao) extends Tagged("tree") {
    override def toString(): String = "[" + tao + "]"
  }
  case class Note(val note: String) extends Tagged("note") {
    override def toString(): String = note
  }
  case class Op(val op: Char) extends Tagged("op") {
    override def toString(): String = "`" + op
  }
  case object Other extends Tagged("other") {}
  private class Input(private val str: String) {
    private val length = str.length()
    private var position = 0
    private val bounds = Stack[(Int, Char)]()

    def done(): Boolean = position >= length

    def at(symbol: Char): Boolean = str.charAt(position) == symbol

    def next() = {
      val ret = str.charAt(position)
      position += 1
      ret
    }

    def error(name: String) = {
      throw new Exception("Error: malformed " + name + " at " + position)
    }

    def bound(symbol: Char) = bounds.push((position, symbol))

    def unbound() = bounds.pop()

    def atBound(): Boolean = {
      if (bounds.size > 0) {
        val (position, symbol) = bounds.top
        if (done()) throw new Exception(
          "ERROR: since " + position + " expected \"" + symbol + "\" before end of input"
        ) else at(symbol)
      } else done()
    }
  }
}