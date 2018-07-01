import tech.letscode.scala.speedrun.Ratio; {
  import tech.letscode.scala.speedrun.typeclass.Ord._

  Ratio(2, 4) === Ratio(3, 6)
  Ratio(1, 1) === Ratio(8, 8)
  Ratio(1, 2) === Ratio(1, 4)

  Ratio(1, 2) > Ratio(2, 4)
  Ratio(1, 2) < Ratio(2, 4)
  Ratio(1, 2) === Ratio(2, 4)
}; {
  import tech.letscode.scala.speedrun.typeclass.Eq._

  val firstList: List[Ratio] = List(Ratio(1, 2), Ratio(3, 5))
  val secondList: List[Ratio] = List(Ratio(1, 2), Ratio(4, 5))

  firstList === secondList
}