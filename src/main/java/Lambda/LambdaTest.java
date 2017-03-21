package Lambda;

import java.util.DoubleSummaryStatistics;
import java.util.Objects;
import java.util.function.Function;

import static Lambda.BasicCalc.divide;

/**
 * Created by eli9 on 3/15/2017.
 */
public class LambdaTest {
    public static Function<Double, Function<Double, Double>> division = first ->
            second -> {
                Double result = null;
                if (Objects.nonNull(first) && Objects.nonNull(second)) {
                    //divide实现的是 UnaryOperator<Double> interface
                    // UnaryOperator extends from function, so we must use function apply
                    result = divide(second).apply(first);
                }
                return result;
            };

    public static void main(String[] args) {
        Function<Double,Double> t = division.apply(2.3);
        System.out.println(t.apply(2.6));
    }
}

