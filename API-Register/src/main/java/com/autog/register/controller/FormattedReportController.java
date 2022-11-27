package com.autog.register.controller;

import com.autog.register.dto.request.EquipamentoRelatorio;
import com.autog.register.repository.*;
import com.autog.register.service.FormattedReportService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/reports")
public class FormattedReportController {

    @Autowired
    private ValorTarifaRepository valorRelatorioRepository;

    @Autowired
    private RelatorioRepository relatorioRepository;

    @Autowired
    private PredioRepository predioRepository;

    @Autowired
    private EmpresaRepository repository;

    @Autowired
    private RegistroRepository registerRepository;

    @Autowired
    private CLNBoxRepository clnBoxRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;


    @PostMapping("/csv")
    public ResponseEntity geracaoRelatorioCsv(@RequestBody EquipamentoRelatorio data) {
        return new FormattedReportService().geracaoRelatorioCsv(data, repository, registerRepository, equipamentoRepository,
                valorRelatorioRepository, clnBoxRepository, predioRepository, enderecoRepository);
    }

    @PostMapping("/txt")
    public ResponseEntity geracaoRelatorioTxt(@RequestBody EquipamentoRelatorio data) {
        return new FormattedReportService().geracaoRelatorioTxt(data, repository, registerRepository, equipamentoRepository,
                valorRelatorioRepository, clnBoxRepository, predioRepository, enderecoRepository);
    }

    @CrossOrigin(value = {"*"}, exposedHeaders = {"Content-Disposition"})
    @PostMapping(value = "/importacao/{idPredio}")
    public ResponseEntity importacaoTxt(@PathVariable int idPredio,
                                        @NotNull @RequestParam("file") MultipartFile file) throws IOException{
        return new FormattedReportService().importacaoTxt(idPredio, file, repository, predioRepository, relatorioRepository);
    }

    // Endpoint para tela de Relatório de Consumo (Traz todos os dados da tabela)
    @PostMapping("/tabela")
    public ResponseEntity tabelaConsumo(@RequestBody EquipamentoRelatorio data) {
        return new FormattedReportService().informacoesTabela(data, repository, registerRepository, equipamentoRepository,
                valorRelatorioRepository, clnBoxRepository, predioRepository, enderecoRepository);
    }

    // Enpoint para tela de Relatório de Consumo (Traz uma lista das salas e seus consumos)
    @PostMapping("/listaconsumo")
    public ResponseEntity listaConsumoSala(@RequestBody EquipamentoRelatorio data) {
        return new FormattedReportService().informacoesDeConsumo(data, repository, registerRepository, equipamentoRepository,
                valorRelatorioRepository, clnBoxRepository);
    }

    // Endpoint do Gráfico (Resumo de Consumo)
    @PostMapping("/grafico/{idPredio}")
    public ResponseEntity GraficoDeSeisMeses(@PathVariable int idPredio) {
        return new FormattedReportService().dadosGrafico(idPredio,repository, registerRepository, equipamentoRepository,
                clnBoxRepository);
    }

}
