package com.supercassa.apitask.controller;

import com.supercassa.apitask.api.model.Request;
import com.supercassa.apitask.api.model.Response;
import com.supercassa.apitask.entity.SuperKassa;
import com.supercassa.apitask.exception.ApiRequestException;
import com.supercassa.apitask.service.SuperCassaService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.supercassa.apitask.util.RequestUtil.validateJsonAndMap;

@RestController
@RequestMapping
@Slf4j
public class SuperKassaController {

    @Autowired
    private SuperCassaService superCassaService;

    @PostMapping("/modify")
    public ResponseEntity<Response> modify(@RequestBody String json) {
        log.info(String.format("Json object: %s", json));

        Request request = validateJsonAndMap(json);

        SuperKassa superKassa;
        try {
            superKassa = superCassaService.findSupperKassaById(request.id());
        } catch (ObjectNotFoundException e) {
            throw new ApiRequestException(e.getMessage());
        }

        superKassa.getResponse().setCurrent(superKassa.getResponse().getCurrent() + request.add());


        superCassaService.save(superKassa);
        log.info(String.format("Super Kassa response: %s", superKassa.getResponse()));

        return new ResponseEntity<>(superKassa.getResponse(), HttpStatus.OK);


    }


}
