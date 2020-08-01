package by.epam.corporate_education.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
public @interface SetterForTest{
    String info() default "";
}
