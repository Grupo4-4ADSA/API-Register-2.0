package com.autog.register.service;

import com.autog.register.dto.request.*;
import com.autog.register.dto.response.InfoEmpresaRelatorio;
import com.autog.register.dto.response.DadoConsumoMes;
import com.autog.register.dto.response.TabelaConsumo;
import com.autog.register.entity.*;
import com.autog.register.repository.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;


@Service
public class FormattedReportService {


    public ResponseEntity geracaoRelatorioCsv(EquipamentoRelatorio data, EmpresaRepository repository,
                                              RegistroRepository registerRepository, EquipamentoRepository equipamentoRepository,
                                              ValorTarifaRepository rateValueRepository, CLNBoxRepository clnBoxRepository) {

        Double setTotalKwm = totalLampadaLigada(data, registerRepository, equipamentoRepository, clnBoxRepository);
        String relatorio = "";

        try {
            if (!(repository.existsById(data.getIdPredio()))) {
                Double totalKwm = 0.0;
                Double totalReais = 0.0;
                InfoEmpresaRelatorio corpo1 = repository.infoEmpresa(data.getIdPredio());
                ValorTarifa rv = rateValueRepository.findByDateBetween(
                        LocalDateTime.of(data.getAno(), data.getMes(), 01, 00, 00),
                        calculoUltimoDiaMes(data.getMes(), data.getAno()));

                relatorio += String.format("%s;%s;Bandeira - %s\n\n", "AUTG", "0" + data.getMes() + "/" + data.getAno(), rv.getBandeira());

                relatorio += String.format("%s;%s;%s\n", "Nome responsavel", "Razao Social", "CNPJ");
                relatorio += String.format("%s;%s;%s\n\n", corpo1.getNomeGestor(), corpo1.getNomeEmpresa(),
                        corpo1.getCnpj());

                relatorio += String.format("%s;%s;%s;%s\n", "Nome do predio", "Logradouro", "Numero", "CEP");
                relatorio += String.format("%s;%s;%d;%s\n\n", corpo1.getNomePredio(), corpo1.getEnderecoPredio(),
                        corpo1.getNumeroEnderecoPredio(), corpo1.getCep());

                List<DadoConsumoMes> listaLength = repository.infoConsumoMes(data.getIdPredio());
                if (!(listaLength.isEmpty())) {
                    ListaObj<DadoConsumoMes> corpo2 = new ListaObj<>(listaLength.size());
                    for (DadoConsumoMes mc : listaLength) {
                        corpo2.adiciona(mc);
                    }

                    relatorio += String.format("%s;%s;%s;%s\n", "Sala", "Andar", "Consumo kwm", "Preco");
                    for (int i = 0; i < corpo2.getTamanho(); i++) {
                        DadoConsumoMes mc = corpo2.getElemento(i);
                        mc.setConsumoKwm(setTotalKwm);
                        relatorio += String.format("%s;%d;", mc.getName(), mc.getFloor());
                        relatorio += String.format("%012.2f;", mc.getConsumoKwm());
                        relatorio += String.format("%012.2f\n", mc.getPreco());
                        totalKwm += mc.getConsumoKwm();
                        totalReais += mc.getPreco();
                    }

                    relatorio += String.format("\n%s;%s\n", "Resumo", "R$");
                    relatorio += String.format("%s;%.2f\n", "Total - kwm: ", totalKwm);
                    relatorio += String.format("%s;%.2f\n", "Total - R$: ", totalReais);
                } else {
                    // Lista vázia
                    return ResponseEntity.status(204).build();
                }

            } else {
                // Id inexistente
                return ResponseEntity.status(404).build();
            }
        } catch (Exception erro) {
            System.out.println("Erro ao puxar os dados do servidor: " + erro);
        }

        return ResponseEntity
                .status(200)
                .header("content-type", "application/csv")
                .header("content-disposition", "filename=\"relatorio-AUTG-CLN.csv\"")
                .body(relatorio);
    }

    public LocalDateTime calculoUltimoDiaMes(int mes, int ano) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, mes - 1);
        int dias = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return LocalDateTime.of(ano, mes, dias, 00, 00);
    }


    public Double totalLampadaLigada(EquipamentoRelatorio data, RegistroRepository registerRepository,
                                     EquipamentoRepository equipamentoRepository, CLNBoxRepository clnBoxRepository) {
        Double totalHoras = 0.0;
        LocalDateTime d1 = null;
        LocalDateTime d2 = null;
        Double potencia = null;
        List<Registro> lista = registerRepository.findByDateBetween(
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

                    if (lista.get(i).isOn() &&
                            lista.get(i).getEquipment().getIdEquipamento()
                                    == clnBox.get(j).getIdCLNBox()) {

                        d1 = LocalDateTime.of(lista.get(i).getDate().getYear(), lista.get(i).getDate().getMonth(),
                                lista.get(i).getDate().getDayOfMonth(),
                                lista.get(i).getDate().getHour(), lista.get(i).getDate().getMinute());

                    } else if (lista.get(i).getEquipment().getIdEquipamento()
                            == clnBox.get(j).getIdCLNBox()) {

                        d2 = LocalDateTime.of(lista.get(i).getDate().getYear(),
                                lista.get(i).getDate().getMonth(), lista.get(i).getDate().getDayOfMonth(),
                                lista.get(i).getDate().getHour(), lista.get(i).getDate().getMinute());

                    }

                    if (d1 != null && d2 != null) {
                        long diferenca = ChronoUnit.MINUTES.between(d1, d2);

                        //long diferenca = Duration.between(d1,d2);
                        totalHoras += diferenca;
                        d1 = null;
                        d2 = null;

                        potencia = (totalHoras / 60) * consumoEquipamento.get(j).getPotencia();
//                        potencia = (totalHoras / 60) * predio.getSalas().get(j).getClnBoxes().getEquipment().get(j).getPotency();
                        ;
                    }
                }
            }
        }

        return potencia;
    }

    public static void gravaRelatorio(String registro, String nomeArq) {
        BufferedWriter saida = null;

        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException ex) {
            System.out.println("Erro ao abrir o arquivo: " + ex);
        }

        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException ex) {
            System.out.println("Erro ao gravar o arquivo: " + ex);
        }
    }

    public ResponseEntity geracaoRelatorioTxt(EquipamentoRelatorio data, EmpresaRepository repository,
                                              RegistroRepository registerRepository, EquipamentoRepository equipamentoRepository,
                                              ValorTarifaRepository rateValueRepository, CLNBoxRepository clnBoxRepository) {

        int contaCorpo = 0;
        String nomeArq = "relatorio.txt";
        InputStreamResource resource = null;
        Double setTotalKwm = totalLampadaLigada(data, registerRepository, equipamentoRepository, clnBoxRepository);

        File file = new File("relatorio.txt");
        file.delete();

        try {
            if (!(repository.existsById(data.getIdPredio()))) {
                InfoEmpresaRelatorio dadosEmpresa = repository.infoEmpresa(data.getIdPredio());
                List<DadoConsumoMes> listaLength = repository.infoConsumoMes(data.getIdPredio());
                ValorTarifa rv = rateValueRepository.findByDateBetween(
                        LocalDateTime.of(data.getAno(), data.getMes(), 01, 00, 00),
                        calculoUltimoDiaMes(data.getMes(), data.getAno()));

                String header = "00Relatorio_AUTG";
                header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                header += "00";
                gravaRelatorio(header, "relatorio.txt");

                String corpo1 = "02";
                corpo1 += String.format("%-5.5s", "AUTG");
                corpo1 += String.format("%-9.9s", "0" + data.getMes() + "/" + data.getAno());
                corpo1 += String.format("%-25.25s", "Bandeira - " + rv.getBandeira());
                gravaRelatorio(corpo1, "relatorio.txt");
                contaCorpo++;

                String corpo2 = "03";
                corpo2 += String.format("%-25.25s", dadosEmpresa.getNomeGestor());
                corpo2 += String.format("%-35.35s", dadosEmpresa.getNomeEmpresa());
                corpo2 += String.format("%-14.14s", dadosEmpresa.getCnpj());
                gravaRelatorio(corpo2, "relatorio.txt");
                contaCorpo++;

                String corpo3 = "04";
                corpo3 += String.format("%-25.25s", dadosEmpresa.getNomePredio());
                corpo3 += String.format("%-35.35s", dadosEmpresa.getEnderecoPredio());
                corpo3 += String.format("%05d", dadosEmpresa.getNumeroEnderecoPredio());
                corpo3 += String.format("%-8.8s", dadosEmpresa.getCep());
                gravaRelatorio(corpo3, "relatorio.txt");
                contaCorpo++;

                if (!(listaLength.isEmpty())) {
                    ListaObj<DadoConsumoMes> dadosSala = new ListaObj<>(listaLength.size());
                    for (DadoConsumoMes mc : listaLength) {
                        dadosSala.adiciona(mc);
                    }

                    String corpo4;
                    Double totalKwm = 0.0;
                    Double totalReais = 0.0;
                    for (int i = 0; i < dadosSala.getTamanho(); i++) {
                        corpo4 = "05";
                        DadoConsumoMes mc = dadosSala.getElemento(i);
                        mc.setConsumoKwm(setTotalKwm);
                        corpo4 += String.format("%-25.25s", mc.getName());
                        corpo4 += String.format("%03d", mc.getFloor());
                        corpo4 += String.format("%012.2f", mc.getConsumoKwm());
                        corpo4 += String.format("%012.2f", mc.getPreco());
                        totalKwm += mc.getConsumoKwm();
                        totalReais += mc.getPreco();
                        contaCorpo++;
                        gravaRelatorio(corpo4, "relatorio.txt");
                    }

                    String corpo5 = "06";
                    corpo5 += String.format("%012.2f", totalKwm);
                    corpo5 += String.format("%012.2f", totalReais);
                    gravaRelatorio(corpo5, "relatorio.txt");
                    contaCorpo++;

                    String trailer = "01";
                    trailer += String.format("%07d", contaCorpo);
                    gravaRelatorio(trailer, nomeArq);

                    resource = new InputStreamResource(new FileInputStream(new File("relatorio.txt")));

                } else {
                    // Lista vázia
                    return ResponseEntity.status(204).build();
                }

            } else {
                // Id inexistente
                return ResponseEntity.status(404).build();
            }
        } catch (Exception erro) {
            System.out.println("Erro ao puxar os dados do servidor: " + erro);
        }

        return ResponseEntity
                .status(200)
                .header("content-type", "application/txt")
                .header("content-disposition", "filename=\"relatorio-AUTG-CLN.txt\"")
                .body(resource);
    }


    String caminho = System.getProperty("user.home");
    String caminhoArquivo = caminho + File.separator + "Downloads" + File.separator;
    Path path = Paths.get(caminhoArquivo);


    public ResponseEntity importacaoTxt(int idPredio, MultipartFile file, EmpresaRepository repository,
                                        PredioRepository predioRepository, RelatorioRepository relatorioRepository) throws IOException {

        String message = "";

        if (!(repository.existsById(idPredio))) {
            try {
//            Files.copy(file.getInputStream(), this.path.resolve(file.getOriginalFilename()));
//            System.out.println(file.getOriginalFilename());
                try {
                    message = "Carregou o arquivo com sucesso:" + file.getOriginalFilename();

                    BufferedReader entrada = null;
                    String registro, tipoRegistro;
                    String empresaPrestadora, dataRelatorio, bandeira, gestorResponsavel, razaoSocial,
                            cnpj, nomeEdificio, logradouro, cep, sala;
                    Integer numero, andar;
                    Double consumoKwm, consumoReais, totalKwm, totalReais;

                    int contaRegCorpoLido = 0;
                    int qtdRegCorpoGravado;

                    RequisicaoInfoRelatorio rir = null;
                    RequisicaoInfoEmpresaRelatorio rcr = null;
                    RequisicaoInfoEnderecoRelatorio rar = null;
                    RequisicaoInfoConsumoRelatorio rcnr = null;
                    List<RequisicaoDadoConsumoMesRelatorio> listaConsumo = new ArrayList<>();

                    String caminhoFolder = caminhoArquivo + file.getOriginalFilename();

                    try {
                        entrada = new BufferedReader(new FileReader(caminhoFolder));
                    } catch (IOException ex) {
                        System.out.println("Erro ao abrir o arquivo: " + ex);
                    }

                    try {
                        registro = entrada.readLine();

                        while (registro != null) {
                            tipoRegistro = registro.substring(0, 2);
                            if (tipoRegistro.equals("00")) {
                                System.out.println("É um registro de header");
                                System.out.println("Tipo de arquivo: " + registro.substring(2, 16));
                                System.out.println("Data e hora gravação: " + registro.substring(16, 35));
                                System.out.println("Versão do documento: " + registro.substring(35, 37));
                            } else if (tipoRegistro.equals("01")) {
                                System.out.println("É um registro de trailer");
                                qtdRegCorpoGravado = Integer.parseInt(registro.substring(2, 9));
                                if (contaRegCorpoLido == qtdRegCorpoGravado) {
                                    System.out.println("Quantidade de registros lidos são compatíveis com a " +
                                            "quantidade de registros gravados");
                                } else {
                                    System.out.println("Quantidade de registros lidos não são compatíveis com a " +
                                            "quantidade de registros gravados");
                                }
                            } else if (tipoRegistro.equals("02")) {
                                System.out.println("É um registro de corpo 1 - Informações do relatório");
                                empresaPrestadora = registro.substring(02, 7).trim();
                                dataRelatorio = registro.substring(07, 15).trim();
                                bandeira = registro.substring(15, 35).trim();
                                contaRegCorpoLido++;

                                rir = new RequisicaoInfoRelatorio(empresaPrestadora, dataRelatorio, bandeira);
                            } else if (tipoRegistro.equals("03")) {
                                System.out.println("É um registro de corpo 2 - Informações da empresa contratante");
                                gestorResponsavel = registro.substring(02, 27).trim();
                                razaoSocial = registro.substring(27, 62).trim();
                                cnpj = registro.substring(62, 76).trim();
                                contaRegCorpoLido++;

                                rcr = new RequisicaoInfoEmpresaRelatorio(gestorResponsavel, razaoSocial, cnpj);
                            } else if (tipoRegistro.equals("04")) {
                                System.out.println("É um registro de corpo 3 - Informações da localidade do edifício");
                                nomeEdificio = registro.substring(02, 27).trim();
                                logradouro = registro.substring(27, 62).trim();
                                numero = Integer.valueOf(registro.substring(62, 67));
                                cep = registro.substring(67, 75).trim();
                                contaRegCorpoLido++;

                                rar = new RequisicaoInfoEnderecoRelatorio(nomeEdificio, logradouro, numero, cep);

                            } else if (tipoRegistro.equals("05")) {
                                System.out.println("É um registro de corpo 4 - Informações de consumo");
                                sala = registro.substring(02, 27).trim();
                                andar = Integer.valueOf(registro.substring(27, 30));
                                consumoKwm = Double.valueOf(registro.substring(30, 42).replace(',', '.'));
                                consumoReais = Double.valueOf(registro.substring(42, 54).replace(',', '.'));
                                contaRegCorpoLido++;

                                RequisicaoDadoConsumoMesRelatorio rmcr = new RequisicaoDadoConsumoMesRelatorio(sala, andar, consumoKwm, consumoReais);
                                listaConsumo.add(rmcr);
                            } else if (tipoRegistro.equals("06")) {
                                System.out.println("É um registro de corpo 5 - Total de consumo kwm e em reais");
                                totalKwm = Double.valueOf(registro.substring(02, 14).replace(',', '.'));
                                totalReais = Double.valueOf(registro.substring(14, 26).replace(',', '.'));
                                contaRegCorpoLido++;

                                rcnr = new RequisicaoInfoConsumoRelatorio(totalKwm, totalReais);
                            } else {
                                System.out.println("Tipo de registro inválido!");
                            }

                            registro = entrada.readLine();
                        }

                        entrada.close();

                    } catch (IOException ex) {
                        System.out.println("Erro ao abrir o arquivo: " + ex);
                    }

                    System.out.println("\nConteúdo importado:");
                    System.out.println(rir);
                    System.out.println(rcr);
                    System.out.println(rar);
                    for (RequisicaoDadoConsumoMesRelatorio rmcr : listaConsumo) {
                        System.out.println(rmcr);
                    }
                    System.out.println(rcnr);

                    Predio b = predioRepository.findByIdPredio(idPredio);
                    relatorioRepository.save(new Relatorio(rir.getEmpresaPrestadora(), rir.getDataRelatorio(),
                            rir.getBandeira(), rcr.getGestorResponsavel(), rcr.getRazaoSocial(),
                            rcr.getCnpj(), rcnr.getTotalKwm(), rcnr.getTotalReais(),
                            file.getOriginalFilename(), b));

                    return ResponseEntity.status(200).body(message);

                } catch (Exception e) {
                    message = "Não foi possível fazer upload do arquivo: " + file.getOriginalFilename() + "!";
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
                }
            } catch (Exception e) {
                throw new RuntimeException("Não foi possível armazenar o arquivo. Erro: " + e.getMessage());
            }
        } else {
            // Id inexistente
            return ResponseEntity.status(404).build();
        }
    }


    public ResponseEntity informacoesTabela(EquipamentoRelatorio data, EmpresaRepository repository,
                                            RegistroRepository registroRepository, EquipamentoRepository equipamentoRepository,
                                            ValorTarifaRepository valorTarifaRepository, CLNBoxRepository clnBoxRepository) {

        TabelaConsumo tc = null;
        Double setTotalKwm = totalLampadaLigada(data, registroRepository, equipamentoRepository, clnBoxRepository);

        try {
            if (!(repository.existsById(data.getIdPredio()))) {
                Double totalKwm = 0.0;
                Double totalReais = 0.0;
                InfoEmpresaRelatorio infoEmpresa = repository.infoEmpresa(data.getIdPredio());
                ValorTarifa vt = valorTarifaRepository.findByDateBetween(
                        LocalDateTime.of(data.getAno(), data.getMes(), 01, 00, 00),
                        calculoUltimoDiaMes(data.getMes(), data.getAno()));

                List<DadoConsumoMes> listaLength = repository.infoConsumoMes(data.getIdPredio());
                if (!(listaLength.isEmpty())) {
                    ListaObj<DadoConsumoMes> infoConsumoMes = new ListaObj<>(listaLength.size());
                    for (DadoConsumoMes mc : listaLength) {
                        infoConsumoMes.adiciona(mc);
                    }

                    for (int i = 0; i < infoConsumoMes.getTamanho(); i++) {
                        DadoConsumoMes mc = infoConsumoMes.getElemento(i);
                        mc.setConsumoKwm(setTotalKwm);
                        totalKwm += mc.getConsumoKwm();
                        totalReais += mc.getPreco();
                    }

                    tc = new TabelaConsumo("AUTG", data.getMes() + "/" + data.getAno(), vt.getBandeira(),
                            infoEmpresa.getNomeGestor(), infoEmpresa.getNomeEmpresa(), infoEmpresa.getCnpj(),
                            infoEmpresa.getNomePredio(), infoEmpresa.getEnderecoPredio(), infoEmpresa.getNumeroEnderecoPredio(), infoEmpresa.getCep(),
                            totalKwm, totalReais);

                } else {
                    // Lista vázia
                    return ResponseEntity.status(204).build();
                }

            } else {
                // Id inexistente
                return ResponseEntity.status(404).build();
            }
        } catch (Exception erro) {
            System.out.println("Erro ao puxar os dados do servidor: " + erro);
        }

        return ResponseEntity.status(200).body(tc);
    }

    public ResponseEntity<List<DadoConsumoMes>> informacoesDeConsumo(EquipamentoRelatorio data, EmpresaRepository repository,
                                                                     RegistroRepository registroRepository, EquipamentoRepository equipamentoRepository,
                                                                     ValorTarifaRepository valorTarifaRepository, CLNBoxRepository clnBoxRepository) {


        List<DadoConsumoMes> lista = repository.infoConsumoMes(data.getIdPredio());
        Double setTotalKwm = totalLampadaLigada(data, registroRepository, equipamentoRepository, clnBoxRepository);

        if (!(lista.isEmpty())) {
            for (DadoConsumoMes mc : lista) {
                mc.setConsumoKwm(setTotalKwm);
            }
        }else {
             // Lista vázia
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(lista);
    }
}