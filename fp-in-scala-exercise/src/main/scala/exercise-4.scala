package exercise4

sealed trait Option[+A] {

  //EXERCISE 4.1 (P.67)

  def map[B](f: A => B): Option[B] = this match {
    case None => None
    case Some(value) => Some(f(value))
  }

  def flatMap[B](f: A => Option[B]): Option[B] = map(f).getOrElse(None)

  def getOrElse[B >: A](default: => B): B = this match {
    case None => default
    case Some(value) => value
  }

  def orElse[B >: A](ob: => Option[B]): Option[B] = map(Some(_)).getOrElse(ob)

  def filter(f: A => Boolean): Option[A] = flatMap(a => if(f(a)) Some(a) else None)
}

case class Some[+A](get: A) extends Option[A]

case object None extends Option[Nothing]

package object functions {

  def variance(xs: Seq[Double]): Option[Double] = {
    val om = if(xs.isEmpty) None else Some(xs.sum / xs.size)
    om.flatMap { m =>
      val ys = xs.map(x => math.pow(x - m, 2))
      if(ys.isEmpty) None else Some(ys.sum / ys.size)
    }
  }
}
