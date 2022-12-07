package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.assembler.OcorrenciaAssembler;
import com.algaworks.algalog.api.model.OcorrenciaModel;
import com.algaworks.algalog.api.model.input.OcorrenciaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.service.BuscaEntregaService;
import com.algaworks.algalog.domain.service.ResgistroOcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private BuscaEntregaService buscaEntregaService;
    private ResgistroOcorrenciaService resgistroOcorrenciaService;
    private OcorrenciaAssembler ocorrenciaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long entregaId,
                 @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {

        Ocorrencia ocorrenciaRegistrada = resgistroOcorrenciaService
                .registrar( entregaId, ocorrenciaInput.getDescricao());

        return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);

        }

        @GetMapping
        public List<OcorrenciaModel> listar(@PathVariable Long entregaId) {
            Entrega entrega = buscaEntregaService.buscar(entregaId);
            return ocorrenciaAssembler.toCollectionModels(entrega.getOcorrencias());
        }
    }
