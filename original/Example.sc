import $file.TaoParser

val p = new TaoParser.TaoParser()
println(p.parse("key [value] array [[item1][item2][item3]]"))