package com.devops.dxc.devops.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.*;

import org.springframework.web.client.RestTemplate;

public class Util {

    /**
     * Método para cacular el 10% del ahorro en la AFP.  Las reglas de negocio se pueden conocer en
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     *
     * @param ahorro
     * @param sueldo
     * @return
     */
    public static int getDxc(int ahorro, int sueldo){
        if(((ahorro*0.1)/getUf()) > 150 ){
            return (int) (150*getUf()) ;
        } else if((ahorro*0.1)<=1000000 && ahorro >=1000000){
            return (int) 1000000;
        } else if( ahorro <=1000000){
            return (int) ahorro;
        } else {
            return (int) (ahorro*0.1);
        }
    }

    /**
     * Método que retorna el valor de la UF.  Este método debe ser refactorizado por una integración a un servicio
     * que retorne la UF en tiempo real.  Por ejemplo mindicador.cl
     * @return
     */

    public static Double getUf(){
        String formatoFecha = "dd-MM-yyyy";
        String fecha = new SimpleDateFormat(formatoFecha).format(new Date());
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("https://mindicador.cl/api/{tipo_indicador}/{fecha}", String.class, "uf", fecha);
        JSONObject obj = new JSONObject(result);
        JSONArray serie = obj.getJSONArray("serie"); 
        Double valor_uf = serie.getJSONObject(0).getDouble("valor");
        return valor_uf;
    }

}
