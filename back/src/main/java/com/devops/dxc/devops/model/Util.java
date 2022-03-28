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
        float impuestoF = 0;
        int retiro = getDxc(ahorro, sueldo);

        if(sueldo > 1500000){
            int bruto_mensual_retiro = (sueldo * 12 + getDxc(ahorro, sueldo))/ 12;  // se calcula el sueldo bruto mas el 10% a retirar
            /*  Tramos
            1.- 1.500.001 -> 1.530.870  | 0,04  |  27.555,66
            2.- 1.530.871 -> 2.551.450  | 0,08  |  88.790,46
            3.- 2.551.451 -> 3.572.030  | 0,135 | 229.120,21
            4.- 3.572.031 -> 4.592.610  | 0,23  | 568.463,06
            5.- 4.592.611 -> 6.123.480  | 0,304 | 908.316,20
            6.- 6.123.481 -> 15.818.990 | 0,35  | 1.189.996,28
            7.- 15.818.991 >            | 0,4   | 1.980.945,78   
            */ 
            if(sueldo > 1500000 && sueldo <= 1530870){
                impuestoF = retiro * 0.04f;
            }else if(sueldo > 1500000 && sueldo <= 2551450){
                impuestoF = retiro * 0.08f;
            }else if(sueldo > 2551450 && sueldo <= 3572030){
                impuestoF = retiro * 0.135f;
            }else if(sueldo > 3572030 && sueldo <= 4592610){
                impuestoF = retiro * 0.23f;
            }else if(sueldo > 4592610 && sueldo <= 6123480){
                impuestoF = retiro * 0.304f;
            }else if(sueldo > 6123480 && sueldo <= 15818990){
                impuestoF = retiro * 0.35f;
            }else if(sueldo > 15818990){
                impuestoF = retiro * 0.40f;
            }
            impuesto = (int)impuestoF;


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