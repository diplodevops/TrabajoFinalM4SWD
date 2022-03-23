package com.devops.dxc.devops.model;

import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import org.json.JSONArray;
import java.text.SimpleDateFormat;
import java.util.Date;

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


    public static int getSaldo(int ahorro, int sueldo){
        int salida = getDxc(ahorro, sueldo);
        return (ahorro - salida);
    }

    public static int getImpuesto(int ahorro, int sueldo){
        int uf = getUf();
        int impuesto = 0; 

        if(sueldo > 1500000){
            
        }else{
            impuesto = 0;
        }

        return impuesto;
    }

    /**
     * Método que retorna el valor de la UF.  Este método debe ser refactorizado por una integración a un servicio
     * que retorne la UF en tiempo real.  Por ejemplo mindicador.cl
     * @return
     */
    public static int getUf(){

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String fecha= formatter.format(date);
        //System.out.println(fecha);

        RestTemplate plantilla = new RestTemplate();
        String resultado = plantilla.getForObject("https://mindicador.cl/api/uf/" + fecha, String.class);
        //System.out.println("Resultado " + resultado);
        int uf = 0;
        try{
        JSONObject result = null;
        if (resultado != null && resultado.length() > 0)
            result = new JSONObject(resultado);

            //JsonValue serie = result.get("serie");

            System.out.println("Resultado de Json   : " + result);
            System.out.println("*********************************" );
            System.out.println("codigo         : " + result.get("codigo"));
            System.out.println("unidad_medida  : " + result.get("unidad_medida"));
            System.out.println("serie          : " + result.get("serie"));
            System.out.println("*********************************" );

            JSONArray resultados = result.getJSONArray("serie");
            JSONObject jsonObject = resultados.getJSONObject(0);
           

            if(jsonObject.has("valor")){
                System.out.println("Valor UF          : " + jsonObject.getString("valor"));
                System.out.println("Valor UF en numero      : " + (int) Float.parseFloat(jsonObject.getString("valor")));
                uf = (int) Float.parseFloat(jsonObject.getString("valor"));
            }else {
                System.out.println("No exist eresultado          : ");
                uf = 0;
            }


        }catch(Exception ex){
            ex.toString();
        }


        return uf;
    }
    
}
