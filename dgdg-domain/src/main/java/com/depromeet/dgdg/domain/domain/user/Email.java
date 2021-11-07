package com.depromeet.dgdg.domain.domain.user;

import com.depromeet.dgdg.common.exception.BadRequestException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

import static com.depromeet.dgdg.common.ErrorCode.BAD_REQUEST_WRONG_EMAIL_FORMAT_EXCEPTION;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Email {

    private final static Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9.%+-]+@[a-zA-Z0-9.,-]+\\.[a-zA-Z]{2,6}$");

    @Column(length = 50)
    private String email;

    private Email(String email) {
        validateFormat(email);
        this.email = email;
    }

    private void validateFormat(String email) {
        if (!EMAIL_REGEX.matcher(email).matches()) {
            throw new BadRequestException(String.format("(%s)은 이메일 포맷에 맞지 않습니다", email), BAD_REQUEST_WRONG_EMAIL_FORMAT_EXCEPTION);
        }
    }

    public static Email of(String email) {
        return new Email(email);
    }

}

