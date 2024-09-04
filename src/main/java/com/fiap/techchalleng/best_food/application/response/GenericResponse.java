package com.fiap.techchalleng.best_food.application.response;

import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GenericResponse {
    public ResponseEntity<Object> response(OutputInterface outputInterface) {
        if (outputInterface.getOutputStatus().getCode() == HttpStatus.OK.value()) {
            return ResponseEntity.status(HttpStatus.OK).body(outputInterface.getBody());
        }

        if (outputInterface.getOutputStatus().getCode() == HttpStatus.CREATED.value()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(outputInterface.getBody());
        }

        if (outputInterface.getOutputStatus().getCode() == HttpStatus.NOT_FOUND.value()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(outputInterface.getBody());
        }

        if (outputInterface.getOutputStatus().getCode() == HttpStatus.NO_CONTENT.value()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(outputInterface.getBody());
        }

        if (outputInterface.getOutputStatus().getCode() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(outputInterface.getBody());
        }

        if (outputInterface.getOutputStatus().getCode() == HttpStatus.NOT_FOUND.value()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(outputInterface.getBody());
        }

        if (outputInterface.getOutputStatus().getCode() == HttpStatus.BAD_REQUEST.value()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(outputInterface.getBody());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(outputInterface.getBody());
    }
}
