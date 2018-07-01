package tech.letscode.scala.speedrun.typeclass

final case class Ratio(num: Integer, den: Integer)

object Ratio {

  implicit val ord: Ord[Ratio] = new Ord[Ratio] {
    override def compare(x: Ratio, y: Ratio): Ord.Compare = {
      (x.num.toLong * y.den, x.den.toLong * y.num) match {
        case (a, b) if a == b => Ord.Compare.EQ
        case (a, b) if a > b => Ord.Compare.GT
        case (a, b) if a < b => Ord.Compare.LT
      }
    }

    override def ===(first: Ratio, second: Ratio): Boolean = compare(first, second) == Ord.Compare.EQ

    override def >(x: Ratio, y: Ratio): Boolean = compare(x, y) == Ord.Compare.GT

    override def <(x: Ratio, y: Ratio): Boolean = compare(x, y) == Ord.Compare.LT
  }
}