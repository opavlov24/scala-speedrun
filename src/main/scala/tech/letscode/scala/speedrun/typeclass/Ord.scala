package tech.letscode.scala.speedrun.typeclass

trait Ord[T] extends Eq[T] {
  def compare(x: T, y: T): Ord.Compare

  def >(x: T, y: T): Boolean

  def <(x: T, y: T): Boolean
}

object Ord {

  sealed trait Compare

  object Compare {

    case object LT extends Compare

    case object EQ extends Compare

    case object GT extends Compare

  }

  implicit class OrdOps[T](val x: T) extends AnyVal {
    def ===(y: T)(implicit ord: Ord[T]): Boolean = ord.===(x, y)

    def >(y: T)(implicit ord: Ord[T]): Boolean = ord.>(x, y)

    def <(y: T)(implicit ord: Ord[T]): Boolean = ord.<(x, y)
  }

}
