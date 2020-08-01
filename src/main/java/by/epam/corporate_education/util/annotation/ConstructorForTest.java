package by.epam.corporate_education.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.CONSTRUCTOR})
public @interface ConstructorForTest {
    String info() default "";
}
