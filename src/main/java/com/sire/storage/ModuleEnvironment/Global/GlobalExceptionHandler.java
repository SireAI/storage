package com.sire.storage.ModuleEnvironment.Global;


import com.sire.storage.ModuleEnvironment.Http.HttpBusinessCode;
import com.sire.storage.ModuleEnvironment.Http.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResponse defaultErrorHandler(HttpServletRequest req, Exception error) throws Exception {
        logger.error(error.getMessage());
        JsonResponse<String> errorInor = JsonResponse.createMessageResponse(HttpBusinessCode.UNDEFINED_ERROR, error.getMessage());
        return errorInor;
    }
}