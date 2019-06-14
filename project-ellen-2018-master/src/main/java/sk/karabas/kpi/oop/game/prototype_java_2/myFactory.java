package sk.karabas.kpi.oop.game.prototype_java_2;


import sk.karabas.kpi.oop.game.characters.Alien;

import java.util.HashMap;
import java.util.Map;

public class myFactory {
    private Map<myProductType, myProduct> templates;



    public myFactory() {
        this.templates = new HashMap<>();
      Alien alien= Alien.getInstance();

      if(alien==null)return;
       this.templates.put(myProductType.KeyY, new myProduct(myProductType.KeyY,alien));

    }


    public myProduct createProduct(myProductType type) {
        return (myProduct) this.templates.get(type).createClone();}

}
