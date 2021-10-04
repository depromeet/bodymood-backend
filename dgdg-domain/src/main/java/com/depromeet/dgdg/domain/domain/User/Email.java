package com.depromeet.dgdg.domain.domain.User;


import com.depromeet.dgdg.common.ErrorCode;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.ValidationException;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class Email {

    private final static Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9.%+-]+@[a-zA-Z0-9.,-]+\\.[a-zA-Z]{2,6}$");

    @Column(length = 50)
    private String email;

    private Email(String email) throws ValidationException{
        validateFormat(email);
        this.email = email;
    }

    private void validateFormat(String email) throws ValidationException {
        if (!EMAIL_REGEX.matcher(email).matches()) {
            throw new ValidationException(String.format("(%s)은 이메일 포맷에 맞지 않습니다", email), String.valueOf(ErrorCode.FORBIDDEN_EXCEPTION));
        }
    }

    public static Email of(String email) throws ValidationException {
        return new Email(email);
    }
}

