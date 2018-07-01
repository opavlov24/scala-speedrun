import tech.letscode.scala.speedrun.Ratio
import tech.letscode.scala.speedrun.semigroup.SemiGroup._
import tech.letscode.scala.speedrun.semigroup.{Prod, Sum}

val list = List(Ratio(1, 2), Ratio(1, 4))

combineListVia[Sum](list)
combineListVia[Prod](list)