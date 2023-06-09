package com.br.pucgo.view;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.br.pucgo.model.Usuarios;
import com.br.pucgo.model.Veiculos;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatorioService {

    public byte[] gerarRelatorioUsuarios(List<Usuarios> usuarios) throws Exception {
        try {
            String jrxmlFile = "src/main/resources/reports/VehiclesProfits.jrxml";
            String jasperFile = "src/main/resources/reports/VehiclesProfits.jasper";
            File file = new File(jasperFile);

            if (!file.exists()) {
                JasperCompileManager.compileReportToFile(jrxmlFile, jasperFile);
            }

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/consorcio_veiculos", "root", "1304200296");

            Map<String, Object> parametros = new HashMap<>();

            List<Map<String, Object>> dataSet = new ArrayList<>();

            for (Usuarios usuario : usuarios) {
                Veiculos veiculos = new Veiculos();
                Map<String, Object> data = new HashMap<>();
                data.put("nomeComprador", usuario.getNome_usuario());
                data.put("nomeVeiculo", veiculos.getModelo());
                data.put("valorVeiculo", veiculos.getValor());
                dataSet.add(data);
            }


            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataSet);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, parametros, dataSource);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            byte[] relatorio = outputStream.toByteArray();

            conn.close();

            System.out.println("Relatório gerado com sucesso!");

            return relatorio;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao gerar relatório de usuários.", e);
        }
    }
}
