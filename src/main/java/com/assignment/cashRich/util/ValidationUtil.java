package com.assignment.cashRich.util;

import com.assignment.cashRich.constant.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;

@Slf4j
public class ValidationUtil {

    public static void validateMandatoryHeader(HttpServletRequest request) {

        var application = request.getHeader(GenericConstant.APPLICATION);
        var channel = request.getHeader(GenericConstant.CHANNEL);
        if (application == null || channel == null) {
            log.error("Invalid value of header application and channel");
            throw new RuntimeException("Null value passed to the headers");
        }

        if (!(EnumUtils.isValidEnum(Application.class, application.toUpperCase())
                && EnumUtils.isValidEnum(Channel.class, channel.toUpperCase()))) {
            log.error("invalid request : for application {} , for channel {}", application, channel);
            throw new RuntimeException("Invalid value of headers provided");
        }
    }
}
