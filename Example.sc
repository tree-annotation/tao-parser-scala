// todo: load TaoParser, parse test str
// perhaps ammonite

// import test.TaoParser

// :load "TaoParser.scala"

import $file.TaoParser

val p = new TaoParser.TaoParser()
println(p.parse("key [value] array [[item1][item2][item3]]"))