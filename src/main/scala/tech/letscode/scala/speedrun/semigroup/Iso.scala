package tech.letscode.scala.speedrun.semigroup

trait Iso[T, U] {
  def wrap(obj: T): U

  def unwrap(obj: U): T
}
