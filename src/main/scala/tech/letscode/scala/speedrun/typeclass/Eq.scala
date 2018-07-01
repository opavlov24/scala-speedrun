package tech.letscode.scala.speedrun.typeclass

trait Eq[T] {
  def ===(first: T, second: T): Boolean
}

object Eq {

  implicit class EqOps[T](val x: T) extends AnyVal {
    def ===(y: T)(implicit eq: Eq[T]): Boolean = eq.===(x, y)
  }

  implicit def compare[T](implicit eq: Eq[T]): Eq[List[T]] = {
    (first: List[T], second: List[T]) =>
      first.size == second.size && first.zip(second).forall { case (f, s) => eq.===(f, s) }
  }
}