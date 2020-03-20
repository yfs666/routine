package lambdasinaction.chap10;

import java.util.*;

import static java.util.Optional.of;
import static java.util.Optional.empty;

public class OperationsWithOptional {

    public static void main(String... args) {
        System.out.println(max(of(3), of(5)));
        System.out.println(max(empty(), of(5)));

        Optional<Person> optionalPerson = Optional.ofNullable(null);
        Optional<Optional<Car>> car = optionalPerson.map(Person::getCar);
        Optional<Car> car1 = optionalPerson.flatMap(Person::getCar);
        Optional<String> s = optionalPerson.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName);

        Optional<Insurance> insurance = optionalPerson.flatMap(Person::getCar).flatMap(Car::getInsurance);

    }

    public static final Optional<Integer> max(Optional<Integer> i, Optional<Integer> j) {
         return i.flatMap(a -> j.map(b -> Math.max(a, b)));
    }
}
