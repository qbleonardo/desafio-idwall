package com.desafioIdwall.exceptions.handler;

import com.desafioIdwall.exceptions.*;
import com.desafioIdwall.exceptions.response.ErrorData;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerHandlerException {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorData> handleConstraintViolationException(ConstraintViolationException cVE){
        ErrorData response = ErrorData.builder()
                .code(HttpStatus.BAD_REQUEST.name().concat(" - ").concat(String.valueOf(HttpStatus.BAD_REQUEST.value())))
                .title(String.format("Formato incorreto no %s", cVE.getConstraintViolations().stream().map(ConstraintViolation::getPropertyPath).collect(Collectors.toList())))
                .detail(cVE.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorData> handleMissingServletRequestParameterException(MissingServletRequestParameterException mSR){
        ErrorData response = ErrorData.builder()
                .code(HttpStatus.BAD_REQUEST.name().concat(" - ").concat(String.valueOf(HttpStatus.BAD_REQUEST.value())))
                .title(String.format("Formato incorreto no campo: %s", mSR.getParameterName()))
                .detail(mSR.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameFbiNotFoundException.class)
    public ResponseEntity<ErrorData> handleNameFbiNotFoundException(NameFbiNotFoundException nNF){
        ErrorData response = ErrorData.builder()
                .code(HttpStatus.BAD_REQUEST.name().concat(" - ").concat(String.valueOf(HttpStatus.BAD_REQUEST.value())))
                .title("Nome não encontrado")
                .detail("Este nome não encontra-se na lista de procurados do FBI. Favor validar nome procurado.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameInterpolNotFoundException.class)
    public ResponseEntity<ErrorData> handleNameInterpolNotFoundException(NameInterpolNotFoundException nIn){
        ErrorData response = ErrorData.builder()
                .code(HttpStatus.BAD_REQUEST.name().concat(" - ").concat(String.valueOf(HttpStatus.BAD_REQUEST.value())))
                .title("Nome não encontrado")
                .detail("Este nome não encontra-se na lista de procurados da Interpol. Favor validar nome procurado.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameNotFoundException.class)
    public ResponseEntity<ErrorData> handleNameNotFoundException(NameNotFoundException nameNotFoundException){
        ErrorData response = ErrorData.builder()
                .code(HttpStatus.NOT_FOUND.name().concat(" - ").concat(String.valueOf(HttpStatus.NOT_FOUND.value())))
                .title("Nome não encontrado na base de dados")
                .detail("Este nome não encontra-se na base de dados da aplicação. Favor validar nome procurado ou iniciar busca para armazena-lo.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AnoNascimentNotFoundException.class)
    public ResponseEntity<ErrorData> handleAnoNascimentNotFoundExceptionException(AnoNascimentNotFoundException anoNascimentNotFoundException){
        ErrorData response = ErrorData.builder()
                .code(HttpStatus.NOT_FOUND.name().concat(" - ").concat(String.valueOf(HttpStatus.NOT_FOUND.value())))
                .title("Ano de nascimento não encontrado na base de dados")
                .detail("Este ano de nascimento não encontra-se na base de dados da aplicação. Favor validar nome procurado ou iniciar busca para armazena-lo.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoriaNotFoundException.class)
    public ResponseEntity<ErrorData> handleCategoriaNotFoundException(CategoriaNotFoundException categoriaNotFoundException){
        ErrorData response = ErrorData.builder()
                .code(HttpStatus.NOT_FOUND.name().concat(" - ").concat(String.valueOf(HttpStatus.NOT_FOUND.value())))
                .title("Categoria não localizada!")
                .detail(categoriaNotFoundException.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorData> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException mAt){
        ErrorData response = ErrorData.builder()
                .code(HttpStatus.BAD_REQUEST.name().concat(" - ").concat(String.valueOf(HttpStatus.BAD_REQUEST.value())))
                .title(String.format("%s não encontrada", mAt.getName()))
                .detail(String.format("Valor: [%s] informado não pertence a categoria de busca referente à AML. Por favor revisitar as categorias disponíveis ou pesquise pelo método responsável por nome.", mAt.getValue().toString()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
