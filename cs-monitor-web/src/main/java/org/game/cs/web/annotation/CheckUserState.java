package org.game.cs.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.game.cs.core.model.enums.UserState;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckUserState {

    UserState requiredState() default UserState.CONNECTED;
    
}
