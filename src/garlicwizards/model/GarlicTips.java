/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garlicwizards.model;

/**
 *
 * @author Beto
 */
public class GarlicTips {
    
    private static String tips[] = {
        "",
        "El ajo es tiene propiedades anti-microbianas \ny anti-bacteriales",
        "El ajo fue conocido como “penicilina rusa” durante Segunda Guerra \nMundial debido a sus propiedades medicinales",
        "Si deseas eliminar el aliento a ajo, bebe leche o mastica una \nhoja de perejil",
        "La alicina es el compuesto químico responsable \ndel olor característico del ajo",
        "El aliento a ajo es causado debido a la presencia de \nsulfuro de metilo alílico en el ajo",
        "El ajo tiene sus orígenes en el \ncontinente asiático",
        "El ajo no tiene su olor característico hasta que se corta, debido a \nque la alicina se produce al picar o triturar el gajo.",
        "La alicina actúa como un antioxidante muy potente y anticancerígeno \ny es un estimulante excelente del sistema inmunológico.",
        "Los estudios demostraron que las personas que consumen ajo crudo o \ncocido se enfrentan regularmente a la mitad de riesgo de cáncer de estómago.",
        "Consumir ajo crudo protege al cuerpo de los efectos dañinos de los \nradicales libres.",
        "La alicina es un vasodilatador y anticoagulante natural que se \nencuentra en el ajo.",
        "El ajo es un gran aliado para combatir enfermedades como: la \ndiabetes, hipertensión y problemas vasculares.",
        "El ajo es rico en vitaminas antioxidantes como la vitamina B3,\nvitamina B6 y la vitamina C",
        "El ajo ayuda a aumentar el colesterol bueno",
        "El ajo es rico en minerales como el manganeso, selenio, \npotasio, fósforo y hierro"
    };
    
    public static String getRandomTip(){
        int index = (int) (Math.random() * (tips.length) + 1);
        return tips[index];
    }
}
