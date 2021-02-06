package com.llycloud.lms.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

/**
 * @author Akide Liu
 * Date : 6/2/21
 */
public class UserAlreadyExistsException extends AbstractThrowableProblem {

    public UserAlreadyExistsException(String message) {

        super(null,message, Status.CONFLICT);

    }
}
