package sensorval;

import java.util.List;

public class SensorValidator 
{
	static final double SOC_JUMPFREQUENCY=0.05;
	static final double  CURRENT_JUMPFREQUENCY=0.1;
	
    public static boolean isSensorNoisy(double value, double nextValue, double maxDelta) {
    	
        return (nextValue - value > maxDelta);
    }
    
    public static boolean validateSOCreadings(List<Double> values) {
    	return (values==null)?false:validateReadings(values,SOC_JUMPFREQUENCY);
    }
    public static boolean validateCurrentreadings(List<Double> values) {
        return (values==null)?false:validateReadings(values,CURRENT_JUMPFREQUENCY);
    }
    
    public static boolean validateReadings(List<Double> values,double jumpfrequency){
    	int lastButOneIndex = values.size() - 1;
        for(int i = 0; i < lastButOneIndex; i++) {
            if(isSensorNoisy(values.get(i), values.get(i + 1),jumpfrequency)) {
            return false;
            }
        }
        return true;
    }
}
