package fr.utbm.da50.fastandform.core.service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import fr.utbm.da50.fastandform.core.entity.Rule;

public class VerifyServiceFloat {

    public VerifyServiceFloat() {
      // TODO document why this constructor is empty
    }

    public Boolean controlFloat(List<Rule> rules, Double data){
        Boolean a=true;
        int i = 1;
        Boolean b=false;
        String id,type;
        Map<String, Object> options;

        if(data==null){
            for (Rule x : rules) {
                id = x.getId();
                if("required".equals(id)){
                    return false;
                }
            }
            return true;
        }

        for (Rule x : rules) {
          id = x.getId();
          type = x.getType();
          options = x.getOptions();

          if("length".equals(id)){
                b = length(options, data);
                if(Boolean.FALSE.equals(b)){return false;}
          }
          if("intervalle".equals(id)){
              b = intervalle(options, data);
              if(Boolean.FALSE.equals(b)){return false;}
          }
          if("regex".equals(id)){
            b = regex(options, data);
            if(Boolean.FALSE.equals(b)){return false;}
          }
        }

        return a;
    }
    
    public Boolean intervalle(Map<String, Object> x, Double data) {
        Double min, max;
        boolean b = true, c = true;

        if (x.containsKey("min")) {
            min = (Double) x.get("min");
            b = (data >= min);
        }
        if (x.containsKey("max")) {
            max = (Double) x.get("max");
            c = (data <= max);
        }
        return (b && c);
    }

    public Boolean length(Map<String, Object> x, Double data) {
        Double min, max, value;
        boolean b = true, c = true;

        if (x.containsKey("value")) {
            value = (Double) x.get("value");
            return (String.valueOf(data).length() == (value + 1));
        }
        if (x.containsKey("min")) {
            min = (Double) x.get("min");
            b = (String.valueOf(data).length() >= (min+1));
        }
        if (x.containsKey("max")) {
            max = (Double) x.get("max");
            c = (String.valueOf(data).length() <= (max+1));
        }

        return (b && c);
    }

    public Boolean regex(Map<String, Object> x, Double data) {
        Boolean b = true;
        String dataStr= String.valueOf(data);

        if (x.containsKey("value")) {
            String regex = (String) x.get("value");
            Pattern p = Pattern.compile(regex);
            if (!p.matcher(dataStr).matches()) {
                b = false;
            }
        }
        return b;
    }

}