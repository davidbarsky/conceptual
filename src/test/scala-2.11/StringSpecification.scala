import org.scalacheck.{Properties, Gen}
import org.scalacheck.Prop.forAll

object StringSpecification extends Properties("String") {
  property("startsWith") = forAll { (a: String, b: String) =>
    (a+b).startsWith(a)
  }

  property("concatenate") = forAll { (a: String, b: String) =>
    (a+b).length >= a.length && (a+b).length >= b.length
  }

  property("substring") = forAll { (a: String, b: String, c: String) =>
    (a+b+c).substring(a.length, a.length+b.length) == b
  }
}

object IntegerSpecification extends Properties("User") {
  val smallInteger = Gen.choose(0, 100)

  property("numberIsWithinRange") = forAll(smallInteger) { n =>
    n >= 0 && n <= 100
  }
}