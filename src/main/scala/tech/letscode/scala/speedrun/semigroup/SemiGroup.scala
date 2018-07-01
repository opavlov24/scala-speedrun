package tech.letscode.scala.speedrun.semigroup

import scala.language.higherKinds

trait SemiGroup[T] {
  def combine(first: T, second: T): T
}

object SemiGroup {

  implicit class SemiGroupOps[T](val x: T) extends AnyVal {

    def |+|(y: T)(implicit sg: SemiGroup[T]): T = combine(y)

    def combine(y: T)(implicit sg: SemiGroup[T]): T = sg.combine(x, y)
  }

  def combineListVia[U[_]] = new CombineListVia[U]

  class CombineListVia[U[_]] {
    def apply[T](list: List[T])(implicit iso: Iso[T, U[T]], sg: SemiGroup[U[T]]): Option[T] = {
      list.reduceOption((first, second) => iso.unwrap(sg.combine(iso.wrap(first), iso.wrap(second))))
    }
  }

}

final case class Sum[T](obj: T) extends AnyVal

final case class Prod[T](obj: T) extends AnyVal

object Sum {
  implicit def sumIso[T]: Iso[T, Sum[T]] = new Iso[T, Sum[T]] {
    override def wrap(obj: T): Sum[T] = Sum(obj)

    override def unwrap(obj: Sum[T]): T = obj.obj
  }
}

object Prod {
  implicit def prodIso[T]: Iso[T, Prod[T]] = new Iso[T, Prod[T]] {
    override def wrap(obj: T): Prod[T] = Prod(obj)

    override def unwrap(obj: Prod[T]): T = obj.obj
  }
}