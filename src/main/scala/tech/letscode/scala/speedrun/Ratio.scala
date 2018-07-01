package tech.letscode.scala.speedrun

import tech.letscode.scala.speedrun.semigroup.{Prod, SemiGroup, Sum}
import tech.letscode.scala.speedrun.typeclass.Ord

final case class Ratio(num: Integer, den: Integer)

object Ratio {

  def sum(first: Ratio, second: Ratio): Ratio = Ratio(
    first.num * second.den + second.num * first.den,
    first.den * second.den
  )

  def mulitiply(first: Ratio, second: Ratio): Ratio = Ratio(
    first.num * second.num,
    first.den * second.den
  )

  implicit val semiGroupSum: SemiGroup[Sum[Ratio]] = (first: Sum[Ratio], second: Sum[Ratio]) => Sum(Ratio.sum(first.obj, second.obj))

  implicit val semiGroupProd: SemiGroup[Prod[Ratio]] = (first: Prod[Ratio], second: Prod[Ratio]) => Prod(Ratio.mulitiply(first.obj, second.obj))

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