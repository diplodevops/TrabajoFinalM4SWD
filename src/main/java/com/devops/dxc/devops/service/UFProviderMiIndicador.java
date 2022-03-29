package com.devops.dxc.devops.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.devops.dxc.devops.excepcion.UFNoDisponibleException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UFProviderMiIndicador implements UFProvider {

    @Override
    public int getPorDia(Date dia) throws UFNoDisponibleException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String jsonUf;
        try {
            jsonUf = peticionHttpGet("https://mindicador.cl/api/uf/" + formatter.format(dia));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonUf);
            return jsonNode.get("serie").get(0).get("valor").asInt();
        } catch (Exception e) {
            throw new UFNoDisponibleException(e.getMessage());
        }
    }

    public static String peticionHttpGet(String urlParaVisitar) throws Exception {
        // Esto es lo que vamos a devolver
        StringBuilder resultado = new StringBuilder();
        // Crear un objeto de tipo URL
        URL url = new URL(urlParaVisitar);

        // Abrir la conexión e indicar que será de tipo GET
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        // Búferes para leer
        BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea;
        // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);
        }
        // Cerrar el BufferedReader
        rd.close();
        return resultado.toString();
    }
}
