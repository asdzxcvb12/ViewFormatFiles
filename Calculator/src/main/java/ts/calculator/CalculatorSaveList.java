package ts.calculator;

/**
 * Created by user on 2017-02-21.
 */
public class CalculatorSaveList {
    private String CalSaveName;
    private String CalculationResult;
    private String Calculation;

    public CalculatorSaveList(String CalSaveName, String CalculationResult, String Calculation) {
        this.CalSaveName = CalSaveName;
        this.CalculationResult = CalculationResult;
        this.Calculation = Calculation;
    }

    public String getCalSaveName() {
        return this.CalSaveName;
    }

    public String getCalculationResult() {
        return this.CalculationResult;
    }

    public String getCalculation() {
        return this.Calculation;
    }
}