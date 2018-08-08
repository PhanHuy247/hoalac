package com.vnpt.iot.hoalac.api.model;


import com.vnpt.iot.hoalac.api.dto.ScheduleConfigDTO;
import com.vnpt.iot.hoalac.api.dto.ScheduleConfigItemDTO;
import com.vnpt.iot.hoalac.api.dto.ScheduleCriteriaItemDTO;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by anhqh on 5/18/2017.
 */

public class GatewayAPI {


    /**
     * generate gate way command
     * @param  commandName
     * @param commandValue
     * @return String
     * */
    public static String generateGWCommand(String commandName,String commandValue){

        StringBuilder  cmd =new StringBuilder();
        cmd.append("{").append("\"").append("name").append("\"").append(":").append("\"")
         .append(commandName).append("\"").append(",").append("\"")
         .append("value").append("\"").append(":").append("\"")
         .append(commandValue).append("\"").append("}");
            return cmd.toString();
    }
    /**
     * generate gate way direct control device message
     * @param   command
     * @return String
     * */
    public static  String generateControlDevice(String command){
        JSONObject jsonObject =new JSONObject();
        try {
            jsonObject.put("resourceType",12); // Command Resource Type
            jsonObject.put("action",3); // 1 : inssert or update , 2 : delete : 3 Control
            jsonObject.put("value",command);

        }
        catch (Exception e){
        }
        return jsonObject.toString();
    }


    /**
     * generate gate way message schedule configuration at gate way
     *
     * */
    public static String generateCreateOrUpdateSchedule(String scheduleName, Integer state, Integer mode, String cmd,
                                                        String originalDeviceId, String dependDeviceId, String valueAbove,
                                                        String valueBelow, String conditionParam, String timeZone){
        JSONObject jsonObject =new JSONObject();
        try {

            jsonObject.put("resourceType",18); //schedule
            jsonObject.put("schedule", scheduleName);
            jsonObject.put("action", 1); // 1 create or updpate , 2 : delete
            jsonObject.put("state", state); // 0 : stop 1: Active
            jsonObject.put("mode", mode); //1 daily 2 range
            jsonObject.put("command", cmd); // generateGWCommand
            jsonObject.put("originalDeviceId", originalDeviceId);
            jsonObject.put("dependDeviceId", dependDeviceId);
            jsonObject.put("valueAbove", valueAbove);
            jsonObject.put("valueBelow", valueBelow);
            jsonObject.put("conditionParam", conditionParam); // hum , temp, light ....
    	    if (mode==1) {    	  
//    			jsonObject.put("value", value);
                jsonObject.put("valueAbove", convertToGMT0(timeZone, valueAbove));
                jsonObject.put("valueBelow", convertToGMT0(timeZone, valueBelow));
    			jsonObject.put("timeZone", "GMT+00:00");
    	    }
    	    else  {
    			jsonObject.put("valueAbove", valueAbove);
    			jsonObject.put("valueBelow", valueBelow);
    	    }
        }
        catch (Exception e){

        }
        return jsonObject.toString();
    }

    public static String convertToGMT0(String timeZone, String time){
        if (timeZone!=null){
            timeZone=timeZone.replace("GMT", "");
            String plus= timeZone.startsWith("+")?"+":"-";
//    				String _plus= timeZone.startsWith("+")?"":"-";
            timeZone=timeZone.replace(plus, "");
            String hhmm[]= timeZone.split(":");
            if (hhmm!=null &&hhmm.length>1) {
                String hh =hhmm[0];
                String mmm =hhmm[1];
                Integer _mmm =Integer.parseInt(mmm);


                String mm30= time.split(":")[1];
                Integer realMin4gateway = Integer.parseInt(mm30)-_mmm;
                Integer _hh = Integer.parseInt(hh);
                String vab = time.split(":")[0];
                Integer crr = Integer.parseInt(vab)-_hh;
                crr =crr<0?24-(_hh-Integer.parseInt(vab)):crr;
                crr =realMin4gateway<0?crr-1:crr;
                String valab = String.valueOf(crr);
                valab = crr<10?"0".concat(valab):valab;
                if (time.split(":").length>1) {
                    Integer mm = realMin4gateway<0?60-(_mmm-Integer.parseInt(mm30)):realMin4gateway; //  Integer.parseInt(valueAbove.split(":")[1]);
                    String _mm = mm<10?"0".concat(String.valueOf(mm)):String.valueOf(mm);
                    valab=valab.concat(":").concat(_mm);
                }

                return valab;
            }

        }
        return "";
    }


    /**
     * generate delete message schedule at gate way
     *
     * */
    public static String generateDeleteCommand(String scheduleName) {
        JSONObject jsonObject =new JSONObject();
        try {

            jsonObject.put("resourceType",18); //schedule Resource Type
            jsonObject.put("schedule", scheduleName);
            jsonObject.put("action", 2); //delete

        }
        catch (Exception e){

        }
        return jsonObject.toString();
    }

    public static String regenerateWithTimeZone(String hhmm,String timeZone) {
    	String currval= "";
			timeZone=timeZone.replace("GMT", "");
			String plus= timeZone.startsWith("+")?"+":"-";
			timeZone=timeZone.replace(plus, "");
			String tz[] = timeZone.split(":");
			String _hhmm[]=hhmm.split(":");
			if (tz.length<2||_hhmm.length<2) {
				return null;
			}
			int currMin = Integer.parseInt(tz[1])+Integer.parseInt(_hhmm[1]);
			currMin = currMin>60?currMin-60:currMin;
			int currHour = Integer.parseInt(tz[0])+Integer.parseInt(_hhmm[0]);
			currHour=(Integer.parseInt(tz[1])+Integer.parseInt(_hhmm[1]))>60?currHour+1:currHour;
			currHour = currHour>24?currHour-24:currHour;
			
		    String _currHour = currHour<10?"0"+currHour:""+currHour;	
		    String _currMin = currMin<10?"0"+currMin:""+currMin;
		    currval= _currHour.concat(":").concat(_currMin);
    	
    	return currval;
    }

    public static String changeToTimeZoneZero(String hhmm,String timeZone) {
        String currval= "";
        timeZone=timeZone.replace("GMT", "");
        String plus= timeZone.startsWith("+")?"+":"-";
        timeZone=timeZone.replace(plus, "");
        String tz[] = timeZone.split(":");
        String _hhmm[]=hhmm.split(":");
        if (tz.length<2||_hhmm.length<2) {
            return null;
        }
        int currMin =Integer.parseInt(_hhmm[1])-Integer.parseInt(tz[1]);
        currMin = currMin<0?60-currMin:currMin;
        int currHour =Integer.parseInt(_hhmm[0])-Integer.parseInt(tz[0]);
        currHour=(Integer.parseInt(_hhmm[1])-Integer.parseInt(tz[1]))<0?currHour-1:currHour;
        currHour = currHour<0?24-currHour:currHour;

        String _currHour = currHour<10?"0"+currHour:""+currHour;
        String _currMin = currMin<10?"0"+currMin:""+currMin;
        currval= _currHour.concat(":").concat(_currMin);

        return currval;
    }



    public static String generateCreateOrUpdateMultiCondition(String scheduleName,Integer state,String timeZone,List<ScheduleCriteriaItemDTO> listSci){

        ScheduleConfigDTO scheduleConfigDTO =new ScheduleConfigDTO();
        scheduleConfigDTO.setAction(1);//create or update
        scheduleConfigDTO.setResourceType(18);
        scheduleConfigDTO.setSchedule(scheduleName);
        scheduleConfigDTO.setState(state);
        scheduleConfigDTO.setTimeZone(timeZone);

        //re-format time
        if (listSci!=null && !listSci.isEmpty()) {
            for (ScheduleCriteriaItemDTO sci : listSci) {
                if (sci.getMode()==2) { //daily executing
                    for (ScheduleConfigItemDTO sc : sci.getCriteriaList()){
                        if (sc.getValueAbove()!=null && !sc.getValueAbove().isEmpty()) {
                            sc.setValueAbove(changeToTimeZoneZero(sc.getValueAbove(), timeZone));
                        }
                        if (sc.getValueBelow()!=null && !sc.getValueBelow().isEmpty()) {
                            sc.setValueBelow(changeToTimeZoneZero(sc.getValueBelow(), timeZone));
                        }
                    }
                }
            }
        }
        scheduleConfigDTO.setCriteria(listSci);
        Gson gson = new Gson();
        String json = gson.toJson(scheduleConfigDTO);
        return json;
    }
}
