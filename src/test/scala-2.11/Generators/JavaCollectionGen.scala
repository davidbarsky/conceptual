package Generators

import java.util.LinkedList

import org.scalacheck.util.Buildable

import scala.collection.JavaConverters._
import scala.collection._
import scala.collection.mutable.Builder

object JavaCollectionGen {
  // Built using
  // http://stackoverflow.com/questions/39704941/scalacheck-new-buildable-instances
  implicit def buildableLinkedList[T]: Buildable[T, LinkedList[T]] =
  new Buildable[T, LinkedList[T]] {
    override def builder = new Builder[T, LinkedList[T]] {
      val lbq = new LinkedList[T]
      def +=(t: T) = {
        lbq.add(t)
        this
      }
      def clear() = lbq.clear()
      def result = lbq
    }
  }
  implicit def LinkedListTraversable[T](lbq: LinkedList[T]): Traversable[T] = {
    lbq.asScala
  }
}
