package com.fiap.techchalleng.best_food.application.response;

import com.fiap.techchalleng.best_food.domain.generic.presenter.PresenterListInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PresenterListResponse {
    public ResponseEntity<Object> response(PresenterListInterface presenterListInterface) {

        if (presenterListInterface.getOutput().getOutputStatus().getCode() == 200) {
            return ResponseEntity.status(HttpStatus.OK).body(presenterListInterface.toArray());
        }

        if (presenterListInterface.getOutput().getOutputStatus().getCode() == 201) {
            return ResponseEntity.status(HttpStatus.OK).body(presenterListInterface.toArray());
        }

        if (presenterListInterface.getOutput().getOutputStatus().getCode() == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(presenterListInterface.toArray());
        }

        if (presenterListInterface.getOutput().getOutputStatus().getCode() == 204) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(presenterListInterface.toArray());
        }

        if (presenterListInterface.getOutput().getOutputStatus().getCode() == 422) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(presenterListInterface.toArray());
        }

        if (presenterListInterface.getOutput().getOutputStatus().getCode() == 400) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(presenterListInterface.toArray());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(presenterListInterface.toArray());
    }
}
