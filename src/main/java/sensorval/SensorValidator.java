package sensorval;

import java.util.List;

public class SensorValidator 
{
	static final double SOC_MAX_INTERVAL=0.05;
	static final double  CURRENT_MAX_INTERVAL=0.1;
	
    public static boolean isSensorNoisy(double value, double nextValue, double maxDelta) {
    	
        return (nextValue - value > maxDelta);
    }
    
    public static boolean validateSOCreadings(List<Double> values) {
    	return (values!=null && !values.isEmpty())?validateReadings(values,SOC_MAX_INTERVAL):false;
    }
    public static boolean validateCurrentreadings(List<Double> values) {
       
    	return (values!=null && !values.isEmpty())?validateReadings(values,CURRENT_MAX_INTERVAL):false;
    	
    }
    
    public static boolean validateReadings(List<Double> values,double maxInterval){
    	int lastButOneIndex = values.size() - 1;
        for(int i = 0; i < lastButOneIndex; i++) {
            if(checkReadings(values,i,maxInterval)) {
            return false;
            }
        }
        return true;
    }
     

	private static boolean checkReadings(List<Double> values, int index,double maxInterval) {
		
		if (!isValueNull(values,index)&&isSensorNoisy(values.get(index), values.get(index + 1),maxInterval)) {
			return true;
		} 
		return false;
	}

	private static boolean isValueNull(List<Double> values,int index) {
		// check array content is null
		return (values.get(index)== null || values.get(index + 1)== null);
	}
}
