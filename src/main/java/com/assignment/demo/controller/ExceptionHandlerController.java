package com.assignment.demo.controller;

import com.assignment.demo.controller.dto.ErrorMessage;
import com.assignment.demo.exception.AlreadyExistException;
import com.assignment.demo.exception.NoRecordFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NoRecordFoundException.class)
    public ErrorMessage handleRequiredNoRecordFoundException(NoRecordFoundException exc) {
        return new ErrorMessage( "لا توجد نتائج", "لاتوجد نتائج للبحث الذي قمت به",
                "No Record Found", "No record found for this enquiry", exc);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistException.class)
    public ErrorMessage handleRequiredAlreadyExistException(AlreadyExistException exc) {
        return new ErrorMessage( "مدخل سابقا", "الرجاء التاكد من المدخلات لان ماتم ادخاله سبق وان تم تسجيله",
                "Already Exist", "Please check the inputs because the entered data already exist", exc);
    }
}
