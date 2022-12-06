package com.autog.register.service;

import com.autog.register.dto.request.EquipamentoRelatorio;
import com.autog.register.dto.request.EquipamentoRequest;
import com.autog.register.dto.response.EquipamentoComRegistro;
import com.autog.register.dto.response.EquipamentoResponse;
import com.autog.register.dto.response.GenericResponse;
import com.autog.register.dto.response.SucessResponse;
import com.autog.register.dto.response.DadoConsumoMesEquipamento;
import com.autog.register.dto.response.DadoGrafico;
import com.autog.register.entity.CLNBox;
import com.autog.register.entity.Equipamento;
import com.autog.register.entity.ListaObj;
import com.autog.register.entity.Registro;
import com.autog.register.repository.CLNBoxRepository;
import com.autog.register.repository.EmpresaRepository;
import com.autog.register.repository.EquipamentoRepository;
import com.autog.register.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository repository;

    public ResponseEntity registerEquipment(Equipamento newEquipment) {
        repository.save(newEquipment);
        return ResponseEntity.status(201).body(new SucessResponse("Equipamento cadastrado com sucesso!", newEquipment));
    }

    public ResponseEntity getEquipmentByPredio(Integer idPredio) {
        List<Equipamento> equipments = repository.getAllEquipments(idPredio);

        if (equipments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(equipments);
    }

    public ResponseEntity getEquipmentBySala(Integer idSala) {
        FiltroSingleton.getInstancia().setEquipamentoComRegistro(false);
        List<Equipamento> equipments = repository.getEquipamentoBySala(idSala);

        if (equipments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(equipments);
    }

    public ResponseEntity listarSalasComUltimoRegistro(Integer idSala) {
        FiltroSingleton.getInstancia().setEquipamentoComRegistro(true);
        List<Equipamento> equipamentos = repository.getEquipamentoBySala(idSala);

        if (equipamentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(equipamentos);
    }

    public ResponseEntity getEquipment(Integer idEquipamento) {
        List<Equipamento> equipments = repository.getEquipamento(idEquipamento);

        if (equipments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(equipments);
    }

    private LocalDate convertStringToLocalDate(String dateToBeFormatted) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.US);
        LocalDate date = LocalDate.parse(dateToBeFormatted, formatter);
        return date;
    }

    public ResponseEntity editEquipment(Integer id, EquipamentoRequest request) {
        if (repository.existsById(id)) {
            repository.updateEquipamento(
                    id,
                    request.getTipo(),
                    request.getInstalacao(),
                    request.getVidaUtil(),
                    request.getPotencia(),
                    request.getQtdEquipamento()
            );
            return ResponseEntity.ok().body(new GenericResponse(true));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity deleteEquipmentById(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteEquipamento(id);
            return ResponseEntity.ok().body(new GenericResponse(true));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity dadosGrafico(int idPredio, EmpresaRepository repository, RegistroRepository registroRepository,
                                       EquipamentoRepository equipamentoRepository, CLNBoxRepository clnBoxRepository) {

        List<DadoGrafico> listaDadoGrafico = new ArrayList<>();
        DadoGrafico dg = null;
        Integer inicioDosSeisMeses = LocalDateTime.now().getMonthValue() - 6;
        Integer finalDosSeisMeses = LocalDateTime.now().getMonthValue();
        Integer anoAtual = LocalDateTime.now().getYear();

        try {
            for (Integer i = inicioDosSeisMeses; i <= finalDosSeisMeses; i++) {
                System.out.println("i: " + i);

                EquipamentoRelatorio data = new EquipamentoRelatorio(idPredio, i, anoAtual);

                if (registroDeMesExiste(registroRepository, data)) {
                    if (i == finalDosSeisMeses) {
                        listaDadoGrafico.remove(0);
                    }

                    Double setTotalKwm = totalLampadaLigada(data, registroRepository, equipamentoRepository, clnBoxRepository);

                    if (!(repository.existsById(data.getIdPredio()))) {
                        Double totalKwm = 0.0;
                        Double totalReais = 0.0;

                        List<DadoConsumoMesEquipamento> listaLength = equipamentoRepository.infoConsumoMesEquipamento(data.getIdPredio());
                        if (!(listaLength.isEmpty())) {
                            ListaObj<DadoConsumoMesEquipamento> infoConsumoMes = new ListaObj<>(listaLength.size());
                            for (DadoConsumoMesEquipamento dcme : listaLength) {
                                infoConsumoMes.adiciona(dcme);
                            }

                            for (int j = 0; j < infoConsumoMes.getTamanho(); j++) {
                                DadoConsumoMesEquipamento dcme = infoConsumoMes.getElemento(j);
                                dcme.setConsumoKwm(setTotalKwm != null ? setTotalKwm : 0.0);
                                totalKwm += dcme.getConsumoKwm();
                                totalReais += dcme.getPreco();
                            }

                            dg = new DadoGrafico(data.getMes() + "/" + data.getAno(), totalKwm, totalReais);
                            listaDadoGrafico.add(dg);
                        } else {
                            // Lista vázia
                            return ResponseEntity.status(204).build();
                        }

                    } else {
                        // Id inexistente
                        return ResponseEntity.status(404).build();
                    }
                }

                if (listaDadoGrafico.isEmpty()) {
                    return ResponseEntity.status(204).build();
                }
            }

            if (listaDadoGrafico.isEmpty()) {
                return ResponseEntity.status(204).build();
            }

        } catch (Exception erro) {
            System.out.println("Erro ao puxar os dados do servidor: " + erro);
            return ResponseEntity.status(500).body("Erro ao puxar os dados do servidor - Consulte o ADM");
        }
        return ResponseEntity.status(200).body(listaDadoGrafico);
    }

    public Double totalLampadaLigada(EquipamentoRelatorio data, RegistroRepository registerRepository,
                                     EquipamentoRepository equipamentoRepository, CLNBoxRepository clnBoxRepository) {
        Double totalHoras = 0.0;
        LocalDateTime d1 = null;
        LocalDateTime d2 = null;
        Double potencia = null;
        List<Registro> lista = registerRepository.findByDataBetween(
                LocalDateTime.of(
                        data.getAno(),
                        data.getMes(),
                        01, 00, 00),
                calculoUltimoDiaMes(data.getMes(), data.getAno()));

        //Predio predio = predioRepository.findByIdPredio(data.getIdPredio());
        List<CLNBox> clnBox = clnBoxRepository.filtrandoCLNBoxConectadosComEquipamentosDoPredio(data.getIdPredio());
        List<Equipamento> consumoEquipamento = equipamentoRepository.filtrandoEquipamentoConectadosComCLNBox(data.getIdPredio());

        if (!lista.isEmpty() && !clnBox.isEmpty()) {
            for (int j = 0; j < clnBox.size(); j++) {

                for (int i = 0; i < lista.size(); i++) {

                    if (lista.get(i).isLigado() &&
                            lista.get(i).getEquipamento().getClnBox().getIdCLNBox()
                                    == clnBox.get(j).getIdCLNBox()) {

                        d1 = LocalDateTime.of(lista.get(i).getData().getYear(), lista.get(i).getData().getMonth(),
                                lista.get(i).getData().getDayOfMonth(),
                                lista.get(i).getData().getHour(), lista.get(i).getData().getMinute());

                    } else if (lista.get(i).getEquipamento().getClnBox().getIdCLNBox()
                            == clnBox.get(j).getIdCLNBox()) {

                        d2 = LocalDateTime.of(lista.get(i).getData().getYear(),
                                lista.get(i).getData().getMonth(), lista.get(i).getData().getDayOfMonth(),
                                lista.get(i).getData().getHour(), lista.get(i).getData().getMinute());
                    }

                    if (d1 != null && d2 != null) {
                        long diferenca = ChronoUnit.MINUTES.between(d1, d2);

                        //long diferenca = Duration.between(d1,d2);
                        totalHoras += diferenca;
                        d1 = null;
                        d2 = null;

                        potencia = (totalHoras / 60) * consumoEquipamento.get(j).getPotencia();
                        System.out.println("potencia: " + (totalHoras / 60) * consumoEquipamento.get(j).getPotencia());

//                        potencia = (totalHoras / 60) * predio.getSalas().get(j).getClnBoxes().getEquipment().get(j).getPotency();
                    }
                }
            }
        }

        return potencia;
    }

    public LocalDateTime calculoUltimoDiaMes(int mes, int ano) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, mes - 1);
        int dias = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return LocalDateTime.of(ano, mes, dias, 00, 00);
    }

    public boolean registroDeMesExiste(RegistroRepository registerRepository, EquipamentoRelatorio data) {
        List<Registro> lista = registerRepository.findByDataBetween(
                LocalDateTime.of(
                        data.getAno(),
                        data.getMes(),
                        01, 00, 00),
                calculoUltimoDiaMes(data.getMes(), data.getAno()));

        return lista.isEmpty() == true ? false : true;
    }


}
